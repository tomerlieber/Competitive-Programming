import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Graph {

    private int n; // The number of vertices in the graph
    private ArrayList<ArrayList<Edge>> adjList;
    private long[] distances;
    private int[] prev;

    private Graph(int n) {

        this.n = n;
        this.adjList = new ArrayList<>(n);

        //initialize adjacency lists for all the vertices
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    private long[] getDistances() {
        return this.distances;
    }

    private int[] getPrev() {
        return this.prev;
    }

    private void addEgde(int source, int destination, long weight) {

        adjList.get(source).add(new Edge(destination, weight));
        adjList.get(destination).add(new Edge(source, weight));
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