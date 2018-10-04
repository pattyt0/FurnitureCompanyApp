package com.participant;

import com.promotionalPeriod.PromotionalPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
     List<Participant> findAllByPromotionalPeriod(PromotionalPeriod promotionalPeriod);
}
