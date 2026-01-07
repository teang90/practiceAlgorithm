package acrofuture.test_2023.sol1;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Sol2 {
    public static void main(String[] args) throws IOException {
        String dir = "D:\\ACRO_TYJEONG\\codingtest/data.log";
        List<String> allLines = Files.readAllLines(Paths.get(dir));
        Map<String, String> map = allLines.stream().collect(
                Collectors.groupingBy(
                        s->s.split(",")[0].split(":")[1].trim(),
                        Collectors.mapping(
                                s -> s.split(",")[1].split(":")[1].trim(),
                                Collectors.joining())
                )
        );

        int sumFromStream = map.values().stream().map(Integer::valueOf).reduce(0, Integer::sum);
        int minFromStream = map.values().stream().map(Integer::valueOf).reduce(Integer.MAX_VALUE, Integer::min);
        String minNameListFromStream = map.entrySet().stream().filter(e->Integer.valueOf(e.getValue())==minFromStream)
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.joining("&"));

        int maxFromStream = map.values().stream().map(Integer::valueOf).reduce(Integer.MIN_VALUE, Integer::max);
        String maxNameListFromStream = map.entrySet().stream().filter(e->Integer.valueOf(e.getValue())==maxFromStream)
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.joining("&"));

        String answerFromStream = String.format("{\"avg\":%d, \"min\":{%s:%d}, \"max\":{%s:%d}}", sumFromStream/map.size(), minNameListFromStream, minFromStream, maxNameListFromStream, maxFromStream);
        System.out.println(answerFromStream);

        String lastName = map.keySet().stream().sorted((o1, o2) -> o2.compareTo(o1)).findFirst().get();
        String answer2FromStream = String.format("{%s:%d}",lastName, Integer.valueOf(map.get(lastName)));
        System.out.println(answer2FromStream);


        System.out.println("-------------------- ------------ --------------------");
        System.out.println("-------------------- use for loop --------------------");
        System.out.println("-------------------- ------------ --------------------");

        BufferedReader br = new BufferedReader(new FileReader(dir));
        Map<String, Integer> scoreMap = new TreeMap<>();
        String line = "";
        while ((line=br.readLine())!=null) {
            String namePart = line.split(",")[0].split(":")[1].trim().replace("\"","");
            int scorePart = Integer.valueOf(line.split(",")[1].split(":")[1].trim());
            scoreMap.put(namePart, scorePart);
        }
        int _avg = scoreMap.values().stream().reduce(0, Integer::sum)/ scoreMap.size();
        int _min = scoreMap.values().stream().reduce(Integer.MAX_VALUE, Integer::min);
        String minList = scoreMap.entrySet().stream()
                            .filter(e->Integer.valueOf(e.getValue())==_min)
                            .map(e->String.valueOf(e.getKey()))
                            .collect(Collectors.joining("&"));
        int _max = scoreMap.values().stream().reduce(Integer.MIN_VALUE, Integer::max);
        String maxList = scoreMap.entrySet().stream()
                            .filter(e->Integer.valueOf(e.getValue())==_max)
                            .map(e->String.valueOf(e.getKey()))
                            .collect(Collectors.joining("&"));

        String answer = String.format("{\"avg\":%d, \"min\":{\"%s\":%d}, \"max\":{\"%s\":%d}}", _avg, minList, _min, maxList, _max);
        System.out.println(answer);

        String lastkey = new ArrayList<>(scoreMap.keySet()).get(scoreMap.size()-1);
        String answer2 = String.format("{\"%s\":%d}",lastkey, scoreMap.get(lastkey));
        System.out.println(answer2);
    }
}
