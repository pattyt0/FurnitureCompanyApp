package com.raffleTicket;

import com.prize.Prize;
import com.purchase.Purchase;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RaffleUtils {
    static String filePath = "C:\\FurnitureCompanyApp\\ResourceConfiguration.txt";

    public static String generateRaffleCode(Purchase purchase) {
        //TODO: implement logic to get a unique code for every chance
        String uniqueCode = StringUtils.EMPTY;
        uniqueCode.concat(purchase.getBuyer().getFirstName());
//        uniqueCode.concat(purchase.getFurniture().getFurnitureId().toString());
        uniqueCode.concat(purchase.getPurchaseDate().toString());
        return StringUtils.EMPTY;
    }

    public static List<RaffleTicket> raffleWinnersPerPrize(List<RaffleTicket> chancesPerPromotionalPeriod, List<Prize> prizes) {
        //TODO: logic for raffleTicket prize
        List<RaffleTicket> results = new ArrayList<>();
        RaffleTicket chance = null;
        for (Prize prize:prizes) {
            int winnerLocation = (int) getRandomWinnerBetweenRange(0, chancesPerPromotionalPeriod.size()-1);
            chance = chancesPerPromotionalPeriod.get(winnerLocation);
//            chance.setPrizeId(prize.getPrizeId());
            results.add(chance);
        }
        return results;
    }

    public static double getRandomWinnerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

    public static Map<Long, Integer> loadConfigurationChanges() {
        Map<Long, Integer> configurationTickets = new HashMap<>();
        File file = new File(filePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //TODO: in progress
                String[] furnitureModel;

            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return configurationTickets;
    }
}
