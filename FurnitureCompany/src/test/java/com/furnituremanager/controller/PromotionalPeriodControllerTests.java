package com.furnituremanager.controller;

import com.furnituremanager.dao.Participant;
import com.furnituremanager.dao.PromotionalPeriod;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.furnituremanager.service.PromotionalPeriodService;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromotionalPeriodControllerTests {

    @Mock
    private PromotionalPeriodService promotionalPeriodService;

    @InjectMocks
    private PromotionalPeriodController promotionalPeriodController;

    /**
     * pre-conditions:
     *  - ensure have furniture purchases in promotional period range date
     *
     */
    @Test
    public void raffleWinnersForPeriodThatExistsAndPurchasesForThisPeriodAreNotEmpty() throws URISyntaxException, EntityNotFoundException {
        PromotionalPeriod promotionalPeriodtest = new PromotionalPeriod();
        promotionalPeriodtest.setPromotionalPeriodId((long) 100);
        promotionalPeriodtest.setPromotionStart(LocalDate.of(2018,11,1));
        promotionalPeriodtest.setPromotionEnd(LocalDate.of(2018,11,30));
        promotionalPeriodtest.setTitle("November rain offers");

        int promotionalPeriodId = 100;
        URI uriWinners = new URIBuilder()
                .setScheme("http")
                .setHost("localhost")
                .setPath("/api/promotionalPeriods/")
                .setParameter("promotionalPeriodId", String.valueOf(promotionalPeriodId))
                .setPath("/winners")
                .build();

        RestTemplate restTemplate = new RestTemplate();

        when(promotionalPeriodService.getPromotionalPeriod(anyLong())).thenReturn(promotionalPeriodtest);
        
        ResponseEntity<List<Participant>> response = restTemplate.exchange(uriWinners,
                HttpMethod.PUT, null, new ParameterizedTypeReference<List<Participant>>(){});
        List<Participant> participants = response.getBody();



//        assertEquals(participants.size(), 7);
    }
}
