import java.io.*;

public class RangeMinimumQueriesII {

    public static void main(String[] args) throws IOException {

        // Sources:
        // https://www.youtube.com/watch?v=ZBHKZF5w4YU&ab_channel=TusharRoy-CodingMadeSimple
        // https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SegmentTreeMinimumRangeQuery.java

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The number of values
        int q = in.nextInt(); // The number of queries

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] segmentTree = createMinSegmentTree(arr);

        for (int i = 1; i <= q; i++) {
            int type = in.nextInt();
            int num1 = in.nextInt();
            int num2 = in.nextInt();

            if (type == 1) {

                int index = num1 - 1;
                updateSegmentTree(arr, segmentTree, index, num2);


            } else if (type == 2) {
                int a  = num1 - 1;
                int b = num2 - 1;
                int minValue = rangeMinimumQuery(segmentTree, n, a, b);
                System.out.println(minValue);
            }
        }
    }

    static int[] createMinSegmentTree(int input[]) {

        // Height of the segment tree
        int height = (int) (Math.ceil(Math.log(input.length) / Math.log(2)));

        int[] segmentTree = new int[2 * (int) Math.pow(2, height) - 1];

        for (int i = 0; i < segmentTree.length; i++) {
            segmentTree[i] = Integer.MAX_VALUE;
        }
        createMinSegmentTree(segmentTree, input, 0, input.length - 1, 0);
        return segmentTree;
    }

    static void createMinSegmentTree(int segmentTree[], int input[], int low, int high, int pos) {

        if (low == high) {
            segmentTree[pos] = input[low];
            return;
        }

        int mid = (low + high) / 2;
        int leftChildPos = 2 * pos + 1;
        int rightChildPos = 2 * pos + 2;

        createMinSegmentTree(segmentTree, input, low, mid, leftChildPos);
        createMinSegmentTree(segmentTree, input, mid + 1, high, rightChildPos);
        segmentTree[pos] = Math.min(segmentTree[leftChildPos], segmentTree[rightChildPos]);
    }

    // Returns minimum of arr[a..b]
    static int rangeMinimumQuery(int[] segmentTree, int inputLength, int a, int b) {

        return rangeMinimumQuery(segmentTree, 0, inputLength - 1, a, b, 0);
    }

    static int rangeMinimumQuery(int[] segmentTree, int low, int high, int queryLow, int queryHigh, int pos) {

        // Total overlap
        if (queryLow <= low && queryHigh >= high) {
            return segmentTree[pos];
        }

        // No overlap
        if (queryLow > high || queryHigh < low) {
            return Integer.MAX_VALUE;
        }

        // Partial overlap
        int mid = (low + high) / 2;
        int leftChild = 2 * pos + 1;
        int rightChild = 2 * pos + 2;
        return Math.min(rangeMinimumQuery(segmentTree, low, mid, queryLow, queryHigh, leftChild),
                rangeMinimumQuery(segmentTree, mid + 1, high, queryLow, queryHigh, rightChild));
    }

    /**
     * update the segment tree and the input array at position index to value
     * Updates segment tree for certain index by given delta
     */
    static void updateSegmentTree(int input[], int segmentTree[], int index, int value){
        input[index] = value;
        updateSegmentTree(segmentTree, index, value, 0, input.length - 1, 0);
    }

    static void updateSegmentTree(int segmentTree[], int index, int value, int low, int high, int pos){

        //if index to be updated is less than low or higher than high just return.
        if(index < low || index > high){
            return;
        }

        //if low and high become equal, then index will be also equal to them and update
        //that value in segment tree at pos
        if(low == high){
            segmentTree[pos] = value;
            return;
        }
        //otherwise keep going left and right to find index to be updated
        //and then update current tree position if min of left or right has
        //changed.
        int mid = (low + high)/2;
        updateSegmentTree(segmentTree, index, value, low, mid, 2 * pos + 1);
        updateSegmentTree(segmentTree, index, value, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2*pos+1], segmentTree[2*pos + 2]);
    }


    static void setStandardInput() {

        String input = "8 4\n" +
                "3 2 4 5 1 1 5 3\n" +
                "2 1 4\n" +
                "2 5 6\n" +
                "1 2 3\n" +
                "2 1 4";

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
