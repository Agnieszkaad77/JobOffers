package com.joboffers.infrastructure.offer.http;

import com.joboffers.domain.offer.OfferFetcher;
import com.joboffers.domain.offer.dto.FetchedOfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class OfferHttpClient implements OfferFetcher {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;
    @Override
    public List<FetchedOfferResponseDto> fetchOffers() {
        log.info("Started fetching offers using http client");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            String urlForService = getUrlForService("/offers");
            final String url = UriComponentsBuilder.fromHttpUrl(urlForService).toUriString();
            ResponseEntity<List<FetchedOfferResponseDto>> response = restTemplate.exchange(url, HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            final List<FetchedOfferResponseDto> body = response.getBody();
            if (body == null) {
                log.info("Response Body was null while returning empty list");
                return Collections.emptyList();
            }
            log.info("Success Response Body returned: " + body);
            return body;
        } catch (ResourceAccessException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
