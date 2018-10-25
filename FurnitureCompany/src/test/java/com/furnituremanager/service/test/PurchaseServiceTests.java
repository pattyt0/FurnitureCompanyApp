package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.PurchaseRepository;
import com.furnituremanager.service.PurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PurchaseServiceTests {
    private PurchaseRepository purchaseRepository;
    private PurchaseService purchaseService;

    @Before
    public void init() {
        purchaseRepository = Mockito.mock(PurchaseRepository.class);
        purchaseService = new PurchaseService(purchaseRepository);
    }

    @Test
    public void addPurchaseWithEnabledData(){
        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        LocalDate purchaseDate = LocalDate.of(2018, 10, 23);

        Purchase purchase = new Purchase();
        purchase.setPurchaseId((long)1);
        purchase.setPurchaseDate(purchaseDate);
        purchase.setBuyer(juan);

        when(purchaseRepository.save(purchase)).thenReturn(purchase);

        Purchase actualPurchase = purchaseService.savePurchase(purchase);

        assertEquals(actualPurchase.getBuyer(), purchase.getBuyer());
        assertEquals(actualPurchase.getBuyer().getBuyerId(), purchase.getBuyer().getBuyerId());
        assertEquals(actualPurchase.getPurchaseDate().toString(), purchase.getPurchaseDate().toString());
    }

    @Test
    public void getPurchasesByBuyerAndPurchaseDateBetween(){
        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        Purchase purchaseChair = new Purchase();
        purchaseChair.setPurchaseId((long) 100);
        purchaseChair.setBuyer(juan);
        purchaseChair.setPurchaseDate(LocalDate.of(2018,11,01));

        Purchase purchaseDesk = new Purchase();
        purchaseDesk.setPurchaseId((long) 101);
        purchaseDesk.setBuyer(juan);
        purchaseDesk.setPurchaseDate(LocalDate.of(2018,11,02));

        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchaseChair);
        purchases.add(purchaseDesk);

        Page<Purchase> purchasesByBuyerAndRangeDates = new Page<Purchase>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super Purchase, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List<Purchase> getContent() {
                return purchases;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Purchase> iterator() {
                return null;
            }
        };

        when(purchaseRepository.findAllByBuyerAndPurchaseDateBetween(any(Buyer.class), any(LocalDate.class), any(LocalDate.class), any(Pageable.class))).thenReturn(purchasesByBuyerAndRangeDates);

        Page<Purchase> results = purchaseService.getPurchasesByBuyerAndPurchaseDateBetween(
                juan,
                LocalDate.of(2018,11,01),
                LocalDate.of(2018,11,01),
                Pageable.unpaged());

        assertEquals(results.getContent().size(), purchases.size());
    }
}
