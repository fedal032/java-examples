package yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TestTask
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
       // int result = 0;
        Deque<String> seq = new ArrayDeque<>();
        String n = r.readLine();
        String elem;
        for (int i = 0; i < Integer.parseInt(n); i++)
        {
            elem = r.readLine();
            if (seq.isEmpty() || !elem.equals(seq.peekLast()))
                seq.add(elem);

        }
        int size = seq.size();
        for (int i = 0; i < size; i++)
        {
            System.out.println(seq.removeFirst());
        }

        // недубликаты
//        int result = 0;
//        Stack<String> seq = new Stack<>();
//        String n = r.readLine();
//        String elem;
//        for (int i = 0; i < Integer.parseInt(n); i++)
//        {
//            elem = r.readLine();
//            if(seq.isEmpty() && "1".equals(elem)) {
//                seq.push(elem);
//                result = seq.size();
//                continue;
//            }
//
//            if("0".equals(elem))
//            {
//                if(seq.size() >= result)
//                    result = seq.size();
//                seq.clear();
//            }
//            else {
//                seq.push(elem);
//            }
//        }

        //вхождения в строку
//        String J = r.readLine(); //драгоценности
//        String S = r.readLine(); //камни
//        Set<Character> used = new HashSet<>();
//        int result = 0;
//        if (S.length()!= 0 || J.length() != 0)
//        {
//            for(char s : S.toCharArray())
//            {
//                if(used.contains(s) || J.indexOf(s) >= 0)
//                {
//                    result++;
//                    used.add(s);
//                }
//            }
//        }
        //System.out.println(seq.size() >= result ? seq.size() : result);
    }
}
