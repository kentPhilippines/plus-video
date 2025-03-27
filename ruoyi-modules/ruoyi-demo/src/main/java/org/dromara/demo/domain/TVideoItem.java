package org.dromara.demo.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * video对象 t_video_item
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_video_item")
public class TVideoItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "row_id")
    private Long rowId;

    /**
     * 2.大标题3.小标题
     */
    private Integer rowType;

    /**
     * 视频编码
     */
    private String itemCode;

    /**
     * 视频名称
     */
    private String itemName;

    /**
     * 0:禁用 1:启用
     */
    private Integer enable;

    /**
     * 是否热门 0:不是 1:是
     */
    private Integer hot;

    /**
     * 热门排序
     */
    private Integer hotOrderId;

    /**
     * 0否 1是 分类热门
     */
    private Integer recommend;

    /**
     * 排序编号
     */
    private Integer orderId;

    /**
     * 分类热门排序ID
     */
    private Integer recommendOrderId;

    /**
     * 站点(每个租户分配一个站点标识)
     */
    private String site;

    /**
     * 修改人
     */
    private String modifyBy;

    /**
     * 最后修改时间
     */
    private Long lastModifyTime;

    /**
     * 语言(zh,br)
     */
    private String languageCode;

    /**
     * 图标(后台上传)
     */
    private String icon;

    /**
     * 视频地址
     */
    private String videoUrl;


}
