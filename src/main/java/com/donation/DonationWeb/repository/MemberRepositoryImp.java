package com.donation.DonationWeb.repository;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    //맴버 불러오기
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select p from Member p",Member.class).getResultList();
    }

    @Override
    public void delete(long id) {

    }

}
