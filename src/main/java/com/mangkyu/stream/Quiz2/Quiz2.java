package com.mangkyu.stream.Quiz2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz2 {

    private static List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    public Map<String, Integer> quiz1() {
        return WORDS.stream()
                .map(word -> word.substring(0,1))
                .collect(Collectors.toMap(s -> s, s -> 1, (oldValue, newValue) -> newValue += oldValue))
                ;
    }

    public String quiz2() {
        return WORDS.stream()
                .filter(word -> word.length() > 1)
                .map(word -> word.substring(0,1).toUpperCase())
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Quiz2 quiz2 = new Quiz2();
        String s = quiz2.quiz2();
        System.out.println("s = " + s);
    }
}
