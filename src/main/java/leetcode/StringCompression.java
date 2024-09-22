package leetcode;

import java.util.Arrays;

public class StringCompression
{
    public static void main(String[] args)
    {
        char[] ch1 = {'a','a','b','b','c','c','c'};
        char[] ch2 = {'a'};
        char[] ch3 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};

        System.out.println(compress(ch1));
        System.out.println("-----------------------");
        System.out.println(compress(ch2));
        System.out.println("-----------------------");
        System.out.println(compress(ch3));
        System.out.println("-----------------------");
    }

    public static int compress(char[] chars) {
        int index = 1;
        int count = 0;
        char prev = chars[0];

        for (char c : chars)
        {
            if(c == prev ) {
                count++;
                System.out.println("count = " + count);
            }
            else {
                if (count > 1) {
                    for (char ic : Integer.toString(count).toCharArray()) {
                        System.out.println("index  = " + index);
                        chars[index++] = ic;
                        System.out.println("index  = " + index + " chars[index++] = " + ic);
                    }
                }
                count = 1;
                System.out.println("index  = " + index);
                chars[index++] = c;
                System.out.println("index  = " + index + " chars[index++] = " + c);

            }
            prev = c;
        }

        if (count > 1) {
            for (char ic : Integer.toString(count).toCharArray()) {
                chars[index++] = ic;
            }
        }

        return index;
    }
}
