package com.raffle.repository;

import com.raffle.dao.Participant;
import com.raffle.dao.PromotionalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByPromotionalPeriod(PromotionalPeriod promotionalPeriod);
//    List<Participant> findAllByBuyer(Buyer buyer);
//    List<Participant> findByBuyerAndPromotionalPeriod(Buyer buyer, PromotionalPeriod promotionalPeriod);

    List<Participant> findAllByPromotionalPeriodAndPrizeNotNull(PromotionalPeriod promotionalPeriod);
}
