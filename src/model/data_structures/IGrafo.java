package model.data_structures;

public interface IGrafo <K extends Comparable<K>, V >
{
	public boolean containsVertex(K id);
	public int numVertices();
	public int numEdges();
	public void insertVertex(K id, V value) ;
	public void addEdge(K source, K dest, float weight);
	public Vertex<K,V> getVertex(K id);
	public Edge<K,V> getEdge(K idS, K idD);
	public ILista<Edge<K,V>> adjacentEdges(K id);
	public ILista<Vertex<K,V>> adjacentVertex(K id);
	public int indegree(K vertex);
	public int outdegree(K vertex); 
	public ILista<Edge<K,V>> edges();
	public ILista<Vertex<K,V>> vertices();
	public void unmark();
	public void dfs(K id);
	public void bfs(K id);
}
