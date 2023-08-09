package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends ObjectTime {

    @Builder
    public Member(String memberId,String password, String memberNickname, String memberName, String memberPhone,String email,ServiceAgreement svcAge,Address address ) {
        this.memberId = memberId;
        this.password = password;
        this.memberNickname = memberNickname;
        this.memberName=memberName;
        this.memberPhone = memberPhone;
        this.email = email;
        this.svcAge=svcAge;
        this.address = address;
    }

    @Id
    @GeneratedValue
    @Column(name="member_num")
    private Long id;
    //멤버 아이디
    @Column(name = "member_id")
    private String memberId;
    //멤버 닉네임
    @Column(name = "member_nickname") //나중에 유니크 조건
    private String memberNickname;
    //멤버 비밀번호
    @Column(name = "password")
    private String password;
    //멤버 이름
    @Column(name = "member_name")
    private String memberName;
    //멤버 전화번호
    @Column(name = "member_phone")
    private String memberPhone;
    //멤버 이메일
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "member")
    private List<InterestPost> interestPosts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    //주소
    @Embedded
    private Address address;


    //서비스 이용약관
    @Enumerated(EnumType.STRING)
    private ServiceAgreement svcAge;

    //업데이트
    public   void updateValidate(MemberUpdateDto memberUpdateDto) {
            if(ObjectUtils.isEmpty(memberUpdateDto))
                throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
            if (memberUpdateDto.getMemberNickname() != null)
                this.memberNickname = memberUpdateDto.getMemberNickname();
            if (memberUpdateDto.getMemberPhone() != null)
                this.memberPhone = memberUpdateDto.getMemberPhone();
            if (memberUpdateDto.getEmail() != null)
                this.email = memberUpdateDto.getEmail();
            if (memberUpdateDto.getCity() != null)
                if (memberUpdateDto.getStreet() != null)
                    if (memberUpdateDto.getStreet() != null)
                        this.address = new Address(memberUpdateDto.getCity(), memberUpdateDto.getStreet(), memberUpdateDto.getZipcode());
                                    }


}
