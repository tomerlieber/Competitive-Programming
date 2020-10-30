import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

public class CollectingGold {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // The number of cities/villages
        int m = in.nextInt(); // The number of connections between them.

        // Initializing a Dictionary
        HashMap<Long, Integer> cityIDtoIndex = new HashMap<>();
        HashMap<Integer, Long> indexTocityID = new HashMap<>();

        long minId = Long.MAX_VALUE;
        long maxId = 0;
        for (int i = 0; i < n; i++) {

            long currID = in.nextLong();
            cityIDtoIndex.put(currID, i);
            indexTocityID.put(i, currID);

            if (currID < minId) {
                minId = currID;
            }

            if (currID > maxId) {
                maxId = currID;
            }
        }

        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) {

            long x = in.nextLong();
            long y = in.nextLong();
            int d = in.nextInt();

            int city1 = cityIDtoIndex.get(x);
            int city2 = cityIDtoIndex.get(y);

            g.addEgde(city1, city2, d);
        }

        g.runDijkstra(cityIDtoIndex.get(minId));

        int[] prev = g.getPrev();

        int finishingCity = cityIDtoIndex.get(maxId);

        long result = getKilosOfGolds(maxId);

        while (prev[finishingCity] != -1) {

            finishingCity = prev[finishingCity];
            result += getKilosOfGolds(indexTocityID.get(finishingCity));
        }

        System.out.println(result);
    }

    private static int getKilosOfGolds(long cityID) {

        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};

        int counter = 0;

        long nextProduct =  primes[counter];

        while (nextProduct <= cityID) {

            counter++;
            nextProduct *= primes[counter];
        }

        return counter;
    }

    private static class Graph {

        private int n; // The number of vertices in the graph
        private ArrayList<ArrayList<Edge>> adjList;

        private Graph(int n) {

            this.n = n;
            this.adjList = new ArrayList<>(n);

            //initialize adjacency lists for all the vertices
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        private void addEgde(int source, int destination, long weight) {

            adjList.get(source).add(new Edge(destination, weight));
            adjList.get(destination).add(new Edge(source, weight));
        }

        private long[] distances;
        private int[] prev;

        private long[] getDistances() {
            return this.distances;
        }

        private int[] getPrev() {
            return this.prev;
        }

        private void runDijkstra(int source) {

            prev = new int[n];
            prev[source] = -1;

            distances = new long[n];

            // Initially the distance to the source is 0 and the distances to all other nodes is infinity.
            Arrays.fill(distances, Long.MAX_VALUE);
            distances[source] = 0;

            boolean[] processed = new boolean[n];

            PriorityQueue<Edge> pQueue = new PriorityQueue<>(n);

            // Add source node to the priority queue
            pQueue.add(new Edge(source, 0));

            while (!pQueue.isEmpty()) {

                // remove the minimum distance node
                int a = pQueue.poll().getDestination(); // O(log(m))

                // If current node hasn't already been processed
                if (processed[a]) {
                    continue;
                }

                processed[a] = true;

                for (int i = 0; i < adjList.get(a).size(); i++) {

                    Edge child = adjList.get(a).get(i);
                    int b = child.getDestination();
                    long w = child.getWeight();

                    // If new distance is cheaper in cost
                    if (distances[a] + w < distances[b]) {
                        distances[b] = distances[a] + w;
                        prev[b] = a;

                        // Add the current node to the queue
                        pQueue.add(new Edge(b, distances[b])); // O(1)
                    }
                }
            }
        }

        private class Edge implements Comparable<Edge> {

            int destination;
            long weight;

            private Edge(int destination, long weight) {
                this.destination = destination;
                this.weight = weight;
            }

            private int getDestination() {
                return this.destination;
            }

            private long getWeight() {
                return this.weight;
            }

            @Override
            public int compareTo(Edge otherEdge) {
                return Long.compare(this.weight, otherEdge.weight);
            }
        }
    }


    private static void setStandardInput() {

        String input = "4 5\n" +
                "500\n" +
                "2\n" +
                "300\n" +
                "20\n" +
                "2 300 5000\n" +
                "2 20 100\n" +
                "300 20 300\n" +
                "300 500 100\n" +
                "20 500 200";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
