package com.raffleTicket;

import com.promotionalPeriod.PromotionalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaffleTicketRepository extends JpaRepository<RaffleTicket, Long> {
//    List<Purchase> findAllByPurchasesTimeBetween(String promotionalPeriodStartDate, String promotionalPeriodEndDate);
    RaffleTicket findAllByPromotionalPeriod(PromotionalPeriod promotionalPeriod);
}
