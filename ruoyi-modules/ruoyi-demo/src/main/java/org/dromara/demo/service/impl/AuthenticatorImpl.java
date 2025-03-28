package org.dromara.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.demo.domain.vo.MemberVo;
import org.dromara.demo.service.IAuthenticator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticatorImpl implements IAuthenticator {


    /**
     * 验证
     *
     * @param authType 验证方式 0不验证 1:蓝盾验证码,2:网易验证码 3:短信
     * @param param    验证参数
     */
    @Override
    public void verify(int authType, MemberVo param) {
        // 蓝盾
        if (authType == StringUtils.Y) {
            //TODO
            //网易
        } else if (authType == StringUtils.Number.N2) {
            //TODO
            //短信验证
        } else if (authType == StringUtils.Number.N3) {
            //TODO
        }
    }
}
