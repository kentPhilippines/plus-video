package org.dromara.demo.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.demo.domain.vo.TVideoItemVo;
import org.dromara.demo.domain.bo.TVideoItemBo;
import org.dromara.demo.service.ITVideoItemService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * video
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/videoItem")
public class TVideoItemController extends BaseController {

    private final ITVideoItemService tVideoItemService;

    /**
     * 查询video列表
     */
    @SaCheckPermission("system:videoItem:list")
    @GetMapping("/list")
    public TableDataInfo<TVideoItemVo> list(TVideoItemBo bo, PageQuery pageQuery) {
        return tVideoItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出video列表
     */
    @SaCheckPermission("system:videoItem:export")
    @Log(title = "video", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TVideoItemBo bo, HttpServletResponse response) {
        List<TVideoItemVo> list = tVideoItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "video", TVideoItemVo.class, response);
    }

    /**
     * 获取video详细信息
     *
     * @param rowId 主键
     */
    @SaCheckPermission("system:videoItem:query")
    @GetMapping("/{rowId}")
    public R<TVideoItemVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long rowId) {
        return R.ok(tVideoItemService.queryById(rowId));
    }

    /**
     * 新增video
     */
    @SaCheckPermission("system:videoItem:add")
    @Log(title = "video", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TVideoItemBo bo) {
        return toAjax(tVideoItemService.insertByBo(bo));
    }

    /**
     * 修改video
     */
    @SaCheckPermission("system:videoItem:edit")
    @Log(title = "video", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TVideoItemBo bo) {
        return toAjax(tVideoItemService.updateByBo(bo));
    }

    /**
     * 删除video
     *
     * @param rowIds 主键串
     */
    @SaCheckPermission("system:videoItem:remove")
    @Log(title = "video", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rowIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] rowIds) {
        return toAjax(tVideoItemService.deleteWithValidByIds(List.of(rowIds), true));
    }
}
