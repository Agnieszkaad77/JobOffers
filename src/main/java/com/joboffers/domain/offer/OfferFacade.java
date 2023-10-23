package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;

    public List<OfferResponseDto> findOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapToOfferResponseDto)
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        return offerRepository.findById(id)
                .map(OfferMapper::mapToOfferResponseDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public OfferResponseDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offerToSave = OfferMapper.mapToOfferEntity(offerRequestDto);
        Offer savedOffer = offerRepository.save(offerToSave);
        return OfferMapper.mapToOfferResponseDto(savedOffer);
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveIfNotExist() {
        return offerService.fetchAllOffersAndSaveIfNotExist()
                .stream()
                .map(OfferMapper::mapToOfferResponseDto)
                .toList();
    }
}
