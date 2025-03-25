package org.dromara.video.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.file.FileUtils;
import org.dromara.common.core.utils.file.MimeTypeUtils;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.oss.core.OssClient;
import org.dromara.common.oss.entity.UploadResult;
import org.dromara.common.oss.factory.OssFactory;
import org.dromara.video.domain.Video;
import org.dromara.video.domain.bo.VideoBo;
import org.dromara.video.domain.vo.VideoVo;
import org.dromara.video.mapper.VideoMapper;
import org.dromara.video.service.IVideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 视频服务实现
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements IVideoService {

    private final VideoMapper baseMapper;

    /**
     * 查询视频
     */
    @Override
    public VideoVo queryById(Long videoId) {
        VideoVo vo = baseMapper.selectVoById(videoId);
        if (vo != null) {
            // 格式化大小和时长
            vo.setFormattedSize(formatFileSize(vo.getSize()));
            vo.setFormattedDuration(formatDuration(vo.getDuration()));
        }
        return vo;
    }

    /**
     * 查询视频列表
     */
    @Override
    public TableDataInfo<VideoVo> queryPageList(VideoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Video> lqw = buildQueryWrapper(bo);
        Page<VideoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        
        // 格式化大小和时长
        for (VideoVo vo : result.getRecords()) {
            vo.setFormattedSize(formatFileSize(vo.getSize()));
            vo.setFormattedDuration(formatDuration(vo.getDuration()));
        }
        
        return TableDataInfo.build(result);
    }

    /**
     * 查询视频列表
     */
    @Override
    public List<VideoVo> queryList(VideoBo bo) {
        LambdaQueryWrapper<Video> lqw = buildQueryWrapper(bo);
        List<VideoVo> list = baseMapper.selectVoList(lqw);
        
        // 格式化大小和时长
        for (VideoVo vo : list) {
            vo.setFormattedSize(formatFileSize(vo.getSize()));
            vo.setFormattedDuration(formatDuration(vo.getDuration()));
        }
        
        return list;
    }

    private LambdaQueryWrapper<Video> buildQueryWrapper(VideoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Video> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), Video::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Video::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增视频
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(VideoBo bo) {
        Video add = MapstructUtils.convert(bo, Video.class);
        // 默认状态为正常
        add.setStatus("0");
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setVideoId(add.getVideoId());
        }
        return flag;
    }

    /**
     * 修改视频
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(VideoBo bo) {
        Video update = MapstructUtils.convert(bo, Video.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除视频
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 删除关联的视频文件
            for (Long id : ids) {
                VideoVo vo = baseMapper.selectVoById(id);
                if (vo != null) {
                    try {
                        if (StringUtils.isNotEmpty(vo.getVideoUrl())) {
                            OssClient storage = OssFactory.instance();
                            storage.delete(vo.getVideoUrl());
                        }
                        if (StringUtils.isNotEmpty(vo.getCoverUrl())) {
                            OssClient storage = OssFactory.instance();
                            storage.delete(vo.getCoverUrl());
                        }
                        if (StringUtils.isNotEmpty(vo.getM3u8Url())) {
                            OssClient storage = OssFactory.instance();
                            storage.delete(vo.getM3u8Url());
                            // 删除分片文件
                            // 注意：这里的实现取决于您的m3u8文件结构和存储位置
                            // 可能需要读取m3u8文件内容来获取所有分片的路径并逐个删除
                        }
                    } catch (Exception e) {
                        log.error("删除视频文件失败", e);
                    }
                }
            }
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 导出视频
     */
    @Override
    public void export(VideoBo bo, HttpServletResponse response) {
        List<VideoVo> list = queryList(bo);
        // 处理导出数据
        for (VideoVo vo : list) {
            vo.setFormattedSize(formatFileSize(vo.getSize()));
            vo.setFormattedDuration(formatDuration(vo.getDuration()));
        }
        ExcelUtil.exportExcel(list, "视频", VideoVo.class, response);
    }

    /**
     * 上传视频文件
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public VideoVo upload(MultipartFile file, String title, String description) {
        if (file.isEmpty()) {
            throw new ServiceException("上传视频文件不能为空");
        }
        
        // 校验视频格式
        String extension = FileUtil.extName(file.getOriginalFilename());
        if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.VIDEO_EXTENSION)) {
            throw new ServiceException("视频格式不正确，请上传" + Arrays.toString(MimeTypeUtils.VIDEO_EXTENSION) + "格式");
        }
        
        try {
            // 上传原始视频文件
            UploadResult uploadResult = uploadFile(file);
            
            // 创建视频记录
            Video video = new Video();
            video.setVideoId(IdUtil.getSnowflakeNextId());
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoUrl(uploadResult.getUrl());
            video.setSize(file.getSize());
            video.setStatus("0"); // 正常状态
            
            // 保存到数据库
            boolean success = baseMapper.insert(video) > 0;
            if (!success) {
                throw new ServiceException("保存视频信息失败");
            }
            
            // 异步处理视频（转m3u8和提取封面）
            // 注意：实际应用中应使用异步任务处理，避免阻塞用户操作
            // 这里为简化实现，直接在当前线程中处理
            processVideoToM3u8(video.getVideoId());
            
            return queryById(video.getVideoId());
        } catch (Exception e) {
            log.error("上传视频失败", e);
            throw new ServiceException("上传视频失败：" + e.getMessage());
        }
    }

    /**
     * 上传文件到OSS存储
     *
     * @param file 上传的文件
     * @return 上传结果
     */
    private UploadResult uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String suffix = StringUtils.substring(originalFilename, originalFilename.lastIndexOf("."), originalFilename.length());
        OssClient ossClient = OssFactory.instance();
        return ossClient.uploadSuffix(file.getBytes(), suffix, file.getContentType());
    }

    /**
     * 处理视频，转换为m3u8格式
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean processVideoToM3u8(Long videoId) {
        VideoVo video = queryById(videoId);
        if (video == null) {
            throw new ServiceException("视频不存在");
        }
        
        try {
            // 1. 下载原始视频到临时目录
            Path tempDir = Files.createTempDirectory("video_process_");
            Path videoPath = downloadVideo(video.getVideoUrl(), tempDir);
            
            // 2. 创建m3u8输出目录
            Path m3u8Dir = tempDir.resolve("m3u8");
            Files.createDirectories(m3u8Dir);
            
            // 3. 使用FFmpeg获取视频信息
            FFprobe ffprobe = new FFprobe("ffprobe"); // 根据实际情况配置ffprobe路径
            FFmpegProbeResult probeResult = ffprobe.probe(videoPath.toString());
            int duration = (int) Math.ceil(probeResult.getFormat().duration);
            
            // 4. 生成封面图（从视频的第一秒提取）
            Path coverPath = tempDir.resolve("cover.jpg");
            generateCover(videoPath, coverPath);
            
            // 5. 转换视频为m3u8格式
            Path m3u8Path = m3u8Dir.resolve("index.m3u8");
            convertToM3u8(videoPath, m3u8Path, m3u8Dir);
            
            // 6. 上传封面图
            UploadResult coverResult = null;
            if (Files.exists(coverPath)) {
                OssClient ossClient = OssFactory.instance();
                byte[] coverBytes = Files.readAllBytes(coverPath);
                coverResult = ossClient.uploadSuffix(coverBytes, ".jpg", "image/jpeg");
            }
            
            // 7. 上传m3u8文件及其分片
            OssClient ossClient = OssFactory.instance();
            String m3u8Prefix = "m3u8/" + videoId + "/";
            
            // 上传索引文件
            String m3u8Content = new String(Files.readAllBytes(m3u8Path));
            // 修改m3u8内容中的片段路径为OSS路径
            m3u8Content = m3u8Content.replaceAll("(segment\\d+\\.ts)", m3u8Prefix + "$1");
            UploadResult m3u8Result = ossClient.uploadSuffix(m3u8Content.getBytes(), "/index.m3u8", "application/vnd.apple.mpegurl");
            
            // 上传TS分片
            File[] tsFiles = m3u8Dir.toFile().listFiles((dir, name) -> name.endsWith(".ts"));
            if (tsFiles != null) {
                for (File tsFile : tsFiles) {
                    byte[] tsBytes = Files.readAllBytes(tsFile.toPath());
                    ossClient.uploadSuffix(tsBytes, "/" + tsFile.getName(), "video/mp2t");
                }
            }
            
            // 8. 更新视频信息
            Video updateVideo = new Video();
            updateVideo.setVideoId(videoId);
            updateVideo.setDuration(duration);
            if (coverResult != null) {
                updateVideo.setCoverUrl(coverResult.getUrl());
            }
            updateVideo.setM3u8Url(m3u8Result.getUrl());
            
            // 9. 清理临时文件
            FileUtils.del(tempDir.toFile());
            
            // 10. 保存数据库
            return baseMapper.updateById(updateVideo) > 0;
        } catch (Exception e) {
            log.error("处理视频失败", e);
            throw new ServiceException("处理视频失败：" + e.getMessage());
        }
    }

    /**
     * 下载视频文件到临时目录
     *
     * @param videoUrl 视频URL
     * @param tempDir  临时目录
     * @return 视频文件路径
     */
    private Path downloadVideo(String videoUrl, Path tempDir) throws IOException {
        OssClient ossClient = OssFactory.instance();
        String key = ossClient.removeBaseUrl(videoUrl);
        Path videoPath = tempDir.resolve("source" + getFileSuffix(key));
        
        // 如果是OSS地址，通过OSS客户端下载
        Path downloadedPath = ossClient.fileDownload(key);
        FileUtil.copy(downloadedPath.toFile(), videoPath.toFile(), true);
        
        return videoPath;
    }

    /**
     * 生成视频封面
     *
     * @param videoPath 视频文件路径
     * @param coverPath 封面输出路径
     */
    private void generateCover(Path videoPath, Path coverPath) throws IOException {
        FFmpeg ffmpeg = new FFmpeg("ffmpeg"); // 根据实际情况配置ffmpeg路径
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
        
        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput(videoPath.toString())
            .addOutput(coverPath.toString())
            .setFrames(1)
            .setVideoFilter("select='gte(t,1)'") // 从视频的第1秒提取帧
            .done();
        
        executor.createJob(builder).run();
    }

    /**
     * 将视频转换为HLS (m3u8) 格式
     *
     * @param videoPath 原始视频路径
     * @param m3u8Path  m3u8文件输出路径
     * @param m3u8Dir   m3u8片段输出目录
     */
    private void convertToM3u8(Path videoPath, Path m3u8Path, Path m3u8Dir) throws IOException {
        FFmpeg ffmpeg = new FFmpeg("ffmpeg"); // 根据实际情况配置ffmpeg路径
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
        
        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput(videoPath.toString())
            .addOutput(m3u8Path.toString())
            .setFormat("hls")
            .addExtraArgs("-hls_time", "10") // 每个片段的时长（秒）
            .addExtraArgs("-hls_list_size", "0") // 保留所有片段
            .addExtraArgs("-hls_segment_filename", m3u8Dir.resolve("segment%d.ts").toString()) // 片段命名
            .setAudioCodec("aac") // 音频编码
            .setVideoCodec("libx264") // 视频编码
            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // 允许实验性功能
            .done();
        
        executor.createJob(builder).run();
    }

    /**
     * 从文件路径获取文件后缀
     *
     * @param path 文件路径
     * @return 文件后缀（包括点，如 .mp4）
     */
    private String getFileSuffix(String path) {
        if (StringUtils.isEmpty(path)) {
            return "";
        }
        int lastIndex = path.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return path.substring(lastIndex);
    }

    /**
     * 格式化文件大小
     *
     * @param size 文件大小（字节）
     * @return 格式化后的文件大小字符串
     */
    private String formatFileSize(Long size) {
        if (size == null || size <= 0) {
            return "0 B";
        }
        
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return String.format("%.2f %s", size / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    /**
     * 格式化视频时长
     *
     * @param duration 视频时长（秒）
     * @return 格式化后的时长字符串（HH:MM:SS）
     */
    private String formatDuration(Integer duration) {
        if (duration == null || duration <= 0) {
            return "00:00:00";
        }
        
        long hours = TimeUnit.SECONDS.toHours(duration);
        long minutes = TimeUnit.SECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = duration - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.MINUTES.toSeconds(minutes);
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
} 