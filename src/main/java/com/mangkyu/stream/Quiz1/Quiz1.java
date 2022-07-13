package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz1 {

    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();

        Stream<String[]> stream = csvLines.stream();
        return stream.map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies-> Arrays.stream(hobbies.split(":")))
                .peek(s -> System.out.println(s))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));
    }

    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return new HashMap<>();
    }

    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return 0;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

    public static void main(String[] args) throws IOException {
        Quiz1 q = new Quiz1();
        q.quiz1();
    }

}
