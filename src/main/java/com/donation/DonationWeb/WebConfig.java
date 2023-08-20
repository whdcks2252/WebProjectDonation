package com.donation.DonationWeb;

import com.donation.DonationWeb.argumentresolver.LoginMemberIdArgumentResolver;
import com.donation.DonationWeb.filter.CorsFilter;
import com.donation.DonationWeb.interceptor.*;
import com.donation.DonationWeb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MemberService memberService;
    //인터셉터 등록
    @Override

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/api/user/*", "/kakaopay/**")
                .excludePathPatterns("/api/user/login", "/api/user/join", "/api/user/idCheck", "/api/user/nickNameCheck", "/kakaopay/success");
        registry.addInterceptor(new CheckUserAccessInterceptor())
                .order(4)
                .addPathPatterns("/api/user/{id}/**").excludePathPatterns("/api/user/idCheck", "/api/user/nickNameCheck", "/api/user/logout", "/api/user/login", "/api/user/join");
        registry.addInterceptor(new PostLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/api/post/**","/api/volunteerPost/**","/api/reviewPost/**");
        registry.addInterceptor(memberRoleCheckInterceptor())
                .order(3)
                .addPathPatterns("/api/post");
        registry.addInterceptor(adminLoginCheckInterceptor())
                .order(5)
                .addPathPatterns("/api/admin/**").excludePathPatterns("/api/admin/login", "/api/admin/logout");
        registry.addInterceptor(postExpirationCheckInterceptor())
                .order(6)
                .addPathPatterns("/api/reviewPost/**").excludePathPatterns("/api/reviewPost/{reviewPostId}/comment");



    }

    @Bean
    public AdminLoginCheckInterceptor adminLoginCheckInterceptor(){
        return new AdminLoginCheckInterceptor();
    }

    @Bean
    public PostExpirationCheckInterceptor postExpirationCheckInterceptor(){
        return new PostExpirationCheckInterceptor();
    }


    //argumentResolvers등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberIdArgumentResolver());

    }

    //cros 메서드
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:9090", "http://localhost:3030")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE) //빈의 우선 순위 저장 우선순위 1순위
    public FilterRegistrationBean crosFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CorsFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean// MemberRoleInterceptor 빈등록
    public MemberRoleCheckInterceptor memberRoleCheckInterceptor() {
        return new MemberRoleCheckInterceptor(memberService);
    }
}


