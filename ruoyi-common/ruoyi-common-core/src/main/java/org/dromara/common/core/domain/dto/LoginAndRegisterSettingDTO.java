package org.dromara.common.core.domain.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jerry
 */
@Data
@Accessors(chain = true)
public class LoginAndRegisterSettingDTO {

    /**
     * 是否容许注册  0:关闭 1:开启
     */
    public Integer registerEnable = 0;
    /**
     * 同一IP注册数   0:不限制  N:数量
     */
    public Integer registerIpLimit = 3;
    /**
     * 同一IP登陆数   0:不限制  N:数量
     */
    public Integer loginIpLimit = 3;
    /**
     * 会员真实姓名
     */
    public MyFieldDTO memberName = new MyFieldDTO().setEnable(false).setRequired(false);
    /**
     * 手机号码
     */
    public MyFieldDTO phone = new MyFieldDTO().setEnable(false).setRequired(false);
    /**
     * 邀请码
     */
    public MyFieldDTO invitationCode = new MyFieldDTO().setEnable(false).setRequired(false);
    /**
     * 注册验证方式 0不验证 1:蓝盾验证码,2:网易验证码 3:短信
     */
    public List<Integer> registerAuthType = new ArrayList<>() {{
        add(StringUtils.N);
    }};
    /**
     * 登录验证方式 0不验证 1:蓝盾验证码,2:网易验证码 3:短信
     */
    public List<Integer> loginAuthType = new ArrayList<>() {{
        add(StringUtils.N);
    }};

    /**
     * 注册账户类型，0普通账号 1手机号
     */
    public Integer registerAccountType = 0;

    /**
     * 连续登陆失败次数 0:不限制 ，n:N
     */
    public Integer loginWrongLimit = 3;
    /**
     * 注册方式1账号密码 2手机号码
     */
    public List<Integer> registerMethod = new ArrayList<>() {{
        add(StringUtils.Y);
    }};
    /**
     * 登陆方式1账号密码 2手机号码
     */
    public List<Integer> loginMethod = new ArrayList<>() {{
        add(StringUtils.Y);
    }};


    /**
     * 系统验证方式 0不验证 1:蓝盾验证码,2:网易验证码 3:短信
     */
    public Integer sysAuthType = 2;

    /**
     * 是否开启一键注册  1开启  0关闭
     */
    public Integer simpleNewNumber = 1;
}
