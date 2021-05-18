package model.data_structures;

import java.util.Iterator;

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
	public int outdegree()
	{
		return 0;
	}
	public int indegree()
	{
		return 0;
	}
	// Retorna el último edge que tiene vertice SO SÉ SI ESTÁ BIEN
	public Edge<K,V> getEdge(K vertex)
	{
		Edge<K,V> resp = null;
		for (int i = 0; i < edge.size(); i++) 
		{
			if(edge.getElement(i).getDestination().equals(vertex) || edge.getElement(i).getSource().equals(vertex))
			{
				resp = edge.getElement(i);
			}
		}
		return resp;
	}
	public ILista<Vertex<K,V>> vertices()
	{
		ILista<Vertex<K, V>> vertices = new ArregloDinamico<>(0);
		for (int i = 0; i < edge.size(); i++) 
		{
			vertices.addLast(edge.getElement(i).getDestination());
		}
		return vertices;
	}

	public ILista<Edge<K,V>> edges()
	{
		ILista<Edge<K, V>> vertices = new ArregloDinamico<>(0);
		if(edge.size() > 0)
		{
			vertices = edge;
		}
		return vertices;
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
		Cola<Vertex<K, V>> cola = new Cola<Vertex<K, V>>();
		cola.enqueue(this);
		while(cola.peek() != null)
		{
			Vertex<K, V> actual = cola.dequeue();
			for(int i = 1; i < actual.edges().size(); i++)
			{
				Vertex<K, V> dest = actual.edges().getElement(i).getDestination();
				if(!dest.marked)
				{
					dest.mark(actual.edges().getElement(i));
					cola.enqueue(dest);
				}
			}
		}
	}

	@Override
	public int compareTo(Vertex<K, V> o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
