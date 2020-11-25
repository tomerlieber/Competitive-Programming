import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SumOfFourValues {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The array size
        int x = in.nextInt(); // The target sum

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        ArrayList<Triple> aux = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                aux.add(new Triple(nums[i] + nums[j], i + 1, j + 1));
            }
        }

        Collections.sort(aux);

        // Fix the first and seconds elements one by one and find the other two elements.

        // To find the other two elements, start two index variables from
        // two corners of the array and move them toward each other
        int i = 0; // index of the first element in the remaining elements
        int j = aux.size() - 1; // index of the last element
        while (i < j) {

            if (aux.get(i).val + aux.get(j).val == x &&
                    aux.get(i).index1 != aux.get(j).index1 && aux.get(i).index1 != aux.get(j).index2 &&
                    aux.get(i).index2 != aux.get(j).index1 && aux.get(i).index2 != aux.get(j).index2) {

                System.out.println(String.format("%d %d %d %d",
                        aux.get(i).index1, aux.get(i).index2, aux.get(j).index1, aux.get(j).index2));
                return;
            } else if (aux.get(i).val + aux.get(j).val < x) {
                i++;
            } else {
                j--;
            }
        }

        System.out.println("IMPOSSIBLE");

    }

    static class Triple implements Comparable<Triple> {
        int val;
        int index1;
        int index2;

        Triple(int val, int index1, int index2) {
            this.val = val;
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public int compareTo(Triple o) {
            return Integer.compare(val, o.val);
        }
    }

    private static void setStandardInput() {

        String input = "8 15\n" +
                "3 2 5 8 1 3 2 3";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String fileName) throws IOException {
            din = new DataInputStream(new FileInputStream(fileName));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}