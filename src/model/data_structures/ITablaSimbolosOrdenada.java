package model.data_structures;

public interface ITablaSimbolosOrdenada <K extends Comparable<K>, V extends Comparable<V>> 
{
	/**
	 * Retorna todas las llaves de un tabla.
	 * @return Lista con toda las llaves de una tabla.
	 */
	public ILista<K> keySet();
	
	/**
	 * Retorna todos los valores de un tabla.
	 * @return Lista con toda los valores de una tabla.
	 */
	public ILista<V> valueSet();
	
	/**
	 * Retorna el valor de una llave dada.
	 * @param key LLave del valor que se desea.
	 * @return Valor de la llace dada.
	 */
	public V get (K key);
	
	/**
	 * Retorna el tamaÒo de la tabla.
	 * @return El tamaÒo de la tabla.
	 */
	public int size();
	
	/**
	 * Coloca una tupla de llave y valor en la tabla.
	 * @param key Llave que se desea insertar.
	 * @param val Valor que se desea insertar.
	 */
	public void put(K key, V val);
	
	/**
	 * Quita una tupla de la tabla.
	 * @param key Llave de kla tupla que se desea eliminar.
	 */
	public void remove (K key);
	
	/**
	 * Verifica si una llave se encuentra dentro de la tabla
	 * @param key Llave que se desea buscar en la tabla.
	 * @return True si la llave esta en la tabla. False de lo contrario.
	 */
	public boolean contains(K key);
	
	/**
	 * Verifica si la tabla esta vacia.
	 * @return True en caso de que la tabla este vacia. False de lo contrario.
	 */
	public boolean isEmpty();

	public int hash(K key);	
	
	public int getHeight(K key);
	
	public int height();
	
	public K min();
	
	public K max();
}
