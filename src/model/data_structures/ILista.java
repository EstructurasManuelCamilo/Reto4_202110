package model.data_structures;

public interface ILista <T extends Comparable<T>> extends Comparable <ILista<T>>
{
	/**
	 * agrega un elemento al inicio de la lista
	 * @param element que se va a agregar
	 */
	void addFirst(T element);
	
	/**
	 * Agraga un elemento al final de la lista
	 * @param element
	 */
	void addLast(T element);
	
	/**
	 * Agrega un elemento en la posici�n pos si la posici�n es una posici�n v�lida. 
	 * Los elementos que est�n a partir de la 
	 * posici�n dada deben correrse una posici�n a la derecha.
	 * Las posiciones v�lidas son posiciones donde ya hay un
	 * elemento en la lista. La posici�n del primer elemento es 1, 
	 * la del segundo es 2 y as� sucesivamente
	 * @param element
	 * @param pos
	 */
	void insertElement(T element, int pos);
	
	/**
	 * Elimina el primer elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	T removeFirst( );
	
	/**
	 * Elimina el �ltimo elemento. Se retorna el elemento eliminado.
	 * @return el elemento eliminado
	 */
	T removeLast( ); 
	
	/**
	 * Elimina el elemento de una posici�n v�lida (mayor que -1). Se retorna el elemento eliminado.
	 * @param la posici�n del elemento a eliminar
	 * @return elemento eliminado
	 */
	T deleteElement( int pos);
	
	/**
	 * Retorna el primer elemento
	 * @return el primer elemento
	 */
	T firstElement( );
	
	/**
	 * Retorna el �ltimo elemento
	 * @return el �ltimo elemento
	 */
	T lastElement();
	
	/**
	 * Retorna el elemento en una posici�n v�lida. La posici�n del
	 * primer elemento es 1, la del segundo es 2 y as� sucesivamente
	 * @param la posici�n del elemento que se busca
	 * @return el elemento en la posici�n especificada
	 */
	T getElement( int pos) ;
	
	/**
	 * Retorna el n�mero de datos en el arreglo
	 * @return el tamanio del arreglo
	 */
	int size( );
	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return si el arreglo tiene datos o no
	 */
	boolean isEmpty( );
	
	/**
	 * Retorna la posici�n v�lida de un elemento. Si no se 
	 * encuentra el elemento, el valor retornado es -1
	 * @param element para consultar
	 * @return la posicion del elemento
	 */
	int isPresent (T element);
	
	/**
	 * Intercambia la informaci�n de los elementos en dos posiciones v�lidas.
	 * @param la posici�n primera posici�n
	 * @param la posici�n segunta posici�n
	 */
	void exchange (int pos1, int pos2);
	
	/**
	 * Cambia la informaci�n del elemento especificado por par�metro
	 * @param pos
	 * @param elem
	 */
	void changeInfo (int pos, T elem);
	
	/**
	 * Crear una sublista de la lista original (this).
	 * Los elementos se toman en el mismo orden como aparecen en la lista original (this).
	 * @param número de elementos que contendrá la sublista. Si el número es superior al tamaño
	 * original de la lista, se obtiene una copia de la lista original.
	* @return sublista creada con la misma representación de la lista original (this).
	 */
	public ILista<T> sublista(int numElementos);

	public ILista<T> subList(int i, int mid);

}
