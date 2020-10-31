public class DetectNegativeCycle {

    // Edge list representation of graph
    static class Edge {
        int src, dest, weight;
    }

    // a structure to represent a connected, directed and
    // weighted graph
    static class Graph {

        // V-> Number of vertices, E-> Number of edges
        int V, E;

        // graph is represented as an array of edges.
        Edge edge[];
    }

    // Creates a graph with V vertices and E edges
    static Graph createGraph(int V, int E) {
        Graph graph = new Graph();
        graph.V = V;
        graph.E = E;
        graph.edge = new Edge[graph.E];

        for (int i = 0; i < graph.E; i++) {
            graph.edge[i] = new Edge();
        }

        return graph;
    }

    // The main function that finds shortest distances
    // from src to all other vertices using Bellman-
    // Ford algorithm. The function also detects
    // negative weight cycle
    static boolean isNegativeCycle(Graph graph, int src) {
        int V = graph.V;
        int E = graph.E;
        int[] distances = new int[V];

        // Step 1: Initialize distances from src to all other vertices as INFINITE
        for (int i = 0; i < V; i++)
            distances[i] = Integer.MAX_VALUE;
        distances[src] = 0;

        // Step 2: Relax all edges |V| - 1 times.
        // A simple shortest path from src to any other vertex can have at-most |V| - 1 edges
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {

                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v])
                    distances[v] = distances[u] + weight;
            }
        }

        // Step 3: check for negative-weight cycles.
        // The above step guarantees shortest distances if graph doesn't contain negative weight cycle.
        // If we get a shorter path, then there is a cycle.
        for (int i = 0; i < E; i++) {
            int u = graph.edge[i].src;
            int v = graph.edge[i].dest;
            int weight = graph.edge[i].weight;
            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v])
                return true;
        }

        return false;
    }

    // Driver Code
    public static void main(String[] args) {

        int V = 4, E = 5;
        Graph graph = createGraph(V, E);

        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 3;

        graph.edge[1].src = 1;
        graph.edge[1].dest = 3;
        graph.edge[1].weight = 1;

        graph.edge[2].src = 3;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = -7;

        graph.edge[3].src = 2;
        graph.edge[3].dest = 1;
        graph.edge[3].weight = 2;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 2;
        graph.edge[4].weight = 2;

       System.out.println("The graph " + (isNegativeCycle(graph, 0) ? "contains" : "doesn't contain") + " a negative cycle.");
    }
}
