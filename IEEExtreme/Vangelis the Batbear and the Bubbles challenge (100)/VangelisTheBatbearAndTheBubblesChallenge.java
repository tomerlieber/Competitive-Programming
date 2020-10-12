import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

class VangelisTheBatbearAndTheBubblesChallenge {

    public static void main (String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int testsNumber = in.nextInt();
        in.skip("\n");

        for (int t = 1; t <= testsNumber; t++) {

            int N = in.nextInt();
            int M = in.nextInt();
            in.skip("\n");

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                int a = in.nextInt();
                int b = in.nextInt();

                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }

            System.out.println(isCyclic(N, adjList) ? "1" : "0");
        }
    }

    // Returns true if the graph contains a cycle, else false.
    private static boolean isCyclic(int N,  ArrayList<ArrayList<Integer>> adjList)
    {
        // Mark all the vertices as not visited and not part of
        // recursion stack
        Boolean[] visited = new Boolean[N];
        for (int i = 0; i < N; i++)
            visited[i] = false;

        // Call the recursive helper function to detect cycle in
        // different DFS trees
        for (int u = 0; u < N; u++)
            if (!visited[u]) // Don't recur for u if already visited
                if (isCyclicUtil(u, visited, -1, adjList))
                    return true;

        return false;
    }

    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v.
    private static Boolean isCyclicUtil(int v, Boolean visited[], int parent,  ArrayList<ArrayList<Integer>> adjList)
    {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adjList.get(v).iterator();
        while (it.hasNext())
        {
            i = it.next();

            // If an adjacent is not visited, then recur for that
            // adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v, adjList))
                    return true;
            }

            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }

    public static void setStandardInput() {

        String input = "2\n" +
                "4 5\n" +
                "0 1 0 2 1 2 2 3 0 3\n" +
                "6 5\n" +
                "0 1 0 3 1 2 1 5 3 4";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}