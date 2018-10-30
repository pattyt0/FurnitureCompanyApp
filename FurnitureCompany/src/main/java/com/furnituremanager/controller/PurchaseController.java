package com.furnituremanager.controller;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.BuyerService;
import com.furnituremanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/buyers/{buyerId}/purchases")
    public ResponseEntity<Object> addPurchaseForBuyer(@PathVariable Long buyerId, @RequestBody Purchase purchase) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        purchase.setBuyer(buyer);
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.OK);
    }

    @DeleteMapping(value="/buyers/{buyerId}/purchases/{purchaseId}")
    public Purchase removePurchaseById(@PathVariable Long buyerId, @PathVariable Long purchaseId) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        Purchase purchase = purchaseService.getPurchase(purchaseId);
        if(buyer == null || purchase == null) { throw new EntityNotFoundException(Purchase.class, "id", purchaseId.toString()); }
        purchaseService.removePurchase(purchase);
        return purchase;
    }

    /**
     * Assume we always receive UNIX timestamp
     * @param buyerId
     * @param purchaseDateStart
     * @param purchaseDateEnd
     * @return
     */
    @GetMapping(value="/buyers/{buyerId}/purchases")
    public ResponseEntity<Page<Purchase>> listAllPurchasesByBuyerAndPurchaseDateBetween(@PathVariable Long buyerId, @RequestParam(value="from", required = false) Long purchaseDateStart, @RequestParam(value="to", required = false) Long purchaseDateEnd, Pageable pageable) throws EntityNotFoundException {
        Buyer buyer = buyerService.getBuyer(buyerId);
        if(purchaseDateStart == null && purchaseDateEnd == null) {
            return new ResponseEntity<>(purchaseService.getAllPurchasesByBuyer(buyer, pageable), HttpStatus.OK);
        }

        if(purchaseDateStart != null || purchaseDateEnd !=null){
            LocalDate startDate = purchaseDateStart!=null? LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateStart), ZoneId.systemDefault()).toLocalDate():null;
            LocalDate endDate = purchaseDateEnd!=null?LocalDateTime.ofInstant(Instant.ofEpochSecond(purchaseDateEnd), ZoneId.systemDefault()).toLocalDate():null;
            if(startDate.equals(endDate) || endDate==null){
                return new ResponseEntity<>(purchaseService.getPurchasesByBuyerAndDate(buyer, startDate, pageable), HttpStatus.OK);
            }

            if(startDate.isAfter(endDate)){
                return new ResponseEntity<>(purchaseService.getPurchasesByBuyerAndPurchaseDateBetween(buyer, endDate, startDate, pageable), HttpStatus.OK);
            }
            //TODO:return custom error to ensure end date is after startDate
            return new ResponseEntity<>(purchaseService.getPurchasesByBuyerAndPurchaseDateBetween(buyer, startDate, endDate, pageable), HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/purchases")
    public List<Purchase> listAllPurchasesByPurchaseDateBetween(@RequestParam(value="from", required = false) String startDate, @RequestParam(value="to", required = false) String endDate) throws EntityNotFoundException {
        LocalDate purchaseDateStart = startDate!=null? LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE):null;
        LocalDate purchaseDateEnd = endDate!=null?LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE):null;

        if(purchaseDateStart != null || purchaseDateEnd !=null){
            //TODO: validate purchaseDateStart and purchaseDateEnd are dates
            if(purchaseDateStart.equals(purchaseDateEnd) || purchaseDateEnd==null){
                return purchaseService.getPurchasesByDate(purchaseDateStart);
            }

            if(purchaseDateStart.isAfter(purchaseDateEnd)){
                return purchaseService.getPurchasesByPurchaseDateBetween(purchaseDateEnd, purchaseDateStart);
            }else {
                //TODO:return custom error to ensure end date is after startDate
                return purchaseService.getPurchasesByPurchaseDateBetween(purchaseDateStart, purchaseDateEnd);
            }
        }
        return purchaseService.getAllPurchases();
    }
}
