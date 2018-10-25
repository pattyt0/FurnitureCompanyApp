package com.furnituremanager.controller;

import com.furnituremanager.dao.*;
import com.furnituremanager.dao.repository.BuyerRepository;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.*;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Part;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PromotionalPeriodControllerTests {

    @Mock
    RestTemplate restTemplate;
    @Mock
    private PromotionalPeriodService promotionalPeriodService;
    @Mock
    private ParticipantService participantService;
    @Mock
    private PurchaseService purchaseService;
    @Mock
    private PrizeService prizeService;
    @Spy
    @InjectMocks
    private PromotionalPeriodController promotionalPeriodController;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
        MockitoAnnotations.initMocks(this);

//        buyerRepository = Mockito.mock(BuyerRepository.class);
//        buyerService = new BuyerService(buyerRepository);
    }

    /**
     * pre-conditions:
     *  - ensure have furniture purchases in promotional period range date
     *
     * promotional period from 2018/11/01 - 2018/11/05
     */
    @Test
    public void raffleWinnersForPeriodThatExistsAndPurchasesForThisPeriodAreNotEmpty() throws URISyntaxException, EntityNotFoundException {
        PromotionalPeriod promotionalPeriodTest = new PromotionalPeriod();
        promotionalPeriodTest.setPromotionalPeriodId((long) 100);
        promotionalPeriodTest.setPromotionStart(LocalDate.of(2018,11,1));
        promotionalPeriodTest.setPromotionEnd(LocalDate.of(2018,11,5));
        promotionalPeriodTest.setTitle("November rain offers");

        Buyer juan = new Buyer();
        juan.setBuyerId((long)1);
        juan.setFirstName("juan");
        juan.setLastName("peres");
        juan.setAddress("nn");
        juan.setPhone("123456");
        juan.setPersonalId("111111cb");
        Ticket ticketJuan = new Ticket();
        ticketJuan.setBuyer(juan);
        ticketJuan.setNumberOfTickets(1);
        ticketJuan.setPurchaseDate(LocalDate.of(2018,11,01));

        Buyer pedro = new Buyer();
        pedro.setBuyerId((long)2);
        pedro.setFirstName("pedro");
        pedro.setLastName("peres");
        pedro.setAddress("nn");
        pedro.setPhone("123456");
        pedro.setPersonalId("111111cb");
        Ticket ticketPedro = new Ticket();
        ticketPedro.setBuyer(pedro);
        ticketPedro.setNumberOfTickets(1);
        ticketPedro.setPurchaseDate(LocalDate.of(2018,11,01));

        Buyer mateo = new Buyer();
        mateo.setBuyerId((long)3);
        mateo.setFirstName("mateo");
        mateo.setLastName("peres");
        mateo.setAddress("nn");
        mateo.setPhone("123456");
        mateo.setPersonalId("111111cb");
        Ticket ticketMateo = new Ticket();
        ticketMateo.setBuyer(mateo);
        ticketMateo.setNumberOfTickets(1);
        ticketMateo.setPurchaseDate(LocalDate.of(2018,11,05));

        List<Ticket> participants = new ArrayList<>();
        participants.add(ticketJuan);
        participants.add(ticketPedro);
        participants.add(ticketMateo);

        Prize car = new Prize();
        car.setPrizeId((long)100);
        car.setName("Car Toyota");
        car.setCategory("A");
        car.setPromotionalPeriod(promotionalPeriodTest);

        Prize refrigerator = new Prize();
        refrigerator.setPrizeId((long)100);
        refrigerator.setName("Consul");
        refrigerator.setCategory("B");
        refrigerator.setPromotionalPeriod(promotionalPeriodTest);
        List<Prize> prizes = new ArrayList<>();
        prizes.add(car);
        prizes.add(refrigerator);

        Participant participantJuan = new Participant();
        participantJuan.setParticipantId((long) 100);
        participantJuan.setBuyer(juan);
        participantJuan.setTicketNumber(UUID.randomUUID().toString());
        participantJuan.setPromotionalPeriod(promotionalPeriodTest);

        Participant participantPedro = new Participant();
        participantPedro.setParticipantId((long) 100);
        participantPedro.setBuyer(juan);
        participantPedro.setTicketNumber(UUID.randomUUID().toString());
        participantPedro.setPromotionalPeriod(promotionalPeriodTest);

        List<Participant> participantsTickets = new ArrayList<>();
        participantsTickets.add(participantJuan);
        participantsTickets.add(participantPedro);

//        URI uriWinners = new URIBuilder()
//                .setScheme("http")
//                .setHost("localhost")
//                .setPort(1234)
//                .setPath("/api/promotionalPeriods/"+String.valueOf(promotionalPeriodTest.getPromotionalPeriodId())+"/winners")
//                .build();


        when(promotionalPeriodService.getPromotionalPeriod(anyLong())).thenReturn(promotionalPeriodTest);
        when(participantService.findAllByPromotionalPeriod(any(PromotionalPeriod.class))).thenReturn(Collections.emptyList());
        when(purchaseService.getPurchasesBetweenPurchaseDateRageWithRaffleChances(promotionalPeriodTest.getPromotionStart(), promotionalPeriodTest.getPromotionEnd())).thenReturn(participants);
        when(prizeService.getPrizesByPromotionalPeriod(promotionalPeriodTest)).thenReturn(prizes);
//        when(participantService.generateRaffleTickets(any(Ticket.class), any(PromotionalPeriod.class))).thenCallRealMethod().;//.thenReturn(participantsTickets);//it does not interact with DB
        doCallRealMethod().when(participantService).generateRaffleTickets(any(Ticket.class), any(PromotionalPeriod.class));
//        when(participantService.raffleWinnersPerPrize(participantsTickets, prizes)).thenCallRealMethod();//.thenReturn(winners);//it does not interact with DB
        doCallRealMethod().when(participantService).raffleWinnersPerPrize(anyList(), eq(prizes));
        doNothing().when(participantService).saveAllParticipants(any());

        List<Participant> results = promotionalPeriodController.generateWinnersByPromotionalPeriod(promotionalPeriodTest.getPromotionalPeriodId());
        assertEquals(results.size(), prizes.size());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        List<Participant> personList = new ArrayList<Participant>();
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(personList,headers);

//        ResponseEntity<List<Participant>> response = restTemplate.exchange(
//                uriWinners,
//                HttpMethod.PUT,
//                requestEntity,
//                new ParameterizedTypeReference<List<Participant>>() {});
//        List<Participant> response;
//        response = restTemplate.put(uriWinners,List<Participant>);
//        List<Participant> winnersResult = response.getBody();
//        assertEquals(winnersResult.size(), prizes.size());
    }
}
