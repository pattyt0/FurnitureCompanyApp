package com.furnituremanager.service;

import com.furnituremanager.dao.repository.LineItemRepository;
import com.furnituremanager.errormanager.EntityNotFoundException;
import com.utils.LineItemUtils;
import com.furnituremanager.dao.Purchase;
import com.furnituremanager.dao.Ticket;
import com.furnituremanager.dao.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LineItemService {

    @Autowired
    LineItemRepository lineItemRepository;

    public LineItemService(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    public List<Ticket> fillRaffleTicketsChance(List<Purchase> purchasesInTimeRange) {
        List<Ticket> promotionalPurchase = new ArrayList<>();

        Ticket raffleTicket = null;
        for (Purchase purchase:purchasesInTimeRange) {
            raffleTicket = new Ticket();
            raffleTicket.setPurchaseDate(purchase.getPurchaseDate());
            raffleTicket.setBuyer(purchase.getBuyer());

            raffleTicket.setNumberOfTickets(getRaffleChances(purchase));
            promotionalPurchase.add(raffleTicket);
        }
        return promotionalPurchase;
    }

    /*
    Furniture dao has 1 chance by Default
    unless configuration file modifies it
     */
    private int getRaffleChances(Purchase purchase) {
        //Map<furnitureId, NumberChances>
        Map<Long, Integer> configurationChances = LineItemUtils.getConfiguredChancePerFurnitureModel();

        int result = 1;
        for (LineItem lineItem:lineItemRepository.findAllByPurchase(purchase)) {
            if(configurationChances.containsKey(lineItem.getFurniture().getFurnitureId())){
                result = configurationChances.get(lineItem.getFurniture().getFurnitureId());
            }
        }
        return result;
    }

    public LineItem saveLineItem(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    public LineItem getLineItem(Long lineItemId) throws EntityNotFoundException {
        Optional<LineItem> lineItem = lineItemRepository.findById(lineItemId);
        if(!lineItem.isPresent()){throw new EntityNotFoundException(LineItem.class, "Id", lineItemId.toString());  }
        return lineItem.get();
    }

    public void deleteLineItem(LineItem lineItemId) {
        lineItemRepository.delete(lineItemId);
    }

    public Page<LineItem> getLineItemsByPurchase(Pageable pageable) {
        return lineItemRepository.findAll(pageable);
    }
}
