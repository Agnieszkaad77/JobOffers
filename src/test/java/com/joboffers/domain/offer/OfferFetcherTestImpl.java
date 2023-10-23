package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.FetchedOfferResponseDto;

import java.util.List;

public class OfferFetcherTestImpl implements OfferFetcher {

    List<FetchedOfferResponseDto> offers;

    OfferFetcherTestImpl(List<FetchedOfferResponseDto> offers) {
        this.offers = offers;
    }

    @Override
    public List<FetchedOfferResponseDto> fetchOffers() {
        return offers;
    }
}
