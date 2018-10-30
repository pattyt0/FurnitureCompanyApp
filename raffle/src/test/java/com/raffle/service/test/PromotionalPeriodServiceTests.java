package com.raffle.service.test;

import com.raffle.dao.PromotionalPeriod;
import com.raffle.repository.PromotionalPeriodRepository;
import com.raffle.service.PromotionalPeriodService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PromotionalPeriodServiceTests {
    private PromotionalPeriodRepository promotionalPeriodRepository;
    private PromotionalPeriodService promotionalPeriodService;

    @Before
    public void init() {
        promotionalPeriodRepository = Mockito.mock(PromotionalPeriodRepository.class);
        promotionalPeriodService = new PromotionalPeriodService(promotionalPeriodRepository);
    }

    @Test
    public void addPromotionalPeriodForRaffleTestingPeriod(){
        PromotionalPeriod promPerOctoberTest = new PromotionalPeriod();
        promPerOctoberTest.setPromotionalPeriodId((long)1);
        promPerOctoberTest.setPromotionStart(LocalDate.of(2018,10,1));
        promPerOctoberTest.setPromotionEnd(LocalDate.of(2018,10,31));
        promPerOctoberTest.setTitle("October fest");

        when(promotionalPeriodRepository.save(promPerOctoberTest)).thenReturn(promPerOctoberTest);

        PromotionalPeriod actualPromotionalPeriod = promotionalPeriodService.savePromotionalPeriod(promPerOctoberTest);

        assertEquals(actualPromotionalPeriod.getTitle(),promPerOctoberTest.getTitle());
        assertEquals(actualPromotionalPeriod.getPromotionStart(), promPerOctoberTest.getPromotionStart());
        assertEquals(actualPromotionalPeriod.getPromotionEnd(), promPerOctoberTest.getPromotionEnd());
    }
}
