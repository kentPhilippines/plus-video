package org.dromara.video.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.video.domain.Video;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 视频视图对象 video
 *
 * @author Lion Li
 */
@Data
@AutoMapper(target = Video.class)
public class VideoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 封面URL
     */
    private String coverUrl;

    /**
     * 原始视频URL
     */
    private String videoUrl;

    /**
     * m3u8播放地址
     */
    private String m3u8Url;

    /**
     * 视频时长(秒)
     */
    private Integer duration;

    /**
     * 视频大小(字节)
     */
    private Long size;

    /**
     * 格式化后的视频大小
     */
    private String formattedSize;

    /**
     * 格式化后的视频时长
     */
    private String formattedDuration;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建者名称
     */
    @Translation(type = TransConstant.USER_ID_TO_NAME, mapper = "createBy")
    private String createByName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

} 