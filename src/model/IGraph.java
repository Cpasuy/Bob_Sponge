package model;

public interface IGraph<T>  {
	public static final int INFINITE = Integer.MAX_VALUE;
	public void addVertex(T element);
	public void addEdge(T from, T to);
	public void addEdge(T from, T to,int weight);
	public boolean removeVertex(T element);
	public boolean removeEdge(T from,T to);
	public boolean isDirected();
	public boolean isWeighted();
	public boolean searchInGraph(T element);
	public void bfs(T initialNode);
	public void  dfs(T initialNode);
	public int dijkstra(T initialNode,T destinyNode);
	public int kruskal();
	public double[][] floyd_Warshall();
}
