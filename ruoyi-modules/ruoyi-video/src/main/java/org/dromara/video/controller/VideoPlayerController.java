package org.dromara.video.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.video.service.IVideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 视频播放器控制器
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/video/player")
public class VideoPlayerController {

    private final IVideoService videoService;

    /**
     * 视频播放页面
     */
    @SaCheckPermission("video:play")
    @GetMapping("/index")
    public String index(@RequestParam("id") Long videoId) {
        return "videoPlayer";
    }
} 