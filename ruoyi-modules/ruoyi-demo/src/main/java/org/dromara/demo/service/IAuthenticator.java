package org.dromara.demo.service;


import org.dromara.demo.domain.vo.MemberVo;

/**
 * @author jerry
 */
public interface IAuthenticator {


    /**
     * 验证
     *
     * @param authType 验证方式 0不验证 1:蓝盾验证码,2:网易验证码
     * @param param    验证参数
     */
    void verify(int authType, MemberVo param);
}
