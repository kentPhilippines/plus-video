package org.dromara.video.domain;

import javax.annotation.processing.Generated;
import org.dromara.video.domain.vo.VideoVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T15:00:27+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class VideoToVideoVoMapperImpl implements VideoToVideoVoMapper {

    @Override
    public VideoVo convert(Video arg0) {
        if ( arg0 == null ) {
            return null;
        }

        VideoVo videoVo = new VideoVo();

        videoVo.setCoverUrl( arg0.getCoverUrl() );
        videoVo.setCreateBy( arg0.getCreateBy() );
        videoVo.setCreateTime( arg0.getCreateTime() );
        videoVo.setDescription( arg0.getDescription() );
        videoVo.setDuration( arg0.getDuration() );
        videoVo.setM3u8Url( arg0.getM3u8Url() );
        videoVo.setSize( arg0.getSize() );
        videoVo.setStatus( arg0.getStatus() );
        videoVo.setTitle( arg0.getTitle() );
        videoVo.setUpdateBy( arg0.getUpdateBy() );
        videoVo.setUpdateTime( arg0.getUpdateTime() );
        videoVo.setVideoId( arg0.getVideoId() );
        videoVo.setVideoUrl( arg0.getVideoUrl() );

        return videoVo;
    }

    @Override
    public VideoVo convert(Video arg0, VideoVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCoverUrl( arg0.getCoverUrl() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setDescription( arg0.getDescription() );
        arg1.setDuration( arg0.getDuration() );
        arg1.setM3u8Url( arg0.getM3u8Url() );
        arg1.setSize( arg0.getSize() );
        arg1.setStatus( arg0.getStatus() );
        arg1.setTitle( arg0.getTitle() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setVideoId( arg0.getVideoId() );
        arg1.setVideoUrl( arg0.getVideoUrl() );

        return arg1;
    }
}
