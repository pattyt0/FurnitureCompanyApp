package com.lineItem;

import com.purchase.Purchase;
import com.purchase.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LineItemService {

    @Autowired
    LineItemRepository lineItemRepository;

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
    Furniture model has 1 chance by Default
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
}
