package com.furnituremanager.service.test;

import com.furnituremanager.dao.Furniture;
import com.furnituremanager.dao.repository.FurnitureRepository;
import com.furnituremanager.service.FurnitureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FurnitureServiceTests {
    private FurnitureRepository furnitureRepository;
    private FurnitureService furnitureService;

    @Before
    public void init() {
        furnitureRepository = Mockito.mock(FurnitureRepository.class);
        furnitureService = new FurnitureService(furnitureRepository);
    }

    @Test
    public void addFurnitureWithEnabledData(){
        Furniture seniorChair = new Furniture();
        seniorChair.setFurnitureId((long)1);
        seniorChair.setCode("PAX1000");
        seniorChair.setName("Pininfarina’s Aresline Xten");

        when(furnitureRepository.save(seniorChair)).thenReturn(seniorChair);

        Furniture actualFurniture = furnitureService.saveFurniture(seniorChair);

        assertEquals(actualFurniture.getName(), seniorChair.getName());
    }

}
