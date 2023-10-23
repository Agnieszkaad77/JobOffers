package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.FetchedOfferResponseDto;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;

public class OfferMapper {

    public static OfferResponseDto mapToOfferResponseDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .company(offer.company())
                .position(offer.position())
                .salary(offer.salary())
                .url(offer.url())
                .build();
    }

    public static Offer mapToOfferEntity(OfferRequestDto offerRequestDto) {
        return Offer.builder()
                .company(offerRequestDto.company())
                .position(offerRequestDto.position())
                .salary(offerRequestDto.salary())
                .url(offerRequestDto.url())
                .build();
    }

    public static Offer mapFromFetchedOfferDtoToOfferEntity(FetchedOfferResponseDto fetchedOfferResponseDto) {
        return Offer.builder()
                .url(fetchedOfferResponseDto.url())
                .salary(fetchedOfferResponseDto.salary())
                .position(fetchedOfferResponseDto.title())
                .company(fetchedOfferResponseDto.company())
                .build();
    }
}
