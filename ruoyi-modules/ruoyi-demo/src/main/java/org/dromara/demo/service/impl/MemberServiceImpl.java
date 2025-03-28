package org.dromara.demo.service.impl;


import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.dto.LoginAndRegisterSettingDTO;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.utils.ServletUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.demo.domain.vo.MemberInfoVo;
import org.dromara.demo.domain.vo.MemberVo;
import org.dromara.demo.service.IAuthenticator;
import org.dromara.demo.service.IMemberService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author jerry
 */
@Slf4j
@Component
@AllArgsConstructor
public class MemberServiceImpl implements IMemberService {
    private final RedissonClient redissonClient;
    private final IAuthenticator authenticatorImpl;

    /**
     * 新增会员
     *
     * @param param 会员信息
     * @return 会员信息
     */
    @Override
    public MemberInfoVo newMember(MemberVo param) {
        Assert.isTrue(StrUtil.isNotBlank(param.getMemberId()), BusinessStatusEnum.ABNORMALITY_OF_ENTRY.getDesc());
        LoginAndRegisterSettingDTO loginAndRegisterSetting = RedisUtils.getLoginAndRegisterSetting(param.getTenantId(), redissonClient);
        Assert.isTrue(loginAndRegisterSetting.getRegisterEnable() == StringUtils.Y, BusinessStatusEnum.LIMIT_SAME_IP.getDesc());
        Assert.isTrue(StrUtil.isNotBlank(param.getMemberId()) || loginAndRegisterSetting.getRegisterAccountType() == StringUtils.Y, BusinessStatusEnum.ABNORMALITY_OF_ENTRY.getDesc());
        if (loginAndRegisterSetting.getRegisterAccountType() == StringUtils.Y) {
            Assert.isTrue(StrUtil.isAllNotBlank(param.getMemberId()), BusinessStatusEnum.ABNORMALITY_OF_ENTRY.getDesc());
            param.setMemberId(param.getTelephone());
        }
        if (loginAndRegisterSetting.getPhone().isEnable() && loginAndRegisterSetting.getPhone().isRequired() || StrUtil.isNotBlank(param.getTelephone())) {
            StringUtils.isMobileNumber(param.getAreaCode(), param.getTelephone());
            if (param.getRequestMethod() == StringUtils.Y) {
                Assert.isTrue(StrUtil.isNotBlank(param.getSmsCode()), BusinessStatusEnum.INVALID_VERIFICATION_CODE.getDesc());
                RBucket<String> rBucket = RedisUtils.verifyCodeCache(param.getAreaCode() + param.getTelephone(), redissonClient);
                String verifyCode = rBucket.get();
                Assert.isTrue(StrUtil.isNotBlank(verifyCode) && verifyCode.equals(param.getSmsCode()), BusinessStatusEnum.INVALID_VERIFICATION_CODE.getDesc());
                rBucket.delete();
            }
        }
        if (loginAndRegisterSetting.getInvitationCode().isEnable() && loginAndRegisterSetting.getInvitationCode().isRequired()) {
            Assert.isTrue(StrUtil.isNotBlank(param.getInvitationCode()), BusinessStatusEnum.ABNORMALITY_OF_ENTRY.getDesc());
        }
        if (loginAndRegisterSetting.getMemberName().isEnable() && loginAndRegisterSetting.getMemberName().isRequired()) {
            Assert.isTrue(StrUtil.isNotBlank(param.getMemberName()), BusinessStatusEnum.ABNORMALITY_OF_ENTRY.getDesc());
        }
        String traceId = MDC.get(StringUtils.TRACE_ID);
        String registerAuthType = RedisUtils.getLoginAuthType(traceId, redissonClient).get();
        int authType = Integer.parseInt(registerAuthType.substring(StringUtils.Y, registerAuthType.length() - StringUtils.Y));
        log.info("新增验证方式{}实际{}参数{}", registerAuthType, authType, param);
        authenticatorImpl.verify(authType, param);
        RedisUtils.sameIpCheck(ServletUtils.getClientIP(), param.getTenantId(), loginAndRegisterSetting, redissonClient);
       /* PublicUtil.validState(
            loginAndRegisterSetting.getLoginIpLimit() == GlobalConstant.N ||
                adminImpl.onlineMemberCount(PublicUtil.getRemoteIp(PublicUtil.getRequest())) <= loginAndRegisterSetting.getLoginIpLimit(), ExceptionEnum.E165);
        SysLevelInfo sysLevelInfo = sysLevelImpl.selectDefaultSysLevel(param.getSite());
        RedissonHelper.setLockName(RedisKeyConstants.getMemberInfoLockKey(param.getRowId()));
        MemberInfoVo memberInfoVo = memberImpl.newMember(param.setLevelId(sysLevelInfo.getSysLevelId()), param.getCurrency(), param.getDeviceType());
        OperatorHelper.getOperatorLog().setUserId(memberInfoVo.getMemberId()).setChannelId(memberInfoVo.getChannelId()).setDownloadSite(memberInfoVo.getDownloadSite());

        OperatorHelper.getOperatorLog().setUserRowId(memberInfoVo.getRowId().toString());
        Operator operator = new Operator().setMemberId(memberInfoVo.getMemberId()).setOperatorType(
                memberInfoVo.getAgent() == GlobalConstant.Y ? GlobalConstant.OperatorType.agent : GlobalConstant.OperatorType.member).setNickName(memberInfoVo.getNickName())
            .setMemberRowId(memberInfoVo.getRowId())
            .setCurrency(memberInfoVo.getCurrency())
            .setChannelId(PublicUtil.getChannelId()).setSite(memberInfoVo.getSite()).setDownloadSite(memberInfoVo.getDownloadSite())
            .setSysLevelId(memberInfoVo.getLevelId().toString()).setVipId(memberInfoVo.getVipId().toString());
        //退出上一次登录的会话
        RedisKeyConstants.loginCache(operator, PublicUtil.getRequest().getSession(), redissonClient);
        return memberInfoVo;*/
        return null;
    }


}
