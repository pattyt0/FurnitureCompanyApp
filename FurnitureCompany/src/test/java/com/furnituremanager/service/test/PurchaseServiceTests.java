package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.PurchaseRepository;
import com.furnituremanager.service.PurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
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

}
