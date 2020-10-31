import java.util.ArrayList;

public class ConnectivityCheck {
    public static void main(String[] args) {

        // Creating a graph with 5 vertices
        int V = 5;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(V); // 0-2-3, 1-4

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(2);
        adjList.get(0).add(3);
        adjList.get(2).add(0);
        adjList.get(2).add(3);
        adjList.get(3).add(0);
        adjList.get(3).add(2);
        adjList.get(1).add(4);
        adjList.get(4).add(1);

        // Uncomment the following lines to make the graph connectivity
        // adjList.get(0).add(1);
        // adjList.get(1).add(0);

       ArrayList<Boolean> isVisited = dfs(0, adjList);

        // Check if the DFS algorithm reach all the nodes.
        boolean isConnectivity = !isVisited.contains(false);

        System.out.println("The graph is " + (isConnectivity ? "" : "not ") + "connectivity.");

    }

    public static ArrayList<Boolean> dfs(int s, ArrayList<ArrayList<Integer>> adjList) {

        ArrayList<Boolean> isVisited = new ArrayList<>(adjList.size());
        for (int i = 0; i < adjList.size(); i++) {
            isVisited.add(false);
        }

        dfsRecursion(s, adjList, isVisited);
        return  isVisited;
    }

    public static void dfsRecursion(int s, ArrayList<ArrayList<Integer>> adjList, ArrayList<Boolean> isVisited) {

        if (isVisited.get(s)) {
            return;
        }

        isVisited.set(s, true);
        adjList.get(s).forEach((u) -> dfsRecursion(u, adjList, isVisited));

        // The above line equal to the lines:
        // for (int i = 0; i < adjList.get(s).size(); i++) {
        // dfsRecursion(adjList.get(s).get(i), adjList, isVisited);
        // }
    }
}
