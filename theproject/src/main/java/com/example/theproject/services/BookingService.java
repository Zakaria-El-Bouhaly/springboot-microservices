package com.example.theproject.services;

import com.example.theproject.dto.BookingDto;
import com.example.theproject.models.Booking;
import com.example.theproject.openfeignclients.UserFeignClient;
import com.example.theproject.repository.BookingRepo;
import com.example.theproject.repository.OfferRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingRepo bookingRepo;
    private OfferRepo offerRepo;

    private final UserFeignClient userFeignClient;

    public BookingService(BookingRepo bookingRepo,
                          UserFeignClient userFeignClient,
                            OfferRepo offerRepo
    ) {
        this.userFeignClient = userFeignClient;
        this.bookingRepo = bookingRepo;
        this.offerRepo = offerRepo;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // add a new booking
    public Booking addBooking(BookingDto booking) {
        ModelMapper modelMapper = new ModelMapper();
        Booking bookingEntity = modelMapper.map(booking, Booking.class);

        bookingEntity.setOffer(offerRepo.findById(booking.getOfferId()).get());
        return bookingRepo.save(bookingEntity);
    }
}
