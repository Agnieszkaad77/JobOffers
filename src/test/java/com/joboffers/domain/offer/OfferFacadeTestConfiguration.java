package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.FetchedOfferResponseDto;

import java.util.List;

public class OfferFacadeTestConfiguration {

    private final OfferFetcherTestImpl offerFetcherTestImpl;
    private final OfferRepository offerRepository;

    OfferFacadeTestConfiguration() {
        this.offerFetcherTestImpl = new OfferFetcherTestImpl(List.of(
                new FetchedOfferResponseDto("title1", "company1", "salary1", "1"),
                new FetchedOfferResponseDto("title2", "company2", "salary2", "2"),
                new FetchedOfferResponseDto("title3", "company3", "salary3", "3"),
                new FetchedOfferResponseDto("title4", "company4", "salary4", "4"),
                new FetchedOfferResponseDto("title5", "company5", "salary5", "5"),
                new FetchedOfferResponseDto("title6", "company6", "salary6", "6")
        ));
        this.offerRepository = new OfferRepositoryImpl();
    }

    OfferFacadeTestConfiguration(List<FetchedOfferResponseDto> clientOffers) {
        this.offerFetcherTestImpl = new OfferFetcherTestImpl(clientOffers);
        this.offerRepository = new OfferRepositoryImpl();
    }

    OfferFacade createOfferFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(offerFetcherTestImpl, offerRepository));
    }
}
