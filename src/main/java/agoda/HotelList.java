package agoda;

import java.util.*;
import java.util.stream.Collectors;

public class HotelList {
    /**
     * O(N), O(1) - SET
     * Hotel names:[
     * "Backpacker hostel",
     * "ABC Bangkok",
     * "Miracle grand at Bangkok hotel",
     * "Palace of grand masters",
     * "Grand palace hotel"
     * ]
     * <p>
     * <p>
     * input:"Backpacker" => expected output:["Backpacker hostel"]
     * input:"backpacker" => expected output:["Backpacker hostel"]
     * input:"backpack" => expected output:[]
     * input:"miracle bangkok" => expected output: ["Miracle grand at Bangkok hotel"]
     * input:"bangkok miracle" => expected output: ["Miracle grand at Bangkok hotel"]
     * input:"bangkok backpacker" => expected output:[]
     * input:"grand" => expected output: ["Miracle grand at Bangkok hotel", "Palace of grand masters", "Grand palace hotel"]
     */

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList(
                "Backpacker hostel",
                "ABC Bangkok",
                "Miracle grand at Bangkok hotel",
                "Palace of grand masters",
                "Grand palace hotel",
                "Siam Siam Hotel"));

//        Arrays.asList("Backpacker", "backpacker", "backpack", "miracle bangkok", "bangkok miracle", "bangkok backpacker", "grand", "siam siam")
//                .forEach(s -> {
//                    System.out.println("test for target: " + s);
//                    List<Integer> hotels = findHotelsByName(makeHotelList(input), s);
//                    for (int pos : hotels) {
//                        System.out.println(input.get(pos));
//                    }
//                    System.out.println("_____________________");
//                });
        Arrays.asList("Backpacker", "backpacker", "backpack", "miracle bangkok", "bangkok miracle", "bangkok backpacker", "grand", "siam siam")
                .forEach(s -> {
                    System.out.println("test for target: " + s);
                    List<String> hotels = findHotels(s, input);
                    System.out.println(hotels);
                    System.out.println("_____________________");
                });
    }

    //O(N*M) - time compl
    public static List<String> findHotels(String tmpl, List<String> hotelsList) {
        List<String> res = new ArrayList<>();
        Map<String, Set<String>> searchMap = new HashMap<>();
        for (String h : hotelsList) {
            // можно добавить кэширвоание
            searchMap.put(h, Arrays.stream(h.split(" ")).map(String::toLowerCase).collect(Collectors.toSet()));
        }
        //O(N) - N = hotelsList.length
        Set<String> tmplSet = Arrays.stream(tmpl.toLowerCase().split(" ")).collect(Collectors.toSet());
        for (Map.Entry<String, Set<String>> entry : searchMap.entrySet()) {
            if (entry.getValue().containsAll(tmplSet)) //O(M) , M = tmplSet.length
                res.add(entry.getKey());
        }
        return res;
    }

    public static List<Integer> findHotelsByName(Map<Integer, Set<String>> listOfHotels, String src) {
        List<Integer> positions = new ArrayList<>();

        if (src == null || src.length() == 0)
            return positions;

        int pos = 0;
        for (Set<String> row : listOfHotels.values()) {
            if (row.containsAll(Arrays.asList(src.toLowerCase(Locale.ROOT).split(" ")))) {
                positions.add(pos);
            }
            pos++;
        }
        return positions;
    }

    private static Map<Integer, Set<String>> makeHotelList(List<String> input) {
        Map<Integer, Set<String>> res = new HashMap<>();
        for (int pos = 0; pos < input.size(); pos++) {
            Set<String> nameByWords = new LinkedHashSet<>(
                    Arrays.asList(
                            input.get(pos)
                                    .toLowerCase(Locale.ROOT)
                                    .split(" ")))
                    ;
            res.put(pos, nameByWords);
        }
        return res;
    }
}
