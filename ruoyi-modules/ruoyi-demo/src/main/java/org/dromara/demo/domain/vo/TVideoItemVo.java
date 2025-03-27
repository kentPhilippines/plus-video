package org.dromara.demo.domain.vo;

import org.dromara.demo.domain.TVideoItem;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * video视图对象 t_video_item
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TVideoItem.class)
public class TVideoItemVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long rowId;

    /**
     * 2.大标题3.小标题
     */
    @ExcelProperty(value = "2.大标题3.小标题")
    private Integer rowType;

    /**
     * 视频编码
     */
    @ExcelProperty(value = "视频编码")
    private String itemCode;

    /**
     * 视频名称
     */
    @ExcelProperty(value = "视频名称")
    private String itemName;

    /**
     * 0:禁用 1:启用
     */
    @ExcelProperty(value = "0:禁用 1:启用")
    private Integer enable;

    /**
     * 是否热门 0:不是 1:是
     */
    @ExcelProperty(value = "是否热门 0:不是 1:是", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer hot;

    /**
     * 热门排序
     */
    @ExcelProperty(value = "热门排序")
    private Integer hotOrderId;

    /**
     * 0否 1是 分类热门
     */
    @ExcelProperty(value = "0否 1是 分类热门", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer recommend;

    /**
     * 排序编号
     */
    @ExcelProperty(value = "排序编号")
    private Integer orderId;

    /**
     * 分类热门排序ID
     */
    @ExcelProperty(value = "分类热门排序ID")
    private Integer recommendOrderId;

    /**
     * 站点(每个租户分配一个站点标识)
     */
    @ExcelProperty(value = "站点(每个租户分配一个站点标识)")
    private String site;

    /**
     * 修改人
     */
    @ExcelProperty(value = "修改人")
    private String modifyBy;

    /**
     * 最后修改时间
     */
    @ExcelProperty(value = "最后修改时间")
    private Long lastModifyTime;

    /**
     * 语言(zh,br)
     */
    @ExcelProperty(value = "语言(zh,br)")
    private String languageCode;

    /**
     * 图标(后台上传)
     */
    @ExcelProperty(value = "图标(后台上传)")
    private String icon;

    /**
     * 视频地址
     */
    @ExcelProperty(value = "视频地址")
    private String videoUrl;


}
