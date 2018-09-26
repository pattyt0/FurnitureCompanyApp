package com.raffle;

import com.buyer.Buyer;
import com.prize.Prize;
import com.promotionalPeriod.PromotionalPeriod;
import com.purchase.Purchase;
import com.utils.RaffleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RaffleController {

    private RaffleRepository raffleRepository;

    @Autowired
    public RaffleController(RaffleRepository raffleRepository){
        this.raffleRepository = raffleRepository;
    }

    /*
    Pick winners given a promotional period Id
     */
    @RequestMapping(value= "RaffleWinners", method= RequestMethod.POST)
    public ResponseEntity<List<BuyerChance>> raffleWinners(@RequestBody Raffle raffle){
        //Returns just promotional period Id
        Long promotionalPeriodId = raffle.getPromotionalPeriod().getPromotionalPeriodId();
        //TODO:Get list of prizes
        List<Prize> prizes = new ArrayList<>();//TODO: drop me
        //List<Prize> prizes = raffleRepository.getPrizesByPromotionalPeriod(raffle);
        //Get list of chances of buyers per promotional period
        List<Purchase> currentChancesOfPromotionalPeriod = new ArrayList<>();
        //1. Generate list of chances
        List<BuyerChance> chancesPerPromotionalPeriod = generateListOfChances(currentChancesOfPromotionalPeriod);
        //2. Raffle
        List<BuyerChance> winners = RaffleUtils.raffleWinnersPerPrize(chancesPerPromotionalPeriod, prizes);

        //Save winners per promotional period
        PromotionalPeriod promotionalPeriod = null;
        Buyer buyer = null;
        Prize prize = null;
        for (BuyerChance buyerChance:winners) {
            raffle = new Raffle();

            promotionalPeriod = new PromotionalPeriod(promotionalPeriodId);
            raffle.setPromotionalPeriod(promotionalPeriod);
            buyer = new Buyer(buyerChance.getBuyerId());
            raffle.setBuyer(buyer);
            prize = new Prize(buyerChance.getPrizeId());
            raffle.setPrize(prize);

            raffleRepository.save(raffle);
        }

        return new ResponseEntity<>(winners, HttpStatus.OK);
    }

    /*
    Generate changes available per user for a promotional period
     */
    private List<BuyerChance> generateListOfChances(List<Purchase> currentChancesOfPromotionalPeriod) {
        //TODO:Additional set of Buyer chances per promotional period
        List<BuyerChance> finalChances = new ArrayList<>();

        for (Purchase purchase:currentChancesOfPromotionalPeriod) {
            List<BuyerChance> buyerChances = getBuyerChancesPerFurnitureModel(purchase);
            finalChances.addAll(buyerChances);
        }
        return finalChances;
    }

    /*
    Buyer chances converter
     */
    private List<BuyerChance> getBuyerChancesPerFurnitureModel(Purchase purchase) {
        List<BuyerChance> results = new ArrayList<>();
        //get map of chances per model <furnitureId, chanceForFurnitureModel>
        Map<Long, Integer> chancesPerModel = new HashMap<>();

        int furnitureAvailableChances = chancesPerModel.get(purchase.getFurnitureId().getFurnitureId());
        if(furnitureAvailableChances > 0){
            //Ask for model chance
            BuyerChance buyerChance = null;
            for (int i=0; i<furnitureAvailableChances; i++) {
                buyerChance = new BuyerChance(purchase.getBuyer().getBuyerId(), RaffleUtils.generateRaffleCode(purchase));
                results.add(buyerChance);
            }
        }
        return results;
    }
}