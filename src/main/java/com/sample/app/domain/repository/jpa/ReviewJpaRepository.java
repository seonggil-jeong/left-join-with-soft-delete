
package com.sample.app.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.domain.core.Review;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

}