package org.dromara.demo.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class MemberInfoVo {

    private String memberId;
    private Long rowId;
    /**
     * 会员层级
     */
    private Integer levelId;
    /**
     * 会员VIP等级
     */
    private Integer vipId;
    /**
     * 用户密码
     */
    private String memberPwd;
    /**
     * 交易密码
     */
    private String busiPwd;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 注册设备追踪号
     */
    private String traceId;
    /**
     * 设备信息
     */
    private String device;
    /**
     * 设备类型[Android,iOS,HarmongOS]
     */
    private String deviceType;
    /**
     * 登录真实地址
     ****/
    private String registerAddress;
    /*** 邀请码*/
    private String invitationCode;
    /**
     * 注册终端 A:PC B:IOS C:安卓 D:H5 E:其他
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
    @TableField(typeHandler = Fastjson2TypeHandler.class, value = "client_restrictions")
    private List<Integer> clientRestrictions;
//    /** 最后登录Ip */
//    private String loginIp;
//    /** 最后登录Ip地址 */
//    private String loginAddress;
    /**
     * 最后登录设备追踪号
     */
    private String loginTraceid;
    /** 最后登录设备 */
//    private String loginDevice;
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
     * 币种 CNY:人民币 USDT:USDT USD:美元 SGD:新币   VND:越南盾 JPY 日元 KRW 韩元 IDR  印尼盾  MYR 马来西亚币
     * BRL:巴西雷亚尔 MXN:墨西哥比索
     */
    private String currency;
    /**
     * 人民币账户金额
     */
    private BigDecimal balanceCny;
    /**
     * USDT账户金额
     */
    private BigDecimal balanceUsdt;
    /**
     * 美元账户金额
     */
    private BigDecimal balanceUsd;
    /**
     * 新加坡元账户金额
     */
    private BigDecimal balanceSgd;
    /**
     * 越南盾账户金额
     */
    private BigDecimal balanceVnd;

    /**
     * 日元账户金额
     */
    private BigDecimal balanceJpy;
    /**
     * 韩元账户金额
     */
    private BigDecimal balanceKrw;
    /**
     * 印尼盾账户金额
     */
    private BigDecimal balanceIdr;
    /**
     * 马来西亚币账户金额
     */
    private BigDecimal balanceMyr;
    /**
     * 巴西雷亚尔
     */
    private BigDecimal balanceBrl;
    /**
     * 墨西哥比索
     */
    private BigDecimal balanceMxn;
    /**
     * 印度卢比
     */
    private BigDecimal balanceInr;
    /**
     * 菲律宾比索
     */
    private BigDecimal balancePhp;


    //投注积分
    private BigDecimal betAmount;
    //充值积分
    private BigDecimal rechargeAmount;

    //上次VIP晋级等级
    private Integer lastObtainedVipId;

    //当前积分
    private BigDecimal currentScore;
    /**
     * 累计积分
     */
    private BigDecimal score;
    /**
     * 是否是代理 0不是1是
     */
    private Integer agent;
    /*** 代理链条*/
    private String linkCode;
    /**
     * 是否是回归用户 0不是1是
     */
    private Integer isReturn;
    /**
     * 联系方式 json
     */
    private String contactInfoJson;
}
