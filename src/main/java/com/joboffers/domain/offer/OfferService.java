package com.joboffers.domain.offer;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class OfferService {

    private final OfferFetcher offerFetcher;
    private final OfferRepository offerRepository;

    List<Offer> fetchAllOffersAndSaveIfNotExist() {
        List<Offer> fetchedOffers = fetchOffers();
        List<Offer> filteredOffers = filterNonExistingOffers(fetchedOffers);
        try {
            return offerRepository.saveAll(filteredOffers);
        } catch (OfferDuplicateException offerDuplicateException) {
            throw new OfferSavingException(offerDuplicateException.getMessage(), fetchedOffers);
        }
    }

    private List<Offer> filterNonExistingOffers(List<Offer> fetchedOffers) {
        return fetchedOffers.stream()
                .filter(offer -> !offer.url().isEmpty())
                .filter(offer -> !offerRepository.existsByUrl(offer.url()))
                .toList();
    }

    private List<Offer> fetchOffers() {
        return offerFetcher.fetchOffers()
                .stream()
                .map(OfferMapper::mapFromFetchedOfferDtoToOfferEntity)
                .toList();
    }
}
