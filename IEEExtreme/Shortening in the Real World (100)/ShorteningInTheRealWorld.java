import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ShorteningInTheRealWorld {

    private static String base62alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String base16alphabet = "0123456789ABCDEF";
    ;

    public static void main(String[] args) {

        try {

            // Uncomment the following line in order to simulate a specific input
            // setStandardInput();

            Scanner in = new Scanner(System.in);

            String baseURL = in.nextLine();
            byte[] baseUrlBytes = baseURL.getBytes("UTF-8");

            int n = in.nextInt();
            in.skip("\n");

            for (int i = 0; i < n; i++) {

                // Find the UTF-8 encoding of target URL
                String targetURL = in.nextLine();
                byte[] targetUrlBytes = targetURL.getBytes("UTF-8");

                // Apply the exclusive-or cipher.
                // In addition, Repeating the bytes in the base URL so that the two strings are the same length.
                byte[] exclusiveOR = new byte[targetUrlBytes.length];
                for (int j = 0; j < targetUrlBytes.length; j++) {
                    exclusiveOR[j] = (byte) (targetUrlBytes[j] ^ baseUrlBytes[j % baseUrlBytes.length]);
                }

                // Take the last 8 bytes
                byte[] last8Bytes = new byte[8];
                for (int j = 0; j < 8; j++) {
                    last8Bytes[j] = exclusiveOR[j + (exclusiveOR.length - 8)];
                }

                // Convert it to an unsigned integer
                String hexadecimal = convertToHexadecimal(last8Bytes);
                long unsignedInteger = Long.parseLong(hexadecimal, 16);

                // Convert the number from base 10 to base 62.
                String result = convertFromBase10to64(unsignedInteger);

                System.out.println(baseURL + "/" + result);
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static String convertFromBase10to64(long index) {

        StringBuilder stringBuilder = new StringBuilder();

        while (index > 0) {
            index = convertFromBase10to64(index, stringBuilder);
        }

        return stringBuilder.reverse().toString();
    }

    private static long convertFromBase10to64(long index, final StringBuilder stringBuilder) {

        long reminder = index % 62;
        stringBuilder.append(base62alphabet.charAt((int) reminder));
        return index / 62;
    }


    private static String convertToHexadecimal(byte[] bytes) {

        char[] hexadecimalChars = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {

            int value = bytes[j] & 0xFF;
            hexadecimalChars[j * 2] = base16alphabet.charAt(value >>> 4);
            hexadecimalChars[j * 2 + 1] = base16alphabet.charAt(value & 0x0F);
        }

        return new String(hexadecimalChars);
    }

    private static void setStandardInput() {

        String input = "http://www.ieee.com\n" +
                "2\n" +
                "http://www.ieee.org/xtreme\n" +
                "http://www.ieee.org/membership_services/membership/young_professionals/index.html";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}