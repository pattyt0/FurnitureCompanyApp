package com.furnituremanager.service.test;

import com.furnituremanager.dao.Buyer;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.BuyerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyerServiceIntegrationTests {

    @Mock
    BuyerService buyerService;

//    @Rule
//    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveBuyerWithValidValues(){
        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        given(buyerService.saveBuyer(juan)).willReturn(juan);
        Buyer actualBuyer = buyerService.saveBuyer(juan);
        assertEquals(actualBuyer.getFirstName(), juan.getFirstName());
    }

    /**
     * Set Buyer Id for testing purposes
     */
    @Test
    public void getBuyerThatExistsOnDB() throws EntityNotFoundException {
        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");

        given(buyerService.getBuyer(juan.getBuyerId())).willReturn(juan);
        Buyer actualBuyer = buyerService.getBuyer(juan.getBuyerId());

        assertEquals(actualBuyer.getBuyerId(), juan.getBuyerId());
    }

    /**
     * Set Buyer Id for testing purposes
     */
    @Test(expected = EntityNotFoundException.class)
    public void getBuyerThatDoesNotExistsOnDBThrowsException() throws EntityNotFoundException {
        Buyer pepe = new Buyer();
        pepe.setBuyerId((long)2);
        pepe.setFirstName("pepe");
        pepe.setLastName("brown");
        pepe.setAddress("Av. Melchor peres");
        pepe.setPhone("4444444");
        pepe.setPersonalId("1112223cb");

        given(buyerService.getBuyer(pepe.getBuyerId())).willAnswer( invocation -> { throw new EntityNotFoundException(Buyer.class, "id", pepe.getBuyerId().toString());});
        Buyer actualBuyer = buyerService.getBuyer(pepe.getBuyerId());
    }
}
