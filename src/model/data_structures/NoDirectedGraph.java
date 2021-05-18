package model.data_structures;

public class NoDirectedGraph < K extends Comparable<K>, V> implements IGrafo<K, V>
{
	private ITablaSimbolos<K, Vertex<K, V>> vertices;


	public NoDirectedGraph(int pTamanio) 
	{
		vertices = new TablaHashLinearProbing<K, Vertex<K, V>>(pTamanio);
	}

	@Override
	public boolean containsVertex(K id) 
	{
		boolean resp = false;
		for (int i = 0; i < vertices.size(); i++) 
		{
			if(vertices.get(id) != null)
			{
				resp = true;
			}
		}
		return resp;
	}

	@Override
	public int numVertices() 
	{
		return vertices().size();
	}

	@Override
	public int numEdges() 
	{
		return edges().size();
	}

	@Override
	public void insertVertex(K id, V value) 
	{
		vertices.put(id, new Vertex <K, V> (id, value));
	}

	@Override
	public void addEdge(K source, K dest, float weight) 
	{
		Edge<K, V> existe = getEdge(source, dest);
		if(existe != null)
		{
			Vertex<K, V> origin = getVertex(source);
			Vertex<K, V> destination = getVertex(dest);
			if(origin!= null && destination!= null)
			{
				origin.addEdge(new Edge<K, V>(origin, destination, weight));
				destination.addEdge(new Edge<K, V>(origin, destination, weight));

			}
		}
		else
		{

		}
	}

	@Override
	public Vertex<K, V> getVertex(K id) 
	{
		ILista<Vertex<K, V>> vertices = vertices();
		Vertex<K, V> resp = null;
		for(int i = 0; i <= vertices.size(); i++)
		{
			if(vertices.getElement(i).getId().equals(id))
			{
				resp = vertices.getElement(i);
			}
		}
		return resp;
	}

	@Override
	public Edge<K, V> getEdge(K idS, K idD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<Edge<K, V>> adjacentEdges(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<Vertex<K, V>> adjacentVertex(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int outdegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ILista<Edge<K, V>> edges() 
	{
		ILista<Edge<K, V>> resp = new ArregloDinamico<>(7);

		for (int i = 0; i < vertices.size(); i++) 
		{
			for(int j = 0; j < vertices().getElement(i).edges().size(); j++)
			{
				if(resp.isPresent(vertices().getElement(i).edges().getElement(j)) == -1)
				{
					resp.addLast(vertices().getElement(i).edges().getElement(j));
				}
			}
		}
		return resp;
	}

	@Override
	public ArregloDinamico<Vertex<K, V>> vertices() 
	{
		ArregloDinamico<Vertex<K, V>> resp = new ArregloDinamico<>(7);

		for (int i = 0; i < vertices.size(); i++) 
		{
			resp.addLast(vertices.valueSet().getElement(i));
		}
		return resp;
	}

	@Override
	public void unmark() 
	{
		ILista<Vertex<K, V>> vertices = vertices();
		for(int i = 0; i <= vertices.size(); i++)
		{
			vertices.getElement(i).unmark();
		}
	}
	// Es recursivo
	@Override
	public void dfs(K id) 
	{
		Vertex<K, V> inicio = getVertex(id);
		inicio.dfs(null); // Depende de lo que quedamos hacer
		unmark();
	}

	@Override
	public void bfs(K id) 
	{
		Vertex<K, V> inicio = getVertex(id);
		inicio.bfs();
		unmark();
	}


}
