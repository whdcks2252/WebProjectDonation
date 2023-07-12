package com.donation.DonationWeb.member.repository;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImp implements MemberRepository {

    private final EntityManager em;

    //맴버 생성
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        Member member = findById(memberId).orElseThrow(() -> new IllegalArgumentException("not found : " + memberId));
        member.updateValidate(updateParam);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    @Override
    public void delete(Long memberId) {
        Member member = findById(memberId).orElseThrow(() -> new IllegalArgumentException("not found : " + memberId));
        em.remove(member);
    }

    @Override
    public Optional<Member> findMemberIDAndPassword(LoginMemberRequest loginMemberRequest){

        return em.createQuery("select m from Member m where m.memberId = : loginId and m.password =:password")
                .setParameter("loginId", loginMemberRequest.getLoginId()).setParameter("password",loginMemberRequest.getPassWord())
                .getResultList().stream().findAny();
    }



}
