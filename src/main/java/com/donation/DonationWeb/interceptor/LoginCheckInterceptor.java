package com.donation.DonationWeb.interceptor;

import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.login.session.SessionConst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        log.info("http메서드={}", request.getMethod());
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("message", "미인증 사용자 요청");
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
