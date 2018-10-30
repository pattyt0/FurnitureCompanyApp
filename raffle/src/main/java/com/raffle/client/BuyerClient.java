package com.raffle.client;

import com.raffle.model.Buyer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("furnitureManagerMicroservice")
public interface BuyerClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/api/buyers/{buyerId}",
            consumes="application/json")
    Buyer getBuyer(@PathVariable("buyerId") Long buyerId);
}
