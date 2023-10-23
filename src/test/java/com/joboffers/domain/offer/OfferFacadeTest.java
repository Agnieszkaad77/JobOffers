package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OfferFacadeTest {

    @Test
    public void should_fetch_offers_and_save_all() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().createOfferFacadeForTests();

        // when
        List<OfferResponseDto> offers = offerFacade.fetchAllOffersAndSaveIfNotExist();

        // then
        assertThat(offers).hasSize(6);
    }

    @Test
    public void should_fetch_offers_and_save_only_3_new_offers() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().createOfferFacadeForTests();

        offerFacade.saveOffer(new OfferRequestDto("comp1", "pos1", "sal1", "1"));
        offerFacade.saveOffer(new OfferRequestDto("comp2", "pos2", "sal2", "2"));
        offerFacade.saveOffer(new OfferRequestDto("comp2", "pos2", "sal2", "3"));

        // when
        List<OfferResponseDto> fetchedOffers = offerFacade.fetchAllOffersAndSaveIfNotExist();

        // then
        Assertions.assertAll(
                () -> assertThat(fetchedOffers.get(0).url()).isEqualTo("4"),
                () -> assertThat(fetchedOffers.get(1).url()).isEqualTo("5"),
                () -> assertThat(fetchedOffers.get(2).url()).isEqualTo("6"),
                () -> assertThat(offerFacade.findOffers()).hasSize(6)
        );
    }

    @Test
    public void should_save_offer() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).createOfferFacadeForTests();

        // when
        offerFacade.saveOffer(new OfferRequestDto("comp1", "pos1", "sal1", "url1"));
        offerFacade.saveOffer(new OfferRequestDto("comp2", "pos2", "sal2", "url2"));
        offerFacade.saveOffer(new OfferRequestDto("comp3", "pos3", "sal3", "url3"));

        // then
        assertThat(offerFacade.findOffers()).hasSize(3);
    }

    @Test
    public void should_find_offer_by_id() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).createOfferFacadeForTests();
        OfferResponseDto offer = offerFacade.saveOffer(
                new OfferRequestDto("comp", "pos", "sal", "url1"));

        // when
        OfferResponseDto offerById = offerFacade.findOfferById(offer.id());

        // then
        assertThat(offerById).isEqualTo(offer);
    }

    @Test
    public void should_throw_offer_not_found_exception_if_offer_not_found() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).createOfferFacadeForTests();

        // when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("0"));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id: 0 not found!");
    }
}