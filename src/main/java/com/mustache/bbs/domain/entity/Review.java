package com.mustache.bbs.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name="hospital_id")
    private Integer hospitalId;*/
    private String title;
    private String content;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public static Review of(Hospital hospital, String title, String content, String userName) {
        Review review = new Review();
        review.setHospital(hospital);
        review.setTitle(title);
        review.setContent(content);
        review.setUserName(userName);
        return review;
    }
}
