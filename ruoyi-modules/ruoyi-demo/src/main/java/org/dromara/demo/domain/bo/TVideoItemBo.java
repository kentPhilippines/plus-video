package org.dromara.demo.domain.bo;

import org.dromara.demo.domain.TVideoItem;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * video业务对象 t_video_item
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TVideoItem.class, reverseConvertGenerate = false)
public class TVideoItemBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
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
