package org.dromara.demo.service;

import org.dromara.demo.domain.vo.TVideoItemVo;
import org.dromara.demo.domain.bo.TVideoItemBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * videoService接口
 *
 * @author Lion Li
 * @date 2025-03-27
 */
public interface ITVideoItemService {

    /**
     * 查询video
     *
     * @param rowId 主键
     * @return video
     */
    TVideoItemVo queryById(Long rowId);

    /**
     * 分页查询video列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return video分页列表
     */
    TableDataInfo<TVideoItemVo> queryPageList(TVideoItemBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的video列表
     *
     * @param bo 查询条件
     * @return video列表
     */
    List<TVideoItemVo> queryList(TVideoItemBo bo);

    /**
     * 新增video
     *
     * @param bo video
     * @return 是否新增成功
     */
    Boolean insertByBo(TVideoItemBo bo);

    /**
     * 修改video
     *
     * @param bo video
     * @return 是否修改成功
     */
    Boolean updateByBo(TVideoItemBo bo);

    /**
     * 校验并批量删除video信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
