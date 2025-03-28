package org.dromara.demo.controller;



import cn.hutool.crypto.digest.DigestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.dto.MemberCacheInfoDTO;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.demo.domain.vo.MemberInfoVo;
import org.dromara.demo.domain.vo.MemberVo;
import org.dromara.demo.service.IMemberService;
import org.redisson.api.RedissonClient;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final RedissonClient redissonClient;
    private final IMemberService memberService;

    /**
     * 新增会员
     *
     * @param param 会员信息
     * @return 会员信息MemberVo
     */
    @PostMapping("/mc/newMember")
    public MemberInfoVo newMember(@RequestBody MemberVo param, HttpServletRequest request) {
        Assert.notNull(param, BusinessStatusEnum.CONTACT_US.getDesc());
        String authParamStr = request.getHeader(HttpHeaders.AUTHORIZATION);
        Assert.notNull(authParamStr, BusinessStatusEnum.CONTACT_US.getDesc());
        String traceId = MDC.get(StringUtils.TRACE_ID);
        String authType = RedisUtils.getLoginAuthType(traceId, redissonClient).get();
        String xAuthToken = request.getHeader(StringUtils.HttpParamName.X_AUTH_TOKEN);
        //第二位是注册验证方式 第三位是登陆验证方式
        String authStr = DigestUtil.md5Hex(xAuthToken.replaceAll(authType.substring(authType.length() - StringUtils.Y), StringUtils.EMPTY));
        log.info("签名参数{}实际参数{}", authParamStr, authStr);
        Assert.isTrue(authStr.equals(authParamStr), BusinessStatusEnum.CONTACT_US.getDesc());
        //验证用户名
        Assert.isTrue(StringUtils.isValidUsername(param.getMemberId()), BusinessStatusEnum.E262.getDesc());
        MemberInfoVo memberInfoVo = memberService.newMember(param);
        RedisUtils.getMemberInfo(memberInfoVo.getRowId(), redissonClient)
            .set(new MemberCacheInfoDTO().setDownloadSite(memberInfoVo.getDownloadSite()).setLevelId(memberInfoVo.getLevelId()).setCreateTime(memberInfoVo.getCreateTime()));
        // pushServiceSupport.bind(memberInfoVo, site, PublicUtil.getChannelId());
        // 统计有效注册
        // visitStaticService.validRegister(new DownloadSiteStatic().setSite(PublicUtil.getSite()).setIp(PublicUtil.getRemoteIp(request)).setValidInstall(1).setDomain(memberInfoVo.getDownloadSite()).setAppUserNum(1));
        return memberInfoVo;
    }
}
