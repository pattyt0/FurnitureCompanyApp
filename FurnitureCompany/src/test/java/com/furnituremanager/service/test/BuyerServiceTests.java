package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.repository.BuyerRepository;
import com.furnituremanager.service.BuyerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BuyerServiceTests {

    private BuyerRepository buyerRepository;
    private BuyerService buyerService;

    @Before
    public void init() {
        buyerRepository = Mockito.mock(BuyerRepository.class);
        buyerService = new BuyerService(buyerRepository);
    }

    @Test
    public void addBuyerWithEnabledData(){
        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        when(buyerRepository.save(juan)).thenReturn(juan);

        Buyer actualBuyer = buyerService.saveBuyer(juan);

        assertEquals(actualBuyer.getFirstName(), juan.getFirstName());
    }
}
