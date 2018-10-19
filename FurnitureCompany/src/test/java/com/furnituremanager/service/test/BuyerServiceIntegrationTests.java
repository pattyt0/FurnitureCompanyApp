package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.dao.repository.BuyerRepository;
import com.furnituremanager.service.BuyerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyerServiceIntegrationTests {

    @MockBean
    BuyerRepository buyerRepositoryMock;

    @Autowired
    BuyerService buyerService;

    @Test
    public void saveBuyerWithValidValues(){
        Buyer juan = new Buyer();
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        when(buyerRepositoryMock.save(juan)).thenReturn(juan);

        assertEquals(juan.getFirstName(), buyerService.saveBuyer(juan).getFirstName());
    }
}
