package com.furnituremanager.service;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.repository.BuyerRepository;
import com.furnituremanager.errormanager.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer getBuyer(Long buyerId) throws EntityNotFoundException {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if(!buyer.isPresent()) throw new EntityNotFoundException(Buyer.class, "id", buyerId.toString());
        return buyer.get();
    }

    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public void deleteBuyer(Buyer buyer) {
        buyerRepository.delete(buyer);
    }

    public Page<Buyer> getAllBuyers(Pageable pageable) {
        return buyerRepository.findAll(pageable);
    }
}
