package com.sample.app.domain.core;

import java.util.ArrayList;
import java.util.List;

import com.sample.common.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDER_INFO")
@Entity
public class Order extends BaseEntity {

    private String productName;
    private Integer amount;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Builder
    public Order(final String productName, final Integer amout) {
        this.productName = productName;
        this.amount = amout;
        this.reviews = new ArrayList<>();

    }
}
