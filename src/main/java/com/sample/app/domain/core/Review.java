package com.sample.app.domain.core;

import com.sample.common.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "REVIEW")
public class Review extends BaseEntity {
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Builder
    public Review(final String content, final Order order) {
        this.content = content;
        this.order = order;
    }

    public Review delete() {
        this.setDeletedAt();
        return this;
    }
}
