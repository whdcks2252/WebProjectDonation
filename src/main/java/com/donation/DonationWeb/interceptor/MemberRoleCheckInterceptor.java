package com.donation.DonationWeb.interceptor;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.status.MemberRole;
import com.donation.DonationWeb.login.session.SessionConst;
import com.donation.DonationWeb.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@Slf4j
public class MemberRoleCheckInterceptor implements HandlerInterceptor {
    private final MemberService memberService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MemberRoleCheckInterceptor 인증 체크 인터셉터 실행");
        HttpSession session = request.getSession();
        Long findLoginId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(!(request.getMethod().equals("POST"))){ //POST 요청이 아닐시 통과
            return true;
        }

        Member findMember = memberService.findById(findLoginId);
        if(findMember.getMemberRole()!= MemberRole.AUTHORIZED) {
            log.info("미인증 멤버 권한{}",findMember.getMemberRole());
            log.info("미인증 멤버 권한");
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("message", "미인증 멤버 권한");
            String result = objectMapper.writeValueAsString(errorResult);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(result);
            return false;
        }
        return true;
    }
}
