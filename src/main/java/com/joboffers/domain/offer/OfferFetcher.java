package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.FetchedOfferResponseDto;

import java.util.List;

public interface OfferFetcher {
    List<FetchedOfferResponseDto> fetchOffers();
}
