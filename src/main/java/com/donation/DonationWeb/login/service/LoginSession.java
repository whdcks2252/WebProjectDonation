package com.donation.DonationWeb.login.service;


import com.donation.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

/*
 *  SessionLoginService는 HttpSession에 직접 의존하지 않습니다.
 *  스프링이 SessionLoginService에 HttpSession 의존성 주입(DI)을 해줍니다.
 *  LoginService 인터페이스를 이용해 Session방식의 LoginService를 구현합니다.
 *  SessionLoginService는 singleton scope을 가지며 HttpSession은 session scope를 가집니다.
 *  이러한 스코프 차이 때문에 Spring이 HttpSession 인스턴스를 동적 프록시로 생성하여 주입합니다.
 *  이러한 기법은 Scoped Proxy라고 합니다.
 * */

@Service
@Transactional(readOnly = true)
@Slf4j
public class LoginSession implements LoginService {
    @Autowired
    public LoginSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    private final HttpSession httpSession;


    @Override
    public void loginUser(Long id) {
        log.info("session={}",httpSession.getId());

        httpSession.setAttribute(SessionConst.LOGIN_MEMBER,id);

        //httpSession.setMaxInactiveInterval(10); 세션시간
        log.info("session getMaxInactiveInterval ={}",httpSession.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(httpSession.getCreationTime()));
        log.info("isNew{}",httpSession.isNew());

    }

    @Override
    public void logoutUser() {
         httpSession.removeAttribute(SessionConst.LOGIN_MEMBER);

        log.info("session={}",httpSession.getId());
        log.info("session={}",httpSession.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(httpSession.getCreationTime()));
        log.info("isNew{}",httpSession.isNew());



    }

    @Override
    public Long getCurrentUser() {
        return (Long)httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
