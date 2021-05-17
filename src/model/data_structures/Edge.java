package model.data_structures;

public class Edge <K extends Comparable<K>, V> implements Comparable <Edge<K,V>>
{
	private Vertex<K, V> origin;
	private Vertex<K, V> destination;
	private float weight;
	
	public Edge (Vertex<K,V> pSource, Vertex<K,V> pDestination, float pWeight)
	{
		origin = pSource;
		destination = pDestination;
		weight = pWeight;
	}
	public Vertex<K,V> getSource() 
	{
		return origin;
	}
	public Vertex<K,V> getDestination() 
	{
		return destination;
	}
	public float weight() 
	{
		return weight;
	}
	public void setWeight(float pWeight) 
	{
		weight = pWeight;
	}
	@Override
	public int compareTo(Edge<K, V> o) 
	{
		return 0;
	}
	
}
