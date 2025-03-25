package org.dromara.video.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.video.domain.Video;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T15:00:27+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class VideoBoToVideoMapperImpl implements VideoBoToVideoMapper {

    @Override
    public Video convert(VideoBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Video video = new Video();

        video.setCreateBy( arg0.getCreateBy() );
        video.setCreateDept( arg0.getCreateDept() );
        video.setCreateTime( arg0.getCreateTime() );
        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            video.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        video.setSearchValue( arg0.getSearchValue() );
        video.setUpdateBy( arg0.getUpdateBy() );
        video.setUpdateTime( arg0.getUpdateTime() );
        video.setCoverUrl( arg0.getCoverUrl() );
        video.setDelFlag( arg0.getDelFlag() );
        video.setDescription( arg0.getDescription() );
        video.setDuration( arg0.getDuration() );
        video.setM3u8Url( arg0.getM3u8Url() );
        video.setSize( arg0.getSize() );
        video.setStatus( arg0.getStatus() );
        video.setTitle( arg0.getTitle() );
        video.setVideoId( arg0.getVideoId() );
        video.setVideoUrl( arg0.getVideoUrl() );

        return video;
    }

    @Override
    public Video convert(VideoBo arg0, Video arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateTime( arg0.getCreateTime() );
        if ( arg1.getParams() != null ) {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.getParams().clear();
                arg1.getParams().putAll( map );
            }
            else {
                arg1.setParams( null );
            }
        }
        else {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.setParams( new LinkedHashMap<String, Object>( map ) );
            }
        }
        arg1.setSearchValue( arg0.getSearchValue() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setCoverUrl( arg0.getCoverUrl() );
        arg1.setDelFlag( arg0.getDelFlag() );
        arg1.setDescription( arg0.getDescription() );
        arg1.setDuration( arg0.getDuration() );
        arg1.setM3u8Url( arg0.getM3u8Url() );
        arg1.setSize( arg0.getSize() );
        arg1.setStatus( arg0.getStatus() );
        arg1.setTitle( arg0.getTitle() );
        arg1.setVideoId( arg0.getVideoId() );
        arg1.setVideoUrl( arg0.getVideoUrl() );

        return arg1;
    }
}
