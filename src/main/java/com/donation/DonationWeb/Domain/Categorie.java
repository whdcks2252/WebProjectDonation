package com.donation.DonationWeb.Domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Categorie {
    @Id @GeneratedValue
    @Column(name= "category_id")
    private Long id;

    private String name;

}
