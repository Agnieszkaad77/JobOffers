package com.joboffers.domain.offer;

import lombok.Builder;

@Builder
record Offer(
        String id,
        String company,
        String position,
        String salary,
        String url) {
}
