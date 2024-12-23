package com.sample.app.domain.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.Import;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.app.domain.core.Order;
import com.sample.app.domain.core.Review;
import com.sample.app.domain.repository.jpa.OrderJpaRepository;
import com.sample.app.domain.repository.jpa.ReviewJpaRepository;
import com.sample.common.config.QueryDslConfig;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@DataJpaTest
@Import(QueryDslConfig.class)
@RequiredArgsConstructor
public class OrderQueryRepositoryImplTest {

        @Autowired
        private EntityManager entityManager;
        private JPAQueryFactory queryFactory;

        @Autowired
        private OrderJpaRepository orderJpaRepository;

        @Autowired
        private ReviewJpaRepository reviewJpaRepository;

        @Autowired
        private OrderQueryRepositoryImpl target;

        private Order order;
        private Order orderWithReview;
        private Order orderWithDeletedReview;
        private Order orderWithDeletedReviewAndNotDeletedReview;

        private Review review;
        private Review deletedReview;
        private Review deletedReview2;
        private Review review2;

        @BeforeEach
        void init() {
                queryFactory = new JPAQueryFactory(entityManager);
                target = new OrderQueryRepositoryImpl(queryFactory);
                createProduct();
                createReview();

        }

        void createProduct() {
                order = orderJpaRepository.save(Order.builder()
                                .productName("name")
                                .amout(1000).build());
                orderWithReview = orderJpaRepository.save(Order.builder()
                                .productName("name")
                                .amout(1000).build());
                orderWithDeletedReview = orderJpaRepository.save(Order.builder()
                                .productName("name")
                                .amout(1000).build());

                orderWithDeletedReviewAndNotDeletedReview = orderJpaRepository.save(Order.builder()
                                .productName("name")
                                .amout(1000).build());
        }

        void createReview() {
                review = reviewJpaRepository.save(Review.builder()
                                .content("content")
                                .order(orderWithReview)
                                .build());

                deletedReview = reviewJpaRepository.save(Review.builder()
                                .content("content")
                                .order(orderWithDeletedReview)
                                .build().delete());

                deletedReview2 = reviewJpaRepository.save(Review.builder()
                                .content("content")
                                .order(orderWithDeletedReviewAndNotDeletedReview)
                                .build().delete());
                review2 = reviewJpaRepository.save(Review.builder()
                                .content("content")
                                .order(orderWithDeletedReviewAndNotDeletedReview)
                                .build());

        }

        @Test
        @DisplayName("found all orders that has and not has review")
        void testFindAllProduct() {
                // when
                List<Order> expectedOrders = target.findAllOrders();

                assertThat(expectedOrders.size()).isEqualTo(4);
        }
}
