package com.furnituremanager.service;

import com.furnituremanager.dao.repository.PurchaseRepository;
import com.furnituremanager.dao.Ticket;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.errormanager.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    LineItemService lineItemService;

    public List<Purchase> getAllPurchasesBetweenDates(LocalDate purchaseDateStart, LocalDate purchaseDateEnd) {
        return purchaseRepository.findAllByPurchaseDateBetween(purchaseDateStart, purchaseDateEnd);
    }

    public Optional<Purchase> getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }

    public List<Ticket> getPurchasesBetweenPurchaseDateRageWithRaffleChances(Long purchaseDateStart, Long purchaseDateEnd) {
        LocalDate rangeStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate();
        LocalDate rangeEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate();

        return getPurchasesBetweenPurchaseDateRageWithRaffleChances(rangeStart, rangeEnd);
    }

    public List<Ticket> getPurchasesBetweenPurchaseDateRageWithRaffleChances(LocalDate purchaseDateStart, LocalDate purchaseDateEnd) {

        List<Purchase> purchasesInTimeRange = purchaseRepository.findAllByPurchaseDateBetween(purchaseDateStart, purchaseDateEnd);

        List<Ticket> raffleTickets = lineItemService.fillRaffleTicketsChance(purchasesInTimeRange);

        return raffleTickets;
    }

    public Purchase getPurchase(Long purchaseId) throws EntityNotFoundException {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        if(!purchase.isPresent()){ throw new EntityNotFoundException(Purchase.class, "id", purchaseId.toString()); }
        return purchase.get();
    }
}
