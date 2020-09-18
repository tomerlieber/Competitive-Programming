import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

public class Orienteering {

    public static void main (String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        in.skip("\n");

        int p = in.nextInt();
        in.skip("\n");

        int s1 = in.nextInt();
        int s2 = in.nextInt();
        in.skip("\n");

        ArrayList<Point> positions = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            positions.add(new Point(x, y));
        }

        in.skip("\n");

        double[][] runnabilityMatrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            String[] row = in.nextLine().split(" ");

            for (int j = 0; j < m; j++) {
                runnabilityMatrix[i][j] = Double.valueOf(row[j]);
            }
        }


        double[][] elevationMatrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            String[] row = in.nextLine().split(" ");

            for (int j = 0; j < m; j++) {
                elevationMatrix[i][j] = Double.valueOf(row[j]);
            }
        }


        ArrayList<ArrayList<Node>> adjList = new ArrayList<>(n * m);

        for (int i = 0; i < n * m; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int currIndex = i * m + j;

                if (j < m - 1) {
                    int rightIndex = i * m + (j + 1);
                    double moveRight = calcTime(i, j, i, j + 1, runnabilityMatrix, elevationMatrix);
                    adjList.get(currIndex).add(new Node(rightIndex, moveRight));
                }
                if (j > 0) {
                    int leftIndex = i * m + (j - 1);
                    double moveLeft = calcTime(i, j, i, j - 1, runnabilityMatrix, elevationMatrix);
                    adjList.get(currIndex).add(new Node(leftIndex, moveLeft));
                }
                if (i > 0) {
                    int upIndex = (i - 1) * m + j;
                    double moveUp = calcTime(i, j, i - 1, j, runnabilityMatrix, elevationMatrix);
                    adjList.get(currIndex).add(new Node(upIndex, moveUp));
                }

                if (i < n - 1) {
                    int downIndex = (i + 1) * m + j;
                    double moveDown = calcTime(i, j, i + 1, j, runnabilityMatrix, elevationMatrix);
                    adjList.get(currIndex).add(new Node(downIndex, moveDown));
                }
            }
        }

        double result = 0;

        int source = s1 * m + s2;


        for (int t = 0; t < positions.size(); t++) {

            double[] distances = new double[m * n];
            Arrays.fill(distances, Double.MAX_VALUE);

            boolean[] processed = new boolean[m * n];

            PriorityQueue<Node> pQueue = new PriorityQueue<>(m * n);

            // Distance to the source is 0
            distances[source] = 0;

            pQueue.add(new Node(source, 0));

            while (!pQueue.isEmpty()) {

                // remove the minimum distance node
                int a = pQueue.poll().getNode(); // O(log(m))

                // If current node hasn't already been processed
                if (processed[a]) {
                    continue;
                }

                processed[a] = true;

                for (int i = 0; i < adjList.get(a).size(); i++) {

                    Node child = adjList.get(a).get(i);
                    int b = child.getNode();
                    double w = child.getWeight();

                    // If new distance is cheaper in cost
                    if (distances[a] + w < distances[b]) {
                        distances[b] = distances[a] + w;

                        // Add the current node to the queue
                        pQueue.add(new Node(b, distances[b])); // O(1)
                    }
                }
            }

            int nextPoisition = positions.get(t).getX() * m + positions.get(t).getY();
            result += distances[nextPoisition];
            source = nextPoisition;
        }

        System.out.println((int)Math.ceil(result));
    }

    private static double calcTime(int x1, int y1, int x2, int y2, double[][] runnabilityMatrix, double[][] elevationMatrix) {

        double ra = runnabilityMatrix[x1][y1];
        double rb = runnabilityMatrix[x2][y2];
        double hb = elevationMatrix[x2][y2];
        double ha = elevationMatrix[x1][y1];

        double t = ((ra + rb) / 2) * Math.exp(3.5 * Math.abs(((hb - ha) / 10) + 0.05));
        return t;
    }

    private static class Point {
        private int x;
        private int y;


        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int getX() {
            return this.x;
        }

        private int getY() {
            return this.y;
        }
    }

    public static class Node implements Comparable<Node>{
        private int node;
        private double weight;


        private Node(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }

        private int getNode() {
            return this.node;
        }

        private double getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Node otherNode) {
            return Double.compare(this.weight, otherNode.weight);
        }
    }

    private static void setStandardInput() {

        String input = "1 3\n" +
                "2\n" +
                "0 0\n" +
                "0 2\n" +
                "0 0\n" +
                "5.0 6.0 5.0\n" +
                "100.0 101.5 103.0";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}

