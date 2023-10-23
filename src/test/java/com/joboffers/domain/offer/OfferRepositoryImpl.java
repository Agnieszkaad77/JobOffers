package com.joboffers.domain.offer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OfferRepositoryImpl implements OfferRepository {

    Map<String, Offer> database = new ConcurrentHashMap<>();

    @Override
    public List<Offer> findAll() {
        return database.values().stream()
                .toList();
    }

    @Override
    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Offer save(Offer offerToSave) {
        if (database.values().stream()
                .anyMatch(offer -> offer.url().equals(offerToSave.url()))) {
            throw new OfferDuplicateException(offerToSave.url());
        }

        UUID id = UUID.randomUUID();
        Offer offer = new Offer(id.toString(), offerToSave.company(), offerToSave.position(), offerToSave.salary(),
                offerToSave.url());
        database.put(id.toString(), offer);
        return offer;
    }

    @Override
    public boolean existsByUrl(String url) {
        long count = database.values().stream()
                .filter(offer -> offer.url().equals(url))
                .count();
        return count == 1;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }
}
