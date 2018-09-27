package com.utils;

import com.prize.Prize;
import com.purchase.Purchase;
import com.raffle.BuyerChance;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RaffleUtils {

    public static String generateRaffleCode(Purchase purchase) {
        //TODO: implement logic to get a unique code for every chance
        String uniqueCode = StringUtils.EMPTY;
        uniqueCode.concat(purchase.getBuyer().getBuyerName());
        uniqueCode.concat(purchase.getFurniture().getFurnitureId().toString());
        uniqueCode.concat(purchase.getPurchaseDate().toString());
        return StringUtils.EMPTY;
    }

    public static List<BuyerChance> raffleWinnersPerPrize(List<BuyerChance> chancesPerPromotionalPeriod, List<Prize> prizes) {
        //TODO: logic for raffle prize
        List<BuyerChance> results = new ArrayList<>();
        BuyerChance chance = null;
        for (Prize prize:prizes) {
            int winnerLocation = (int) getRandomWinnerBetweenRange(0, chancesPerPromotionalPeriod.size()-1);
            chance = chancesPerPromotionalPeriod.get(winnerLocation);
            chance.setPrizeId(prize.getPrizeId());
            results.add(chance);
        }
        return results;
    }

    public static double getRandomWinnerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }
}
