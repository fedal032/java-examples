package educative.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindRepeatedSequences
{
    public static void main(String[] args)
    {
        Set<String> set = findRepeatedSequences("AAAAACCCCCAAAAACCCCCC", 8);
        System.out.printf("Set size = %d%n", set.size());
        Set<String> set2 = findRepeatedSequencesFast("AAAAACCCCCAAAAACCCCCC", 8);
        System.out.printf("Set2 size = %d%n", set2.size());
    }

    public static Set<String> findRepeatedSequences(String s, int k)
    {
        Set<String> result = new HashSet<>();
        Set<String> temp = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; i++)
        {
            String substring = s.substring(i, i + k - 1);
            if (!temp.contains(substring))
                temp.add(substring);
            else
                result.add(substring);
        }

        return result;
    }

    public static Set<String> findRepeatedSequencesFast(String s, int k)
    {
        Set<String> output = new HashSet<>();
        int n = s.length();

        if (k > n)
            return output;

        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', 1);
        mapping.put('C', 2);
        mapping.put('G', 3);
        mapping.put('T', 4);

        //base
        int a = 4;

        List<Integer> numbers = new ArrayList<>(s.length());
        for (int i = 0; i < n; i++)
        {
            numbers.add(mapping.get(s.charAt(i)));
        }

        int currentHashValue = 0;

        Set<Integer> hashes = new HashSet<>();
        for (int i = 0; i < n - k + 1; i++)
        {
            if (i == 0)
                for (int j = 0; j < k; j++)
                {
                    currentHashValue += numbers.get(j) * Math.pow(a, k - j - 1);
                }
            else
            {
                int prevHashValue = currentHashValue;
                currentHashValue = ((prevHashValue - numbers.get(i-1)* (int) Math.pow(a, k-1))*a) + numbers.get(i + k-1);
            }

            if (hashes.contains(currentHashValue))
                output.add(s.substring(i, i+k));

            hashes.add(currentHashValue);
        }


        return output;
    }
}
