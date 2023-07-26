package com.donation.DonationWeb;

import com.donation.DonationWeb.argumentresolver.LoginMemberIdArgumentResolver;
import com.donation.DonationWeb.filter.CorsFilter;
import com.donation.DonationWeb.interceptor.CheckUserAccessInterceptor;
import com.donation.DonationWeb.interceptor.LoginCheckInterceptor;
import com.donation.DonationWeb.interceptor.PostLoginCheckInterceptor;
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
public class WebConfig implements WebMvcConfigurer {

    //인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns( "/api/user/*")
                .excludePathPatterns("/api/user/login", "/api/user/join", "/api/user/idCheck", "/api/user/nickNameCheck");
        registry.addInterceptor(new CheckUserAccessInterceptor())
                .order(3)
                .addPathPatterns("/api/user/{id}/**").excludePathPatterns("/api/user/idCheck", "/api/user/nickNameCheck", "/api/user/logout", "/api/user/login", "/api/user/join");
        registry.addInterceptor(new PostLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/api/post/**");


    }
    //argumentResolvers등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberIdArgumentResolver());

    }
   //cros 메서드
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:9090");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE) //빈의 우선 순위 저장 우선순위 1순위
    public FilterRegistrationBean crosFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CorsFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}


