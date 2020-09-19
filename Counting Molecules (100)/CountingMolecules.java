import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CountingMolecules {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        long c = in.nextLong();
        long h = in.nextLong();
        long o = in.nextLong();

        /*
            1 water is made of 2 atoms of hydrogen and 1 atom of oxygen.
            1 carbon is made of 1 atom of carbon and 2 atoms of oxygen.
            1 glucose is made of 6 atoms of carbon, 12 atoms of hydrogen and 6 atoms of oxygen.
            wm: The number of waters.
            cm: The number of carbons.
            gm: The number of glucose.
            ----------------------------------
            c = cm + 6 * gm
            -----------------
           | cm = c - 6 * gm |
            -----------------
            o = wm + 2 * cm + 6 * gm
            o = wm + 2 * (c - 6 * gm) + 6 * gm
            o = wm + 2 * c - 12 * gm + 6 * gm
            o = wm + 2 * c - 6 * gm
            -------------------------
           | wm = o - 2 * c + 6 * gm |
            -------------------------
            h = 2 * wm + 12 * gm
            h = 2 * (o - 2 * c + 6 * gm) + 12 * gm
            h = 2 * o - 4 * c + 12 * gm + 12 * gm
            h = 2 * o - 4 * c + 24 * gm
            -------------------------------
           | gm = (h - 2 * o + 4 * c) / 24 |
            -------------------------------
        */

        long gm = (h - (2 * o) + (4 * c)) / 24;
        long cm = c - 6 * gm;
        long wm = o - 2 * c + 6 * gm;

        long cc = cm + 6 * gm;
        long hh = 2 * wm + 12 * gm;
        long oo = wm + 2 * cm + 6 * gm;

        if (cc != c || hh != h || oo != o) {
            System.out.println("Error");
        }
        else {
            System.out.println(wm + " " + cm + " " + gm);
        }
    }

    private static void setStandardInput() {

        String input = "10 0 20";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
