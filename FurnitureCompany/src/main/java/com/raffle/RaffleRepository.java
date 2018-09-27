package com.raffle;

import com.prize.Prize;
import com.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    @Query(value = "SELECT P.purchase_id, P.purchase_date, P.buyer_id, P.furniture_id FROM PURCHASE P WHERE P.PURCHASE_DATE >= %?1 AND P.PURCHASE_DATE < %?2", nativeQuery = true)
    List<Purchase> findAllByPurchasesTimeBetween(String promotionalPeriodStartDate, String promotionalPeriodEndDate);
}
