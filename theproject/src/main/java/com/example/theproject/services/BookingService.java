package com.example.theproject.services;

import com.example.theproject.dto.BookingDto;
import com.example.theproject.dto.OfferDto;
import com.example.theproject.models.Booking;
import com.example.theproject.models.Offer;
import com.example.theproject.repository.BookingRepo;
import com.example.theproject.repository.OfferRepo;
import com.example.theproject.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private BookingRepo bookingRepo;
    private OfferRepo offerRepo;

    @Autowired
    public BookingService(BookingRepo bookingRepo, UserRepo userRepo, OfferRepo offerRepo
    ) {
        this.bookingRepo = bookingRepo;
        this.offerRepo = offerRepo;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // add an offer
    public Booking addBooking(BookingDto booking) {
        ModelMapper modelMapper = new ModelMapper();
        Booking bookingEntity = modelMapper.map(booking, Booking.class);

        bookingEntity.setOffer(offerRepo.findById(booking.getOfferId()).get());
        return bookingRepo.save(bookingEntity);
    }

    // update an offer
    public Booking updateBooking(int id, BookingDto booking) {
        Booking bookingEntity = bookingRepo.findById(id).get();
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckIn());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckOut());
            bookingEntity.setCheckIn(startDate);
            bookingEntity.setCheckOut(endDate);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse date");
        }

        return bookingRepo.save(bookingEntity);
    }

    // delete an offer
    public void deleteBooking(int id) {
        bookingRepo.deleteById(id);
    }


    // get offer by id
    public Booking getBookingById(int id) {
        return bookingRepo.findById(id).get();
    }
}
