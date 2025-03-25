package org.dromara.video.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.video.domain.bo.VideoBo;
import org.dromara.video.domain.vo.VideoVo;
import org.dromara.video.service.IVideoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 视频控制器
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {

    private final IVideoService videoService;

    /**
     * 查询视频列表
     */
    @SaCheckPermission("video:list")
    @GetMapping("/list")
    public TableDataInfo<VideoVo> list(VideoBo bo, PageQuery pageQuery) {
        return videoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出视频列表
     */
    @SaCheckPermission("video:export")
    @Log(title = "视频", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(VideoBo bo, HttpServletResponse response) {
        videoService.export(bo, response);
    }

    /**
     * 获取视频详细信息
     */
    @SaCheckPermission("video:query")
    @GetMapping(value = "/{videoId}")
    public R<VideoVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long videoId) {
        return R.ok(videoService.queryById(videoId));
    }

    /**
     * 新增视频
     */
    @SaCheckPermission("video:add")
    @Log(title = "视频", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody VideoBo bo) {
        return toAjax(videoService.insertByBo(bo));
    }

    /**
     * 修改视频
     */
    @SaCheckPermission("video:edit")
    @Log(title = "视频", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody VideoBo bo) {
        return toAjax(videoService.updateByBo(bo));
    }

    /**
     * 删除视频
     */
    @SaCheckPermission("video:remove")
    @Log(title = "视频", businessType = BusinessType.DELETE)
    @DeleteMapping("/{videoIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] videoIds) {
        return toAjax(videoService.deleteWithValidByIds(Arrays.asList(videoIds), true));
    }

    /**
     * 上传视频
     */
    @SaCheckPermission("video:upload")
    @Log(title = "视频上传", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public R<VideoVo> upload(@RequestParam("videoFile") MultipartFile file,
                         @RequestParam("title") String title,
                         @RequestParam(value = "description", required = false) String description) {
        VideoVo videoVo = videoService.upload(file, title, description);
        return R.ok(videoVo);
    }

    /**
     * 处理视频
     */
    @SaCheckPermission("video:edit")
    @Log(title = "视频处理", businessType = BusinessType.UPDATE)
    @PostMapping("/process/{videoId}")
    public R<Void> processVideo(@NotNull(message = "主键不能为空") @PathVariable Long videoId) {
        return toAjax(videoService.processVideoToM3u8(videoId));
    }

    /**
     * 获取视频播放信息
     */
    @SaCheckPermission("video:play")
    @GetMapping("/play/{videoId}")
    public R<VideoVo> playVideo(@NotNull(message = "主键不能为空") @PathVariable Long videoId) {
        return R.ok(videoService.queryById(videoId));
    }
} 