import java.util.ArrayList;

public class BipartitenessCheck {
    public static void main(String[] args) {

        // Creating a graph with 5 vertices
        int V = 6;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(V); // 0-2-3, 1-4

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(2);
        // adjList.get(0).add(3);
        adjList.get(2).add(0);
        adjList.get(2).add(3);
        // adjList.get(3).add(0);
        adjList.get(3).add(2);
        adjList.get(1).add(4);
        adjList.get(4).add(1);
        // adjList.get(5).add(4);
        // adjList.get(5).add(1);

        boolean result = isBipartite(0, adjList);

        System.out.println("The graph is " + (result ? "" : "not ") + "Bipartiteness.");
    }

    public static boolean isBipartite(int color, ArrayList<ArrayList<Integer>> adjList) {

        ArrayList<Integer> coloring = new ArrayList<>(adjList.size());
        for (int i = 0; i < adjList.size(); i++) {
            coloring.add(-1);
        }

        while(coloring.contains(-1)) {
            if (!isBipartiteRec(coloring.indexOf(-1), color, adjList, coloring)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isBipartiteRec(int s, int color, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> coloring) {

        if (coloring.get(s) != -1) {
            return coloring.get(s) == color;
        }

        coloring.set(s, color);

        for (int i = 0; i < adjList.get(s).size(); i++) {
            int currentChild = adjList.get(s).get(i);

            if (!isBipartiteRec(currentChild, 1 - color, adjList, coloring)) {
                return false;
            }
        }

        return true;
    }
}
