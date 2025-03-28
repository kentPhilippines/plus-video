package org.dromara.demo.domain;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jerry
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_member_info", autoResultMap = true)
public class MemberInfo {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Long rowId;
    /**
     * 唯一 = 会员账号+站点编码 （memberRowId+site）
     */
    private String memberId;
    /**
     * 会员层级
     */
    private Integer levelId;
    /**
     * 用户密码
     */
    private String memberPwd;
    /**
     * 交易密码
     */
    private String transPwd;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 注册设备追踪号
     */
    private String traceId;
    /**
     * 登录真实地址
     ****/
    private String registerAddress;
    /*** 邀请码*/
    private String invitationCode;
    /*** 代理链条*/
    private String linkCode;
    /**
     * 注册版本 1APP2横版3PC4H5 5其他
     */
    private String channelId;
    /**
     * 来源|下载地址
     */
    private String downloadSite;
    /**
     * 用户端.站点
     */
    private String site;
    /**
     * 是否冻结 0:禁用 1:启用
     */
    private Integer frozen;
    /**
     * 首充时间
     */
    private Long depositFirstDate;
    /**
     * 首充金额
     */
    private BigDecimal depositFirstAmount;
    /**
     * 首充金额币种
     */
    private String depositFirstAmountCurrency;
    /**
     * 总存款
     */
    private BigDecimal depositAmount;
    /**
     * 存款次数
     */
    private Integer depositNumber;
    /**
     * 总提款
     */
    private BigDecimal withdrawAmount;
    /**
     * 提款次数
     */
    private Integer withdrawNumber;
    /*** 登陆次数*/
    private Integer loginTimes;
    /**
     * 最后登录时间
     */
    private Long loginDate;
    /**
     * 客户端限制
     */
    @TableField(typeHandler = Fastjson2TypeHandler.class, value = "client_restrictions")
    private List<Integer> clientRestrictions;
    /**
     * 最后充值时间
     */
    private Long lastDepositDate;
    /**
     * 最后提现时间
     */
    private Long lastWithdrawDate;

    /**
     * 单笔最大充值
     */
    private BigDecimal maxDeposit;

    /**
     * 注册时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long updateTime;
    /**
     * 备注
     ***/
    private String note;
    /**
     * 性别 男:1 女:0
     */
    private Integer sex;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 姓名
     */
    private String memberName;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 区号
     */
    public String areaCode;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 生日
     */
    private Long birthday;
    /**
     * 收货地址
     */
    private String giftAddr;
    /**
     * 邮箱
     */
    private String emailId;
    /**
     * 是否是代理 0不是1是
     */
    private Integer agent;
    /**
     * 是否是回归用户 0不是1是
     */
    private Integer isReturn;
    /**
     * 联系方式 json
     */
    private String contactInfoJson;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
