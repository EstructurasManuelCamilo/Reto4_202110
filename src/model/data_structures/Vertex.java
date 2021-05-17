package model.data_structures;

public class Vertex <K extends Comparable<K>,V > implements Comparable<Vertex<K, V>>
{
	private K id;
	private V value;
	private ArregloDinamico<Edge<K, V>> edge;
	private boolean marked;
	  
	
	public Vertex(K pId, V pValue)
	{
		id = pId;
		value = pValue;
		edge = new ArregloDinamico<Edge<K,V>>(10);
	}
	public K getId()
	{
		return id;
	}
	public V getInfo() 
	{
		return value;
	}
	public boolean getMark() 
	{
		return marked;
	}
	public void addEdge( Edge<K,V> pEdge ) 
	{
		edge.addLast(pEdge);
	}
	public void mark(Edge<K,V> edgeTo) 
	{
		marked = true;
	}
	public void unmark() 
	{
		marked = false;
	}
	public void dfs(Edge<K,V> edgeTo)
	{
		mark(edgeTo);
		for (int i = 0; i < edge.size(); i++) 
		{
			Vertex<K, V> dest = edge.getElement(i).getDestination();
			if(!dest.getMark())
			{
				dest.dfs(edge.getElement(i));
			}
		}
	}
	
	public void bfs()
	{
		// Falta 
	}
	
	@Override
	public int compareTo(Vertex<K, V> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
