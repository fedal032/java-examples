package agoda;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    /**
     * Hotel names:
     * [
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
        List<String> test = Arrays.asList("Backpacker hostel", "ABC Bangkok", "Miracle grand at Bangkok hotel", "Palace of grand masters", "Palace of grand masters", "Grand palace hotel");
        System.out.println(findHotels("bangkok miracle",  test));
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
}
