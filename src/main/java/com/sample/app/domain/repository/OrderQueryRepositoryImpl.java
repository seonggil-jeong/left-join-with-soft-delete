package com.sample.app.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.app.domain.core.Order;

import static com.sample.app.domain.core.QOrder.order;
import static com.sample.app.domain.core.QReview.review;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public List<Order> findAllOrders() {
        return queryFactory
                .selectFrom(order)
                .leftJoin(review)
                .on(review.order.id.eq(order.id).and(review.deletedAt.isNull()))
                .fetchJoin()
                .fetch();
    }

}
