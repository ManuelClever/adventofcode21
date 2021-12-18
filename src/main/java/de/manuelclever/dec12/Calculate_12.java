package de.manuelclever.dec12;

import de.manuelclever.Calculator;
import de.manuelclever.MyFileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate_12 implements Calculator {
    Graph graph;
    Set<String> paths;

    @Override
    public long calculatePart1() {
        createGraph();

        paths = new HashSet<>();
        List<String> startEdges = graph.getNeighbours("start");

        for(String vertex : startEdges) {
            StringBuilder sb = new StringBuilder("start,");
            Set<String> visited = new HashSet<>();
            visited.add("start");

            if (isAllLowercase(vertex)) {
                visited.add(vertex);
            }
            sb.append(vertex).append(",");
            buildPath(vertex, sb, visited);
        }
        return paths.size();
    }

    private void createGraph() {
        MyFileReader fr = new MyFileReader(12,1);
        List<String> rawInput = fr.getStringList();
        graph = new Graph();

        for(String line : rawInput) {
            String[] edge = line.split("-");
            graph.addEdge(edge[0], edge[1]);
        }
    }

    private void buildPath(String vertex, StringBuilder sb, Set<String> visited) {
        List<String> vertexes = graph.getNeighbours(vertex);

        for(String neighbour : vertexes) {

            if(!visited.contains(neighbour)) {
                StringBuilder newSB = new StringBuilder(sb);
                Set<String> newVisited = new HashSet<>(visited);

                if (neighbour.equals("end")) {
                    paths.add(sb.append("end").toString());
                } else {
                    if (isAllLowercase(neighbour)) {
                        newVisited.add(neighbour);
                    }
                    newSB.append(neighbour).append(",");
                    buildPath(neighbour, newSB, newVisited);
                }
            }
        }

    }

    private boolean isAllLowercase(String vertex) {
        String reg = "[a-z]+";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(vertex);

        return matcher.matches();
    }

    @Override
    public long calculatePart2() {
        return 0;
    }
}

class Graph {
    Map<String, List<String>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if(!graph.containsKey(vertex)) {
            graph.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(String vertex1, String vertex2) {

        if(!graph.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if(!graph.containsKey(vertex2)) {
            addVertex(vertex2);
        }

        graph.get(vertex1).add(vertex2);
        graph.get(vertex2).add(vertex1);
    }

    public List<String> getNeighbours(String vertex) {
        return graph.get(vertex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, List<String>> entry : graph.entrySet()) {
            sb.append(entry.getKey()).append(": ");

            for(String conn : entry.getValue()) {
                sb.append(conn).append(",");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
