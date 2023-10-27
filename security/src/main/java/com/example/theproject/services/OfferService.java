package com.example.theproject.services;

import com.example.theproject.dto.OfferDto;
import com.example.theproject.models.Offer;
import com.example.theproject.repository.OfferRepo;
import com.example.theproject.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepo offerRepo;
    private UserRepo userRepo;

    @Autowired
    public OfferService(OfferRepo offerRepo, UserRepo userRepo
    ) {
        this.offerRepo = offerRepo;
        this.userRepo = userRepo;
    }

    public List<Offer> getAllOffers() {
        return offerRepo.findAll();
    }

    // add an offer
    public Offer addOffer(OfferDto offer) {
        ModelMapper modelMapper = new ModelMapper();
        Offer offerEntity = modelMapper.map(offer, Offer.class);
        offerEntity.setUser(userRepo.findById(Integer.parseInt(offer.getUserId())).get());
        return offerRepo.save(offerEntity);
    }

    // update an offer
    public Offer updateOffer(int id, OfferDto offer) {
        Offer offerEntity = offerRepo.findById(id).get();
        offerEntity.setName(offer.getName());
        offerEntity.setDescription(offer.getDescription());
        offerEntity.setPrice(offer.getPrice());
        offerEntity.setCity(offer.getCity());
        offerEntity.setPhoneNumber(offer.getPhoneNumber());
        offerEntity.setUser(userRepo.findById(Integer.parseInt(offer.getUserId())).get());
        offerEntity.setLatitude(offer.getLatitude());
        offerEntity.setLongitude(offer.getLongitude());

        return offerRepo.save(offerEntity);
    }

    // delete an offer
    public void deleteOffer(int id) {
        offerRepo.deleteById(id);
    }

    // find offers that are not booked in a given period
    public List<Offer> findOffersNotBookedInPeriod(String city, String startDate, String endDate) {
        return offerRepo.findOffersNotBookedInPeriod(city, startDate, endDate);
    }

    // get offer by id
    public Offer getOfferById(int id) {
        return offerRepo.findById(id).get();
    }

    // get offers by user id
    public List<Offer> getOffersByUserId(int id) {
        return offerRepo.findOffersByUserId(id);
    }
}
