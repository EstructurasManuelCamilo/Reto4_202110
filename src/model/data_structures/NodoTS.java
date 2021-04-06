package model.data_structures;

public class NodoTS<K extends Comparable<K>, V> implements Comparable<NodoTS<K,V>> 
{
	
	private K llave; // objeto llave (Comparable) private V valor; // objeto de informacion asociado
	
	private V valor;
	
	public NodoTS(K llave, V valor)
	{
		this.llave = llave;
		this.valor = valor;
	}
	
	public K getKey()
	{
		return llave;
	}
	
	public V getValue()
	{
		return valor;
	}
	
	public void setKey(K llave)
	{
		this.llave = llave;
	}
	
	public void setValue(V valor)
	{
		this.valor = valor;
	}
	
	public void setEmpty()
	{
		this.llave = null;
	}
	
	public boolean isEmpty()
	{
		return this.llave == null;
	}
	
	/** 
	 * La comparación de dos NodoTS depende de sus llaves 
	*/ 
	
	public int compareTo(NodoTS<K, V> otro)
	{
		return this.llave.compareTo( otro.llave ); 
	}
}
