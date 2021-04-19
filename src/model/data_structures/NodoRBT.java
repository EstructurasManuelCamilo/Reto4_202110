package model.data_structures;

public class NodoRBT <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<NodoRBT<K,V>>
{
	K llave;
	ArregloDinamico<V> valores;
	NodoRBT derecho, izquierdo;
	int numeroNodos;

	private static final String RED = "Rojo";
	private static final String BLACK = "Negro";

	String color;

	public NodoRBT(K llave, V valor)
	{
		color = RED;
	}

	public int compareTo(NodoRBT<K, V> otro)
	{
		return this.llave.compareTo( otro.llave ); 
	}

	public NodoRBT<K, V> put(K llave, V val)
	{
		int comparacion = llave.compareTo(this.llave);
		if(comparacion == 0)
		{
			//Manda excepcion
		}
		else 
		{

		}
		return null;
	}

	public void cambiarColor(NodoRBT x) 
	{
		x.color = RED;
		x.izquierdo.color = BLACK;
		x.derecho.color = BLACK;
	}	
}
