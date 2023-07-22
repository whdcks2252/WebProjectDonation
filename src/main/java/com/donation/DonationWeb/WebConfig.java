package com.donation.DonationWeb;

import com.donation.DonationWeb.argumentresolver.LoginMemberIdArgumentResolver;
import com.donation.DonationWeb.interceptor.CheckUserAccessInterceptor;
import com.donation.DonationWeb.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/api/post/**","/api/user/*")
                .excludePathPatterns("/api/user/login","/api/user/join","/api/user/idCheck","/api/user/nickNameCheck","/api/post/list"
                        ,"/api/post/list/{categoryId}","/api/post/{postId}");

        registry.addInterceptor(new CheckUserAccessInterceptor())
                .order(2)
                .addPathPatterns("/api/user/{id}").excludePathPatterns("/api/user/idCheck","/api/user/nickNameCheck","/api/user/logout","/api/user/login","/api/user/join");


    }



    //argumentResolvers등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberIdArgumentResolver());

    }
}
