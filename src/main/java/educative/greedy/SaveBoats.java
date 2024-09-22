package educative.greedy;

import java.util.Arrays;

public class SaveBoats {

    public static void main(String[] args) {
        System.out.println(rescueBoats(new int[] {3,1,4,2,4}, 4));
    }

    public static int rescueBoats(int[] people, int limit) {
        //[3,1,4,2,4]
        //1, 2, 3, 4, 4
        // 4
        //
        Arrays.sort(people); // n log n - time and O(n) - sort space compl
        int l = 0;
        int r = people.length - 1;
        int bc = 0;

        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;
                r--;
            }
            else {
                r--;
            }
            bc++;
        }

        return bc;
    }
}
