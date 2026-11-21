//class mygraph. will use Matrix to represent a simple weighted directed graph. There is no loop at vertex. Not more than one edge from vertex i to another vertex j. The vertices are numbered as 1, 2, ..., n The graph with n vertices is reprented by an (n + 1) by (n + 1) matrix with row 0 and column 0 unused. If there is an edge from vertex i to vertex j (i != j) then entry on row i column j of the matrix would be 1. If there is no edge between vertex i to vertex j (i != j) then the entry on row i column j of the matrix will be Integer.

import java.util.*;
public class MyGraph
{
    private int[][] graph;
    private int numberOfVertices;
    

    public MyGraph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        graph = new int[numberOfVertices+1][numberOfVertices+1];
    }
    

    public MyGraph(int [][] graph){
        this.graph = graph;

        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph.length; j++){
                if(i == j) graph[i][j] = 0;
                else if(i != j && graph[i][j] == 0)
                    graph[i][j] = Integer.MAX_VALUE;
            }
        }
        numberOfVertices = graph.length - 1; 
    }
    
   
    public String bfs(int startVertex){
        if(startVertex < 1 || startVertex > numberOfVertices)
            return "Illegal start";
        
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[numberOfVertices + 1];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        visited[startVertex] = true;    
        queue.add(startVertex);
        
        while(!queue.isEmpty()){
            int current = queue.remove();
            result.append(current).append(", ");
            // lower-numbered neighbors first
            for(int next = 1; next <= numberOfVertices; next++){
                if(graph[current][next] != 0 && graph[current][next] != Integer.MAX_VALUE 
                   && !visited[next]){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return result.toString();
    }
    

    public String dfs(int startVertex){
        if(startVertex < 1 || startVertex > numberOfVertices)
            return "Illegal start";
        
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[numberOfVertices + 1];
        dfsVisit(startVertex, visited, result);
        return result.toString();
    }
    

    private void dfsVisit(int current, boolean[] visited, StringBuilder result){
        visited[current] = true;     
        result.append(current).append(", ");

        for(int next = 1; next <= numberOfVertices; next++){
            if(graph[current][next] != 0 && graph[current][next] != Integer.MAX_VALUE 
               && !visited[next]){
                dfsVisit(next, visited, result);
            }
        }
    }
}
