package com.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record FetchedOfferResponseDto(
        String title,
        String company,
        String salary,
        String url) {
}
