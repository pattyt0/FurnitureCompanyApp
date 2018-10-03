package com.lineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
ResourceConfiguration.txt
Columns Order: furnitureId, furnitureCode, number of chances
 */
public class LineItemUtils {

    private static String filePath = "c:\\FurnitureCompanyApp\\ResourceConfiguration.txt";
    private static int FIXED_COLUMN_NUMBER = 3;

    public static Map<Long, Integer> getConfiguredChancePerFurnitureModel() {
        Map<Long, Integer> configurationTickets = new HashMap<>();
        File file = new File(filePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replaceAll("\\s+", "");
                String[] raffleCustomChance = line.split(",");

                if(raffleCustomChance.length == FIXED_COLUMN_NUMBER) {
                    configurationTickets.put(Long.valueOf(raffleCustomChance[0]), Integer.parseInt(raffleCustomChance[2]));
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return configurationTickets;
    }
}
