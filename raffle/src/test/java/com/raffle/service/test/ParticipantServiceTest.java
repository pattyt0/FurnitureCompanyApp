package com.raffle.service.test;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantServiceTest {
//    @Spy
//    @InjectMocks
//    private ParticipantService participantService;
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void generateRaffleTickets() {
//        PromotionalPeriod promotionalPeriodTest = new PromotionalPeriod();
//        promotionalPeriodTest.setPromotionalPeriodId((long) 100);
//        promotionalPeriodTest.setPromotionStart(LocalDate.of(2018,11,1));
//        promotionalPeriodTest.setPromotionEnd(LocalDate.of(2018,11,5));
//        promotionalPeriodTest.setTitle("November rain offers");
//
//        Buyer juan = new Buyer();
//        juan.setBuyerId((long)1);
//        juan.setFirstName("juan");
//        juan.setLastName("peres");
//        juan.setAddress("nn");
//        juan.setPhone("123456");
//        juan.setPersonalId("111111cb");
//
//        Ticket player = new Ticket();
//        player.setBuyer(juan);
//        player.setPurchaseDate(LocalDate.of(2018,11,01));
//        player.setNumberOfTickets(2);
//
//        List<Participant> ticketsForPlayer = participantService.generateRaffleTickets(player, promotionalPeriodTest);
//        Assert.assertEquals(ticketsForPlayer.size(), player.getNumberOfTickets());
//    }
//
//    @Test
//    public void raffleWinnersPerPrize() {
//        PromotionalPeriod promotionalPeriodTest = new PromotionalPeriod();
//        promotionalPeriodTest.setPromotionalPeriodId((long) 100);
//        promotionalPeriodTest.setPromotionStart(LocalDate.of(2018,11,1));
//        promotionalPeriodTest.setPromotionEnd(LocalDate.of(2018,11,5));
//        promotionalPeriodTest.setTitle("November rain offers");
//
//        Buyer juan = new Buyer();
//        juan.setBuyerId((long)1);
//        juan.setFirstName("juan");
//        juan.setLastName("peres");
//        juan.setAddress("nn");
//        juan.setPhone("123456");
//        juan.setPersonalId("111111cb");
//
//        Participant participantJuan = new Participant();
//        participantJuan.setParticipantId((long) 100);
//        participantJuan.setBuyer(juan);
//        participantJuan.setTicketNumber(UUID.randomUUID().toString());
//        participantJuan.setPromotionalPeriod(promotionalPeriodTest);
//
//        Buyer lucas = new Buyer();
//        lucas.setBuyerId((long)2);
//        lucas.setFirstName("lucas");
//        lucas.setLastName("peres");
//        lucas.setAddress("nn");
//        lucas.setPhone("123456");
//        lucas.setPersonalId("111111cb");
//
//        Participant participantLucas = new Participant();
//        participantLucas.setParticipantId((long) 101);
//        participantLucas.setBuyer(lucas);
//        participantLucas.setTicketNumber(UUID.randomUUID().toString());
//        participantLucas.setPromotionalPeriod(promotionalPeriodTest);
//
//        Buyer teresa = new Buyer();
//        teresa.setBuyerId((long)3);
//        teresa.setFirstName("teresa");
//        teresa.setLastName("peres");
//        teresa.setAddress("nn");
//        teresa.setPhone("123456");
//        teresa.setPersonalId("111111cb");
//
//        Participant participantTeresa = new Participant();
//        participantTeresa.setParticipantId((long) 102);
//        participantTeresa.setBuyer(teresa);
//        participantTeresa.setTicketNumber(UUID.randomUUID().toString());
//        participantTeresa.setPromotionalPeriod(promotionalPeriodTest);
//
//        List<Participant> ticketsPerPromotionalPeriod = new ArrayList<>();
//        ticketsPerPromotionalPeriod.add(participantJuan);
//        ticketsPerPromotionalPeriod.add(participantLucas);
//        ticketsPerPromotionalPeriod.add(participantTeresa);
//
//        Prize car = new Prize();
//        car.setPrizeId((long)100);
//        car.setName("Car Toyota");
//        car.setCategory("A");
//        car.setPromotionalPeriod(promotionalPeriodTest);
//
//        Prize tvDesk = new Prize();
//        tvDesk.setPrizeId((long)101);
//        tvDesk.setName("TV Desk Furinno");
//        tvDesk.setCategory("A");
//        tvDesk.setPromotionalPeriod(promotionalPeriodTest);
//
//        List<Prize> prizes = new ArrayList<>();
//        prizes.add(car);
//        prizes.add(tvDesk);
//
//        List<Participant> winners = participantService.raffleWinnersPerPrize(ticketsPerPromotionalPeriod, prizes);
//        assertEquals(winners.size(), prizes.size());
//    }
}
