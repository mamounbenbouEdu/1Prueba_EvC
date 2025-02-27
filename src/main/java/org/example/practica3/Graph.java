package org.example.practica3;


import java.util.*;

public class Graph<V> {

    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2) && adjacencyList.get(v2).add(v1); // Grafo no dirigido
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe.");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    public List<V> onePath(V v1, V v2) {
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null;
        }

        Map<V, V> trace = new HashMap<>();
        Stack<V> stack = new Stack<>();
        stack.push(v1);
        trace.put(v1, null);

        while (!stack.isEmpty()) {
            V current = stack.pop();
            if (current.equals(v2)) {
                return reconstructPath(trace, v2);
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!trace.containsKey(neighbor)) {
                    stack.push(neighbor);
                    trace.put(neighbor, current);
                }
            }
        }

        return null;
    }

    private List<V> reconstructPath(Map<V, V> trace, V v2) {
        List<V> path = new LinkedList<>();
        for (V at = v2; at != null; at = trace.get(at)) {
            path.add(0, at);
        }
        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : adjacencyList.keySet()) {
            sb.append(v).append(" -> ").append(adjacencyList.get(v)).append("\n");
        }
        return sb.toString();
    }
}
