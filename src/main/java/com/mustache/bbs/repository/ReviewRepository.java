package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByHospitalId(Integer hospitalId, Pageable pageable);
}
