package com.example.mil_app.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Random;

public class CSVController {
    /**
     * Amount of rows associated with question in each individual .csv file.
     */
    private static final int LINES_IN_CSV = 10;
    private static final String SPLIT_DELIM = ";";

    /**
     * @param csvNumber Which csv file.
     * @return {@link Question} object
     */
    public static Question getRandomQuestion(int csvNumber) {
        try {
            System.out.println("CSV number: " + csvNumber);
            URL csvFileLocation = CSVController.class.getResource(csvNumber + ".csv");
            System.out.println("CSV resource location url: " + csvFileLocation);
            File CSVFile = new File(csvFileLocation.getPath());
            BufferedReader br = new BufferedReader(new FileReader(CSVFile));
            int rand = new Random().nextInt(LINES_IN_CSV); // random from 0 to lines-1
            for (int i = 0; i <= rand - 1; i++) {
                br.readLine();
            }
            String line = br.readLine();
            br.close();
            String[] record = line.split(SPLIT_DELIM);
            // question;answer1;answer2;answer3;answer4;correct
            String question = record[0];
            byte correct = Byte.parseByte(record[5]);
            String[] answersArray = new String[4];
            System.arraycopy(record, 1, answersArray, 0, 4);
            int questionNumber = csvNumber;
            return new Question(questionNumber, question, answersArray, correct,
                    getPrize(csvNumber));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static int getPrize(int stageNumber) {
        switch (stageNumber) {
            case 1:
                return 500;
            case 2:
                return 1000;
            case 3:
                return 2000;
            case 4:
                return 5000;
            case 5:
                return 10000;
            case 6:
                return 20000;
            case 7:
                return 40000;
            case 8:
                return 75000;
            case 9:
                return 125000;
            case 10:
                return 250000;
            case 11:
                return 500000;
            case 12:
                return 1000000;
        }
        return stageNumber;
    }
}
