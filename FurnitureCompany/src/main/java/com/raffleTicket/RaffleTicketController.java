package com.raffleTicket;

import com.prize.Prize;
import com.prize.PrizeService;
import com.promotionalPeriod.PromotionalPeriod;
import com.promotionalPeriod.PromotionalPeriodService;
import com.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RaffleTicketController {

    private RaffleTicketRepository raffleRepository;

    @Autowired
    private PromotionalPeriodService promotionalPeriodService;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    public RaffleTicketController(RaffleTicketRepository raffleRepository){
        this.raffleRepository = raffleRepository;
    }

    /*
    Pick winners given a promotional period Id
     */
    @RequestMapping(value= "/Raffle/{promotionalPeriodId}/Run", method= RequestMethod.GET)
    public ResponseEntity<List<Prize>> runRaffle(@PathVariable Long promotionalPeriodId){

        //Get promotional period for {promotionalPeriodId}
        Optional<PromotionalPeriod> promotionalPeriod = promotionalPeriodService.getPromotionalPeriodById(promotionalPeriodId);

        //TODO:Get list of prizes of promotional period
        List<Prize> prizes = prizeService.getPrizesByPromotionalPeriod(promotionalPeriod.get());
        //Get list of chances of buyers per promotional period
//        List<Purchase> currentChancesOfPromotionalPeriod = new ArrayList<>();
//        //1. Generate list of chances
//        List<BuyerChance> chancesPerPromotionalPeriod = generateListOfChances(currentChancesOfPromotionalPeriod);
//        //2. Raffle
//        List<BuyerChance> winners = RaffleUtils.raffleWinnersPerPrize(chancesPerPromotionalPeriod, prizes);
//
//        //Save winners per promotional period
//        PromotionalPeriod promotionalPeriod = null;
//        Buyer buyer = null;
//        Prize prize = null;
//        for (BuyerChance buyerChance:winners) {
//            raffleTicket = new Raffle();
//
//            promotionalPeriod = new PromotionalPeriod(promotionalPeriodId);
//            raffleTicket.setPromotionalPeriod(promotionalPeriod);
//            buyer = new Buyer(buyerChance.getBuyerId());
//            raffleTicket.setPurchase(buyer);
//            prize = new Prize(buyerChance.getPrizeId());
//            raffleTicket.setPrize(prize);
//
//            raffleRepository.save(raffleTicket);
//        }

//        return new ResponseEntity<>(winners, HttpStatus.OK);
//        return new ResponseEntity<>(promotionalPeriodId, HttpStatus.OK);
        return new ResponseEntity<>(prizes, HttpStatus.OK);
    }

//    @RequestMapping(value= "/", method= RequestMethod.POST)
//    public ResponseEntity<List<BuyerChance>> runRaffle(@RequestBody Raffle[] raffleTicket){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        //Returns just promotional period Id
//        Raffle raffleResult = new Raffle();
//        Long promotionalPeriodId = raffleTicket[0].getPromotionalPeriod().getPromotionalPeriodId();
//        String promotionalPeriodStartDate = raffleTicket[0].getPromotionalPeriod().getPromotionStart();
//        String promotionalPeriodEndDate = raffleTicket[0].getPromotionalPeriod().getPromotionEnd();
//        List<Prize> prizes = new ArrayList<>();
//        //Fill with request prizes
//        for (Raffle rafflePrize:raffleTicket) {
//            prizes.add(rafflePrize.getPrize());
//        }
//        //Get list of chances of buyers per promotional period
//        //query  requesting purchases in a date range from promotional period
//        //TODO:update get purchases query
////        List<Purchase> currentChancesOfPromotionalPeriod = raffleRepository.findAllByPurchasesTimeBetween(promotionalPeriodStartDate, promotionalPeriodEndDate);
//        List<Purchase> currentChancesOfPromotionalPeriod = new ArrayList<>();
//        //1. Generate list of chances
//        List<BuyerChance> chancesPerPromotionalPeriod = generateListOfChances(currentChancesOfPromotionalPeriod);
//        //2. Raffle
//        List<BuyerChance> winners = RaffleUtils.raffleWinnersPerPrize(chancesPerPromotionalPeriod, prizes);
//
//        //Save winners per promotional period
//        PromotionalPeriod promotionalPeriod = null;
//        Buyer buyer = null;
//        Prize prize = null;
//        for (BuyerChance buyerChance:winners) {
//            raffleResult = new Raffle();
//
//            promotionalPeriod = new PromotionalPeriod(promotionalPeriodId);
//            raffleResult.setPromotionalPeriod(promotionalPeriod);
//            buyer = new Buyer(buyerChance.getBuyerId());
//            raffleResult.setPurchase(buyer);
//            prize = new Prize(buyerChance.getPrizeId());
//            raffleResult.setPrize(prize);
//
//            raffleRepository.save(raffleResult);
//        }
//
//        return new ResponseEntity<>(winners, HttpStatus.OK);
//    }

    /*
    Generate changes available per user for a promotional period
     */
    private List<RaffleTicket> generateListOfChances(List<Purchase> currentChancesOfPromotionalPeriod) {
        //TODO:Additional set of Buyer chances per promotional period
        List<RaffleTicket> finalChances = new ArrayList<>();

        for (Purchase purchase:currentChancesOfPromotionalPeriod) {
            List<RaffleTicket> buyerChances = getBuyerChancesPerFurnitureModel(purchase);
            finalChances.addAll(buyerChances);
        }
        return finalChances;
    }

    /*
    Buyer chances converter
     */
    private List<RaffleTicket> getBuyerChancesPerFurnitureModel(Purchase purchase) {
        List<RaffleTicket> results = new ArrayList<>();
        //get map of chances per model <furnitureId, chanceForFurnitureModel>
        //TODO: load from a configuration file, all furniture model should have number of chances
        Map<Long, Integer> chancesPerModel = new HashMap<>();
        chancesPerModel.put(Long.valueOf(2),2);
        chancesPerModel.put(Long.valueOf(1),1);

//        if(chancesPerModel.containsKey(purchase.getFurniture().getFurnitureId())){
//            int furnitureAvailableChances = chancesPerModel.get(purchase.getFurniture().getFurnitureId());
//            if(furnitureAvailableChances > 0){
//                //Ask for model chance
//                RaffleTicket buyerChance = null;
//                for (int i=0; i<furnitureAvailableChances; i++) {
////                    buyerChance = new RaffleTicket(purchase.getBuyer().getBuyerId(), RaffleUtils.generateRaffleCode(purchase));
//                    results.add(buyerChance);
//                }
//            }
//        }
        return results;
    }
}