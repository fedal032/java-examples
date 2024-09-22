package educative.slidingWindow;

import java.util.*;

public class LongestNotRepSeq
{
    public static void main(String[] args)
    {
        System.out.println(findLongestSubstring("abcdbea"));
        System.out.println(lengthOfLongestSubstring("abcdbea"));
        System.out.println("----");

        System.out.println(findLongestSubstring("aba"));
        System.out.println(lengthOfLongestSubstring("aba"));
        System.out.println("----");

        System.out.println(findLongestSubstring("abccabcabcc"));
        System.out.println(lengthOfLongestSubstring("abccabcabcc"));
        System.out.println("----");

        System.out.println(findLongestSubstring("aaaabaaa"));
        System.out.println(lengthOfLongestSubstring("aaaabaaa"));
        System.out.println("----");

        System.out.println(findLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println("----");

        System.out.println(findLongestSubstring("cdd"));
        System.out.println(lengthOfLongestSubstring("cdd"));
        System.out.println("----");
    }

    public static int findLongestSubstring(String s)
    {
        if (s == null || s.length() == 0)
            return 0;
        //      i
        //a b c a b c b b
        //0 1 2 3 4

        int maxLength = 0;
        int winStart = 0;
        int winLen;
        int i;
        char[] cha = s.toCharArray();
        Map<Character, Integer> lastSeen = new HashMap<>();

        for (i = 0; i < cha.length; i++)
        {
            if (!lastSeen.containsKey(cha[i]))
            {
                lastSeen.put(cha[i], i);
            }
            else
            {
                // 0 1 2
                // a b b a
                // 0 1 2 3
                // дубликат, но нужно быть уверенным, что он после начала окна (дальше мы будем заменять это дубликат)
                if (lastSeen.get(cha[i]) >= winStart) // в данном случае у нас ранее было "a"
                {
                    winLen = i - winStart;
                    maxLength = Math.max(maxLength, winLen);
                    winStart = lastSeen.get(cha[i]) + 1;
                }
                // для вычислений нужно заменить индекс, он уже не входит в окно новое
                lastSeen.replace(cha[i], i);
            }
        }
        //нужно учесть остаток
        return Math.max(maxLength, i - winStart);
    }

    private static ArrayList<Character> removeAllBefore(ArrayList<Character> list, Character character)
    {
        int index = list.indexOf(character);
        for (int i = 0; i <= index; i++)
        {
            list.remove(0);
        }
        return list;
    }

    public static int lengthOfLongestSubstring(String s)
    {
        int curSeqLength = 0;
        ArrayList<Character> arrayList = new ArrayList<>();

        for (int i = 0; i < s.length(); i++)
        {
            Character character = s.charAt(i);
            if (arrayList.contains(character))
            {
                if (curSeqLength < arrayList.size())
                    curSeqLength = arrayList.size();

                arrayList = removeAllBefore(arrayList, character);
            }

            arrayList.add(character);
        }

        return Math.max(arrayList.size(), curSeqLength);
    }
}
