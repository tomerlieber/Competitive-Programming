public class FloydWarshallAlgorithm {

    public static void main(String[] args) {

        int V = 5;

        /*
        Create adjacency matrix to represent the following graph.
             7         2
       (2)--------(3)----(4)
        |          |    /
      2 |          |    /
        |        9 |   / 1
        |          |  /
       (1)--------(0)/
             5
        */
        int[][] adjMatrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            adjMatrix[i][i] = 0;
        }
        adjMatrix[0][4] = 1;
        adjMatrix[4][0] = 1;
        adjMatrix[0][3] = 9;
        adjMatrix[3][0] = 9;
        adjMatrix[3][4] = 2;
        adjMatrix[4][3] = 2;
        adjMatrix[0][1] = 5;
        adjMatrix[1][0] = 5;
        adjMatrix[1][2] = 2;
        adjMatrix[2][1] = 2;
        adjMatrix[2][3] = 7;
        adjMatrix[3][2] = 7;

        // Initialize the distances using the adjacency matrix of the graph.
        int[][] distances = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else if (adjMatrix[i][j] != 0) {
                    distances[i][j] = adjMatrix[i][j];
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Execute the logic of the algorithm.
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    // Skip to next iteration in order to prevent int overflow and unexpected behavior.
                    if (distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        System.out.println("The following matrix shows the shortest distances between every pair of vertices");
        Print(distances);
    }

    static void Print(int[][] distances) {

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                if (distances[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF\t");
                else
                    System.out.print(distances[i][j] + "\t");
            }

            System.out.println();
        }

        System.out.println();
    }
}
