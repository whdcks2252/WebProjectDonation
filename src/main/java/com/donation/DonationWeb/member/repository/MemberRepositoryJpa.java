package com.donation.DonationWeb.member.repository;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryJpa implements MemberRepository {

    private final EntityManager em;

    //맴버 생성
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        Member member = findById(memberId).orElseThrow(() -> new UserException("not found : " + memberId));
        member.updateValidate(updateParam);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    @Override
    public Optional<Member> findUserPosts(Long memberId, Integer page) {
        return em.createQuery("select m from Member m" +
                        " join fetch m.posts p"+
                        " join fetch p.categorie c"+
                        " where p.member.id =: memberId " +
                        " order by p.createTime DESC " ,Member.class)
                .setParameter("memberId",memberId)
                .setFirstResult((page-1)*10).setMaxResults(10)
                .getResultList().stream().findAny();


    }
    @Override
    public Optional<Member> findUserInterestPosts(Long memberId, Integer page) {
        return em.createQuery("select m from Member m" +
                        " join fetch m.interestPosts ip"+
                        " join fetch ip.post p"+
                        " join fetch p.categorie pc"+
                        " where ip.member.id =: memberId " +
                        " order by ip.createTime DESC " ,Member.class)
                .setParameter("memberId",memberId)
                .setFirstResult((page-1)*10).setMaxResults(10)
                .getResultList().stream().findAny();


    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    @Override
    public void delete(Long memberId) {
        Member member = findById(memberId).orElseThrow(() -> new UserException("not found : " + memberId));
        em.remove(member);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId){

        return em.createQuery("select m from Member m where m.memberId = : loginId",Member.class)
                .setParameter("loginId", memberId)
                .getResultList().stream().findAny(); //null일수도 있으므로
    }

    @Override
    public  Optional<Member> idCheck(String id) {
      return  em.createQuery("select m from Member m where m.memberId= :id",Member.class)
              .setParameter("id",id)
              .getResultList().stream().findAny();
    }

    @Override
    public  Optional<Member> nickNameCheck(String nickName) {
        return  em.createQuery("select m  from Member m where m.memberNickname= :nickName",Member.class)
                .setParameter("nickName",nickName)
                .getResultList().stream().findAny();
    }
}
