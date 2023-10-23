package com.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(
        String company,
        String position,
        String salary,
        String url) {
}
