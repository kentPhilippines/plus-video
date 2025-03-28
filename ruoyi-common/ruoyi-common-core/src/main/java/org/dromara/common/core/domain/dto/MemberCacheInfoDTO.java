package org.dromara.common.core.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jerry
 */
@Data
@Accessors(chain = true)
public class MemberCacheInfoDTO {
    /**
     * 来源|下载地址
     */
    private String downloadSite;
    /**
     * 会员层级
     */
    private Integer levelId;
    /**
     * 用户端.站点
     */
    private String site;
    /**
     * 注册时间
     */
    private Long createTime;
}
