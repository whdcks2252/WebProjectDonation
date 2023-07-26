package com.donation.DonationWeb.interceptor;

import com.donation.DonationWeb.argumentresolver.Login;
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
public class CheckUserAccessInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("CheckUserAccessInterceptor인터셉터 실행 {}", requestURI);
        log.info("http메서드={}", request.getMethod());
        HttpSession session = request.getSession();

        Long pathId = Long.parseLong(request.getRequestURI().replaceAll("[^0-9]", "")); //세션 id랑 경로 자원의 아이디랑 유효성 검사
        log.info("test={}", request.getRequestURI().replaceAll("[^0-9]",""));

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
        else if(!session.getAttribute(SessionConst.LOGIN_MEMBER).equals(pathId))
        {
            log.info("사용자의 자원이 아닙니다");
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("message", "사용자의 자원이 아닙니다");
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
