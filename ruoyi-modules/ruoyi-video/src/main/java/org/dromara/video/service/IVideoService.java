package org.dromara.video.service;

import org.dromara.video.domain.bo.VideoBo;
import org.dromara.video.domain.vo.VideoVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 视频服务接口
 *
 * @author Lion Li
 */
public interface IVideoService {

    /**
     * 查询视频
     */
    VideoVo queryById(Long videoId);

    /**
     * 查询视频列表
     */
    TableDataInfo<VideoVo> queryPageList(VideoBo bo, PageQuery pageQuery);

    /**
     * 查询视频列表
     */
    List<VideoVo> queryList(VideoBo bo);

    /**
     * 新增视频
     */
    Boolean insertByBo(VideoBo bo);

    /**
     * 修改视频
     */
    Boolean updateByBo(VideoBo bo);

    /**
     * 校验并批量删除视频信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 导出视频
     */
    void export(VideoBo bo, HttpServletResponse response);

    /**
     * 上传视频
     */
    VideoVo upload(MultipartFile file, String title, String description);

    /**
     * 处理视频，转换为m3u8格式
     */
    Boolean processVideoToM3u8(Long videoId);
} 