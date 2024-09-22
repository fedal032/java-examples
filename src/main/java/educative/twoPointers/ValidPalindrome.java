package educative.twoPointers;


public class ValidPalindrome
{
    public static void main(String[] args)
    {
        System.out.println("is polyndrome = " + isPalindrome2("ab_a"));
        System.out.println("is polyndrome = " + isPalindrome3("ab_A"));
    }

    private static boolean isPalindromeMySolution(String s)
    {
        boolean chet = s.length() % 2 == 0;

        int i;
        int j = s.length();
        char[] array = s.toCharArray();
        for (i = 0; i < (chet ? (s.length()) / 2 : (s.length() - 1) / 2); i++)
        {
            if (array[i] != array[j - 1 - i])
                return false;
        }
        return true;
    }

    public static boolean isPalindrome(String s)
    {
        int left = 0;
        int right = s.length() - 1;
        while (left < right)
        {
            if (s.charAt(left) != s.charAt(right))
            {
                return false;
            }
            left = left + 1;
            right = right - 1;
        }
        return true;
    }

    public static boolean isPalindrome2(String s)
    {
        if(s.isEmpty())
            return true;

        String trimmedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int startPointer = 0;
        int endPointer = trimmedString.length() - 1;

        while(startPointer < endPointer) {
            if(trimmedString.charAt(startPointer) != trimmedString.charAt(endPointer))
                return false;

            startPointer++;
            endPointer--;
        }
        return true;
    }

    public static boolean isPalindrome3(String s)
    {
        if (s.isEmpty())
            return true;

        char[] chars = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();

        int startPointer = 0;
        int endPointer = chars.length - 1;


        while (startPointer < endPointer)
        {
            if (chars[startPointer] != chars[endPointer])
                return false;

            startPointer++;
            endPointer--;
        }
        return true;
    }

    //public boolean isPalindrome(String s) {
    //        if (s.isEmpty()) {
    //        	return true;
    //        }
    //        int start = 0;
    //        int last = s.length() - 1;
    //        while(start <= last) {
    //        	char currFirst = s.charAt(start);
    //        	char currLast = s.charAt(last);
    //        	if (!Character.isLetterOrDigit(currFirst )) {
    //        		start++;
    //        	} else if(!Character.isLetterOrDigit(currLast)) {
    //        		last--;
    //        	} else {
    //        		if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
    //        			return false;
    //        		}
    //        		start++;
    //        		last--;
    //        	}
    //        }
    //        return true;
    //    }
}
