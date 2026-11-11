import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class taller {
	private static class Vertex {
		private final String label;
		private boolean visited;

		private Vertex(String label) {
			this.label = label;
		}
	}

	private final Vertex[] vertexList;
	private final int[][] adjMat;
	private int nVerts;

	public taller(int maxVertices) {
		if (maxVertices <= 0) {
			throw new IllegalArgumentException("El grafo debe permitir al menos un vértice.");
		}
		vertexList = new Vertex[maxVertices];
		adjMat = new int[maxVertices][maxVertices];
	}

	public int addVertex(String label) {
		if (nVerts == vertexList.length) {
			throw new IllegalStateException("El grafo alcanzó su capacidad máxima.");
		}
		vertexList[nVerts] = new Vertex(label);
		return nVerts++;
	}

	public void addEdge(int first, int second) {
		checkVertex(first);
		checkVertex(second);
		adjMat[first][second] = 1;
		adjMat[second][first] = 1;
	}

	public void dfs(int vertex) {
		checkVertex(vertex);
		resetVisited();
		List<String> order = new ArrayList<>();
		dfsVisit(vertex, order);
		displayTraversal("DFS", order);
	}

	private void dfsVisit(int vertex, List<String> order) {
		vertexList[vertex].visited = true;
		order.add(vertexList[vertex].label);

		for (int neighbor = 0; neighbor < nVerts; neighbor++) {
			boolean hasEdge = adjMat[vertex][neighbor] == 1;
			boolean notVisited = !vertexList[neighbor].visited;
			if (hasEdge && notVisited) {
				dfsVisit(neighbor, order);
			}
		}
	}

	public void bfs(int startVertex) {
		checkVertex(startVertex);
		resetVisited();
		List<String> order = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		vertexList[startVertex].visited = true;
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			int current = queue.remove();
			order.add(vertexList[current].label);

			for (int neighbor = 0; neighbor < nVerts; neighbor++) {
				boolean hasEdge = adjMat[current][neighbor] == 1;
				boolean notVisited = !vertexList[neighbor].visited;
				if (hasEdge && notVisited) {
					vertexList[neighbor].visited = true;
					queue.add(neighbor);
				}
			}
		}
		displayTraversal("BFS", order);
	}

	private void resetVisited() {
		for (int vertex = 0; vertex < nVerts; vertex++) {
			vertexList[vertex].visited = false;
		}
	}

	private void checkVertex(int vertex) {
		if (vertex < 0 || vertex >= nVerts) {
			throw new IllegalArgumentException("Índice de vértice inválido: " + vertex);
		}
	}

	private void displayTraversal(String algorithm, List<String> order) {
		System.out.println(algorithm + ": " + String.join(" -> ", order));
	}

	public static void main(String[] args) {
		taller graph = new taller(6);
		int a = graph.addVertex("A");
		int b = graph.addVertex("B");
		int c = graph.addVertex("C");
		int d = graph.addVertex("D");
		int e = graph.addVertex("E");
		int f = graph.addVertex("F");

		graph.addEdge(a, b);
		graph.addEdge(a, c);
		graph.addEdge(b, d);
		graph.addEdge(b, e);
		graph.addEdge(c, f);

		graph.dfs(a);
		graph.bfs(a);
	}
}
