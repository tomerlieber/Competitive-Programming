import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class CouponCodes {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();


        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        HashSet<String> coupons = new HashSet<>();

        for (int i = 0; i < N; i++) {

            coupons.add(in.next());
        }

        int counter = 0;

        for (String coupon : coupons) {

            for (int i = 0; i < coupon.length(); i++) {

                if (i == 4  || i == 9) {
                    continue;
                }

                for (int j = 0; j < abc.length() ; j++) {

                    char charToReplaced = abc.charAt(j);
                    if (charToReplaced == coupon.charAt(i)) {
                        continue;
                    }

                    String changedCoupon = coupon.substring(0 , i) + charToReplaced + coupon.substring(i + 1);
                    if (coupons.contains(changedCoupon)) {
                        counter++;
                    }

                }
            }
        }

        System.out.println(counter / 2);

    }

    // function to calculate Hamming distance
    private static boolean hammingDist(String str1, String str2)
    {
        int i = 0, count = 0;
        while (i < str1.length())
        {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;

                if (count > 1) {
                    return false;
                }
            }

            i++;
        }

        return true;
    }


    private static void setStandardInput() {

        String input = "6\n" +
                "WELC-OMET-OTHE\n" +
                "IEEE-XTRE-ME14\n" +
                "AAAA-0000-A0A0\n" +
                "AAAA-0000-A0A1\n" +
                "AAAA-0000-A0AB\n" +
                "AAAA-0000-ABAB";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
