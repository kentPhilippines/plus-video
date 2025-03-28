package org.dromara.demo.domain.vo;


import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.dromara.demo.domain.MemberInfo;

/**
 * @author jerry
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class MemberVo extends MemberInfo {
    /**
     * 验证参数
     */
    private String validateCode;
    /**
     * 短信验证参数
     */
    private String smsCode;

    /**
     * 登陆时的语言
     */
    public String languageCode;
    /**
     * 0:账号密码 1:手机号码  2纸飞机3脸书4X公司5Line6谷歌7微信
     */
    public Integer requestMethod;

    /**
     * 旧密码
     */
    public String oldPwd;

    /**
     * 会员当前钱包 CNY:人民币 USDT:USDT USD:美元 SGD:新币   VND:越南盾 JPY 日元 KRW 韩元 IDR  印尼盾  MYR 马来西亚币 BRL:巴西雷亚尔 MXN:墨西哥比索
     */
    private String currency;

    /**
     * 设备类型[Android,iOS,HarmongOS]
     */
    private String deviceType;

    private String tenantId;
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
