package com.example.theproject.controllers;

import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.OfferDto;
import com.example.theproject.models.Offer;
import com.example.theproject.services.OfferService;
import com.example.theproject.validation.FileSizeConstraint;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/api/offers")
@Validated
public class OffersController {

    private final OfferService offerService;


    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    // get all offers
    @GetMapping("")

    public ResponseEntity getAllOffers() {
        try {
            return ResponseEntity.ok(offerService.getAllOffers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offers could not be retrieved"));
        }
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity addOffer(@RequestPart @FileSizeConstraint(value = 1 * 1024 * 1024) MultipartFile image, @RequestPart @Valid OfferDto offer) {

        try {
            return ResponseEntity.ok(offerService.addOffer(offer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offer could not be added"));
        }
    }

    // update offer
    @PutMapping("{id}")
    public ResponseEntity updateOffer(@PathVariable int id, @RequestBody @Valid OfferDto offer) {
        try {
            if (id != offer.getId())
                return ResponseEntity.badRequest().body(new CustomErrorResponse("Offer id does not match"));
            return ResponseEntity.ok(offerService.updateOffer(id, offer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offer could not be updated"));
        }
    }

    // delete offer
    @DeleteMapping("{id}")
    public ResponseEntity deleteOffer(@PathVariable int id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offer could not be deleted"));
        }
    }

    @GetMapping("search")
    public ResponseEntity searchOffers(@RequestParam String city, @RequestParam String startDate, @RequestParam String endDate) {
        try {
            Collection<Offer> offers = offerService.findOffersNotBookedInPeriod(city, startDate, endDate);
            return ResponseEntity.ok(offers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offers could not be found"));
        }
    }

    // get offer by id
    @GetMapping("{id}")
    public ResponseEntity getOfferById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(offerService.getOfferById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offer could not be found"));
        }
    }

    // get user offers

    @GetMapping("user/{id}")
    public ResponseEntity getUserOffers(@PathVariable int id) {
        // get user id from principal
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (id != Integer.parseInt(userDetails.getUsername()))
            return ResponseEntity.badRequest().body(new CustomErrorResponse("User id does not match"));

        try {
            return ResponseEntity.ok(offerService.getOffersByUserId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Offers could not be found"));
        }
    }


}
