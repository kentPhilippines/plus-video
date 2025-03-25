package org.dromara.video.domain.bo;

import io.github.linpeilie.AutoMapperConfig__15;
import io.github.linpeilie.BaseMapper;
import org.dromara.video.domain.Video;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__15.class,
    uses = {},
    imports = {}
)
public interface VideoBoToVideoMapper extends BaseMapper<VideoBo, Video> {
}
