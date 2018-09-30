package com.raffleTicket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RaffleTicketRepository extends JpaRepository<RaffleTicket, Long> {
//    List<Purchase> findAllByPurchasesTimeBetween(String promotionalPeriodStartDate, String promotionalPeriodEndDate);
}
