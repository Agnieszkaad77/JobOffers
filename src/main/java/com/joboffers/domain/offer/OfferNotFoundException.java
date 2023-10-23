package com.joboffers.domain.offer;

public class OfferNotFoundException extends RuntimeException {

    private String offerId;

    public OfferNotFoundException(String offerId) {
        super(String.format("Offer with id: %s not found!",  offerId));
        this.offerId = offerId;
    }
}
