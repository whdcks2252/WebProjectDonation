package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.domain.status.MemberAuthorityRequestProcess;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MemberAuthorityRequest extends ObjectTime {
    @Builder
    public MemberAuthorityRequest(String organizationName, String content, Member member,MemberAuthorityRequestProcess memberAuthorityRequestProcess) {
        this.organizationName = organizationName;
        this.content = content;
        this.member = member;
        this.memberAuthorityRequestProcess = memberAuthorityRequestProcess;
    }

    @Id
    @GeneratedValue
    @Column(name = "member_authority_request_num")
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private MemberAuthorityRequestProcess memberAuthorityRequestProcess;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num", nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    public void updateProcess(MemberAuthorityRequestProcess memberAuthorityRequestProcess){
        this.memberAuthorityRequestProcess = memberAuthorityRequestProcess;
    }
}
