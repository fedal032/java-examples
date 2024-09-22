package leetcode.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastBricks {

    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer /*sum*/, Integer /*count*/> map = new HashMap<>();

        for (List<Integer> row : wall) {
            int sum = 0;
            // считаем кирпичи (последний не берем)
            for (int i = 0; i < row.size() - 1; i++ ) {
                sum += row.get(i);
                if (map.containsKey(sum))
                    map.put(sum, map.get(sum) + 1); // обновляем, сколько раз встречали
                else
                    map.put(sum, 1);
            }
        }

        int res = wall.size(); //все кирпичи перескаем
        for (int k : map.keySet()) { // все ключи
            // минимум между всеми и кол-вом одинковой длинны
            // что будет равно максимальному кол-ву строк одинаковый длинны
            res  = Math.min(res, wall.size() - map.get(k));
        }
        return res;
    }

}
