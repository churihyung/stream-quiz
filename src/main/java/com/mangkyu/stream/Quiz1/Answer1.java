package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Answer1 {

    private static final String TARGET = "좋아";
    private static final int TARGET_LENGTH = TARGET.length();

    public Map<String, Integer> quiz1() throws IOException {
        // https://jeong-pro.tistory.com/212
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        List<String[]> csvLines = csvReader.readAll();

        return csvLines.stream()
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .peek(s-> System.out.println(s))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));
    }

    public Map<String, Integer> quiz2() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        List<String[]> csvLines = csvReader.readAll();

        return csvLines.stream()
                .filter(line -> line[0].startsWith("정"))
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> ++newValue));
    }

    public int quiz3() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        List<String[]> csvLines = csvReader.readAll();

        return csvLines.stream()
                .map(line -> countContains(line[2], 0))
                .reduce(0, Integer::sum);
    }

    private int countContains(String src, int fromIndex) {
        int index = src.indexOf(TARGET, fromIndex);
        if (index >= 0) {
            return 1 + countContains(src, index + TARGET_LENGTH);
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Answer1 a = new Answer1();
        int i = a.quiz3();
        System.out.println("here!!!!!");

    }

}
