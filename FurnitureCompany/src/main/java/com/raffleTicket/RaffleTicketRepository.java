package com.raffleTicket;

import com.promotionalPeriod.PromotionalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaffleTicketRepository extends JpaRepository<RaffleTicket, Long> {
     List<RaffleTicket> findAllByPromotionalPeriod(PromotionalPeriod promotionalPeriod);
}
