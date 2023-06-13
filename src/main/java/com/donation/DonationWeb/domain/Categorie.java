package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categorie {
    @Id @GeneratedValue
    @Column(name= "category_id")
    private Long id;

    private String name;

    public Categorie(String name) {
        this.name = name;
    }
}
