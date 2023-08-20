package com.donation.DonationWeb.interceptor;

import com.donation.DonationWeb.domain.status.PostStatus;
import com.donation.DonationWeb.login.session.SessionConst;
import com.donation.DonationWeb.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PostExpirationCheckInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    private PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("PostExpirationCheckInterceptor인터셉터 실행 {}", requestURI);
        log.info("http메서드={}", request.getMethod());

        if(!(request.getMethod().equals("POST"))){ //POST 요청이 아닐시 통과
            return true;
        }

        Long postId = Long.parseLong(request.getRequestURI().replaceAll("[^0-9]", ""));
        log.info("test={}", request.getRequestURI().replaceAll("[^0-9]",""));

        if(!PostStatus.EXPIRATION.equals(postService.findById(postId).getPostStatus()))
        {
            log.info("게시글이 만료되지 않았습니다");
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("message", "게시글이 만료되지 않았습니다");
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
