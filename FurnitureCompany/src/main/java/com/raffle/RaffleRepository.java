package com.raffle;

import com.prize.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {

    //List<Prize> getPrizesByPromotionalPeriod(Raffle raffle);
}
