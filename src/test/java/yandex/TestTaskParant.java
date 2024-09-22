package yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class TestTaskParant
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Set<String> result = new HashSet<>();
        String n = r.readLine();
        backTrack(result, new StringBuilder(), 0, 0, Integer.parseInt(n));
        System.out.println(result);
    }

    private static void backTrack(Set<String> res, StringBuilder current, int open, int close, int max) {
        if (open == max && close == max) {
            res.add(current.toString());
            return;
        }

        if (open < max) {
            backTrack(res, current.append("("), open + 1, close, max);
            current.delete(current.length() - 1, current.length());
        }

        if (close < open) {
            backTrack(res, current.append(")"), open, close + 1, max);
            current.delete(current.length() - 1, current.length());
        }

    }
}
