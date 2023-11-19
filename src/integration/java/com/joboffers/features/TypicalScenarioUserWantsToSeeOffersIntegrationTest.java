package com.joboffers.features;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.joboffers.BaseIntegrationTest;
import com.joboffers.SampleJobOfferResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class TypicalScenarioUserWantsToSeeOffersIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Test
    public void user_wants_to_see_offers_but_have_to_be_logged_in_and_external_server_should_have_some_offers() {
//    Step 1: There are no offers in external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithNoOffers())));

//    Step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
//    Step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)
//    Step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//    Step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//    Step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//    Step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
//    Step 8: there are 2 new offers in external HTTP server
//    Step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//    Step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//    Step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
//    Step 12: user made GET /offers/1000 and system returned OK(200) with offer
//    Step 13: there are 2 new offers in external HTTP server
//    Step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//    Step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000
    }
}
