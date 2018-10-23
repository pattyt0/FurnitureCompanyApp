package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.Furniture;
import com.furnituremanager.dao.LineItem;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.repository.LineItemRepository;
import com.furnituremanager.service.LineItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LineItemServiceTest {
    private LineItemRepository lineItemRepository;
    private LineItemService lineItemService;

    @Before
    public void init() {
        lineItemRepository = Mockito.mock(LineItemRepository.class);
        lineItemService = new LineItemService(lineItemRepository);
    }

    @Test
    public void addLineItemForExistingPurchase(){
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

        Furniture seniorChair = new Furniture();
        seniorChair.setFurnitureId((long)1);
        seniorChair.setCode("PAX1000");
        seniorChair.setName("Pininfarina’s Aresline Xten");

        LineItem lineItem = new LineItem();
        lineItem.setBuyer(juan);
        lineItem.setFurniture(seniorChair);
        lineItem.setPurchase(purchase);

        when(lineItemRepository.save(lineItem)).thenReturn(lineItem);

        LineItem actualLineItem = lineItemService.saveLineItem(lineItem);

        assertEquals(actualLineItem.getBuyer().getBuyerId(), lineItem.getBuyer().getBuyerId());
        assertEquals(actualLineItem.getFurniture().getFurnitureId(), lineItem.getFurniture().getFurnitureId());
        assertEquals(actualLineItem.getPurchase().getPurchaseId(), lineItem.getPurchase().getPurchaseId());
    }
}
