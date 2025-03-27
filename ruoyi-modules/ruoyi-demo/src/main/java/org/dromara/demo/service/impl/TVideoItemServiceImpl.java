package org.dromara.demo.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.demo.domain.bo.TVideoItemBo;
import org.dromara.demo.domain.vo.TVideoItemVo;
import org.dromara.demo.domain.TVideoItem;
import org.dromara.demo.mapper.TVideoItemMapper;
import org.dromara.demo.service.ITVideoItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * videoService业务层处理
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@RequiredArgsConstructor
@Service
public class TVideoItemServiceImpl implements ITVideoItemService {

    private final TVideoItemMapper baseMapper;

    /**
     * 查询video
     *
     * @param rowId 主键
     * @return video
     */
    @Override
    public TVideoItemVo queryById(Long rowId){
        return baseMapper.selectVoById(rowId);
    }

    /**
     * 分页查询video列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return video分页列表
     */
    @Override
    public TableDataInfo<TVideoItemVo> queryPageList(TVideoItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TVideoItem> lqw = buildQueryWrapper(bo);
        Page<TVideoItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的video列表
     *
     * @param bo 查询条件
     * @return video列表
     */
    @Override
    public List<TVideoItemVo> queryList(TVideoItemBo bo) {
        LambdaQueryWrapper<TVideoItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TVideoItem> buildQueryWrapper(TVideoItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TVideoItem> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TVideoItem::getRowId);
        lqw.eq(bo.getRowType() != null, TVideoItem::getRowType, bo.getRowType());
        lqw.eq(StringUtils.isNotBlank(bo.getItemCode()), TVideoItem::getItemCode, bo.getItemCode());
        lqw.like(StringUtils.isNotBlank(bo.getItemName()), TVideoItem::getItemName, bo.getItemName());
        lqw.eq(bo.getEnable() != null, TVideoItem::getEnable, bo.getEnable());
        lqw.eq(bo.getHot() != null, TVideoItem::getHot, bo.getHot());
        lqw.eq(bo.getHotOrderId() != null, TVideoItem::getHotOrderId, bo.getHotOrderId());
        lqw.eq(bo.getRecommend() != null, TVideoItem::getRecommend, bo.getRecommend());
        lqw.eq(bo.getOrderId() != null, TVideoItem::getOrderId, bo.getOrderId());
        lqw.eq(bo.getRecommendOrderId() != null, TVideoItem::getRecommendOrderId, bo.getRecommendOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getSite()), TVideoItem::getSite, bo.getSite());
        lqw.eq(StringUtils.isNotBlank(bo.getModifyBy()), TVideoItem::getModifyBy, bo.getModifyBy());
        lqw.eq(bo.getLastModifyTime() != null, TVideoItem::getLastModifyTime, bo.getLastModifyTime());
        lqw.eq(StringUtils.isNotBlank(bo.getLanguageCode()), TVideoItem::getLanguageCode, bo.getLanguageCode());
        lqw.eq(StringUtils.isNotBlank(bo.getIcon()), TVideoItem::getIcon, bo.getIcon());
        lqw.eq(StringUtils.isNotBlank(bo.getVideoUrl()), TVideoItem::getVideoUrl, bo.getVideoUrl());
        return lqw;
    }

    /**
     * 新增video
     *
     * @param bo video
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TVideoItemBo bo) {
        TVideoItem add = MapstructUtils.convert(bo, TVideoItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRowId(add.getRowId());
        }
        return flag;
    }

    /**
     * 修改video
     *
     * @param bo video
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TVideoItemBo bo) {
        TVideoItem update = MapstructUtils.convert(bo, TVideoItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TVideoItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除video信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
