package com.mangkyu.stream.Quiz1;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Quiz1 {

    private static final String TARGET = "좋아";

    public Map<String, Integer> quiz1() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        List<String[]> csvLines = readCsvLines();

        Stream<String[]> stream = csvLines.stream();
        return stream.map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(h -> h, h -> 1, (o, n) -> n += o));
    }

    public Map<String, Integer> quiz2() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
                .filter(t -> t[0].startsWith("정"))
                .map(t -> t[1].replaceAll("\\s", ""))
                .flatMap(t -> Arrays.stream(t.split(":")))
                .collect(Collectors.toMap(h -> h, h -> 1, (o, n) -> n += o));
    }

    public int quiz3() throws IOException {
        log.info("get connection");
        log.info("quiz3 start");
        List<String[]> csvLines = readCsvLines();
        csvLines.stream()
                .map(t -> countTargetWord(t[2], 0))
                .peek(System.out::println)
                ;
        return 0;
    }

    private Integer countTargetWord(String target, int fromIndex) {
        System.out.println("");
        int index = target.indexOf(TARGET, fromIndex);
        if (index > 0) {
            return 1 + countTargetWord(target, index + TARGET.length());
        }
        return 0;
    }

    private List<String[]> readCsvLines()  {
        List<String[]> lines = new ArrayList<>();
        File csvFile = new File(getClass().getResource("/user.csv").getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();

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
        int i = q.quiz3();
    }

}
