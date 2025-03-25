package org.dromara.video.domain.vo;

import io.github.linpeilie.AutoMapperConfig__15;
import io.github.linpeilie.BaseMapper;
import org.dromara.video.domain.Video;
import org.dromara.video.domain.VideoToVideoVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__15.class,
    uses = {VideoToVideoVoMapper.class},
    imports = {}
)
public interface VideoVoToVideoMapper extends BaseMapper<VideoVo, Video> {
}
