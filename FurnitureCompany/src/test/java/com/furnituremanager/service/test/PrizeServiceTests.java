package com.furnituremanager.service.test;

import com.furnituremanager.dao.Prize;
import com.furnituremanager.dao.PromotionalPeriod;
import com.furnituremanager.dao.repository.PrizeRepository;
import com.furnituremanager.service.PrizeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PrizeServiceTests {
    private PrizeRepository prizeRepository;
    private PrizeService prizeService;

    @Before
    public void init() {
        prizeRepository = Mockito.mock(PrizeRepository.class);
        prizeService = new PrizeService(prizeRepository);
    }

    @Test
    public void addPrizeForRaffleTestingPeriod(){
        PromotionalPeriod promPerOctoberTest = new PromotionalPeriod();
        promPerOctoberTest.setPromotionalPeriodId((long)1);
        promPerOctoberTest.setPromotionStart(LocalDate.of(2018,10,1));
        promPerOctoberTest.setPromotionEnd(LocalDate.of(2018,10,31));
        promPerOctoberTest.setTitle("October fest");

        Prize car = new Prize();
        car.setPrizeId((long)1);
        car.setName("Toyota 0 Km");
        car.setCategory("A");
        car.setPromotionalPeriod(promPerOctoberTest);

        when(prizeRepository.save(car)).thenReturn(car);

        Prize actualPrize = prizeService.addPrize(car);

        assertEquals(actualPrize.getName(), car.getName());
        assertEquals(actualPrize.getCategory(), car.getCategory());
        assertEquals(actualPrize.getPromotionalPeriod().getPromotionalPeriodId(), car.getPromotionalPeriod().getPromotionalPeriodId());
    }


}
