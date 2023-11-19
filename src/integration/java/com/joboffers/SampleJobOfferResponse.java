package com.joboffers;

public interface SampleJobOfferResponse {

    default String bodyWithNoOffers() {
        return "[]";
    }
}
