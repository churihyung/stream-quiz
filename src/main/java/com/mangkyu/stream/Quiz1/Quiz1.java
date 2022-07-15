package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz1 {

    public Map<String, Integer> quiz1() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        List<String[]> csvLines = readCsvLines();

        Stream<String[]> stream = csvLines.stream();
        List<String> list = stream.map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toList());

        for (String s : list) {
            map.put(s, map.getOrDefault(s,0)+1);
        }
        return map;

    }

    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return new HashMap<>();
    }

    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return 0;
    }

    private List<String[]> readCsvLines()  {

//        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
//        csvReader.readNext();
//        return csvReader.readAll();

        List<String[]> lines = new ArrayList<>();

        File csvFile = new File("D:\\github\\stream-quiz\\src\\main\\resources\\user.csv");


        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//            br = new BufferedReader(new FileReader(csvFile));
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    public static void main(String[] args) throws IOException {
        Quiz1 q = new Quiz1();
        Map<String, Integer> stringIntegerMap = q.quiz1();
        Set<String> strings = stringIntegerMap.keySet();
        for (String string : strings) {
            System.out.println("key : "+ string + "  value : " + stringIntegerMap.get(string));
        }
    }

}
