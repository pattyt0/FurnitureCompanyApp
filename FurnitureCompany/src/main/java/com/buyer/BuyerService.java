package com.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    public Optional<Buyer> getBuyerById(Long buyerId) {
        return buyerRepository.findById(buyerId);
    }
}
