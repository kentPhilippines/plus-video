package org.dromara.video.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.video.domain.Video;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频业务对象 video
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Video.class, reverseConvertGenerate = false)
public class VideoBo extends Video {

    /**
     * 视频ID
     */
    @NotNull(message = "视频ID不能为空", groups = { EditGroup.class })
    private Long videoId;

    /**
     * 视频标题
     */
    @NotBlank(message = "视频标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

} 