package educative.slowAndFast;

public class HappyNumber
{
    public static void main(String[] args)
    {
        System.out.println("is happy number: " + checkHappyNumber(4));
        System.out.println("is happy number: " + checkHappyNumber(7));
        System.out.println("is happy number: " + checkHappyNumber(28));
        System.out.println("is happy number: " + checkHappyNumber(19));
    }

    private static boolean checkHappyNumber(int n)
    {
        int slow = n;
        int fast = getSqSum(n);

        while(fast != 1 && fast != slow) {
            slow = getSqSum(slow);
            fast = getSqSum(getSqSum(fast));
        }
        return fast == 1;
    }

    private static int getSqSum(int input)
    {
        String strNumber = Integer.toString(input);
        int res = 0;
        for (int i = 0; i < strNumber.length(); i++)
        {
            int num = Integer.parseInt(String.valueOf(strNumber.charAt(i)));
            res += Math.pow(num, 2);
        }
        return res;
    }

}
