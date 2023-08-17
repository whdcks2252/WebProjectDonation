package com.donation.DonationWeb.MemberAuthorityRequest.repository;

import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberAuthorityRequestRepositoryJPA implements MemberAuthorityRequestRepository{
    private final EntityManager em;

    @Override
    public MemberAuthorityRequest save(MemberAuthorityRequest memberAuthorityRequest) {
        em.persist(memberAuthorityRequest);
        return memberAuthorityRequest;
    }

    @Override
    public Optional<MemberAuthorityRequest> findById(Long id) {
        MemberAuthorityRequest findMemberAuthorityRequest = em.find(MemberAuthorityRequest.class, id);
        return Optional.ofNullable(findMemberAuthorityRequest);
    }

    @Override
    public Optional<MemberAuthorityRequest> findByMemberId(Long memberId) {
       return em.createQuery("select mar from MemberAuthorityRequest mar" +
                " where mar.member.id=: memberId")
                .setParameter("memberId",memberId).getResultStream().findAny();
    }

    @Override
    public List<MemberAuthorityRequest> findByPage(Integer page) {
        return em.createQuery("select mar from MemberAuthorityRequest mar" +
                " join fetch mar.member m " +
                " order by mar.createTime DESC",MemberAuthorityRequest.class)
                .setFirstResult((page - 1) * 10)
                .setMaxResults(10)
                .getResultList();
    }
}
