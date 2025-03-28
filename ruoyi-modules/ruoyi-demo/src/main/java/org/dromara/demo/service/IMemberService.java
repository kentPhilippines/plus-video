package org.dromara.demo.service;


import org.dromara.demo.domain.vo.MemberInfoVo;
import org.dromara.demo.domain.vo.MemberVo;

/**
 * @author jerry
 */
public interface IMemberService {

    /**
     * 新增会员
     *
     * @param param 会员信息
     * @return 会员信息MemberVo
     */
    MemberInfoVo newMember(MemberVo param);
}
