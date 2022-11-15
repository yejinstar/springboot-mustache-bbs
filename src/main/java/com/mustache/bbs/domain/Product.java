package com.mustache.bbs.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String stock;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

}
