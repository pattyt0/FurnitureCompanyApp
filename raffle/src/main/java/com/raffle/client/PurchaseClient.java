package com.raffle.client;

import com.raffle.model.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("furnitureManagerMicroservice")
public interface PurchaseClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/api/purchases/tickets",
            consumes="application/json")
    List<Ticket> getPurchasesBetweenPurchaseDateRageWithRaffleChances(@RequestParam(value="from", required = false) String promotionStart, @RequestParam(value="to", required = false) String promotionEnd);
}
