package com.mustache.bbs.repository;

import com.mustache.bbs.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /* 수원시 피부과
    SELECT * FROM `likelion-db`.nation_wide_hospitals
    where full_address like '%수원시%' and hospital_name like '%피부과%';
    */
}
