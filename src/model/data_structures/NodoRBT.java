package model.data_structures;

import java.awt.Color;

public class NodoRBT <K extends Comparable<K>, V extends Comparable<V>> implements Comparable<NodoRBT<K,V>>
{
	K llave;
	ArregloDinamico<V> valores;
	NodoRBT derecho, izquierdo;
	int numeroNodos;

	public static Color color;

	public NodoRBT(K llave, V valor)
	{

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
			if(comparacion < 0)
			{
				if(izquierdo != null)
				{
					izquierdo.put(llave, val);
				}
				else
				{
					izquierdo = new NodoRBT(llave, val);
				}
			}
			else
			{
				if(derecho != null)
				{
					derecho.put(llave, val);
				}
				else
				{
					derecho = new NodoRBT(llave, val);
				}
			}
		}
		
		NodoRBT<K, V> raiz = evaluarYcorregir();
		raiz = raiz.evaluarYcorregir();
		return raiz;
	}

	private NodoRBT<K, V> evaluarYcorregir()
	{
		NodoRBT<K, V> raizSubArbol = this;
		NodoRBT<K, V> der = this.derecho;
		NodoRBT<K, V> izq = this.izquierdo;
		if(der != null && der.color == Color.RED)
		{
			if(izq != null && izq.color == Color.RED)
			{
				der.color = Color.BLACK;
				izq.color = Color.BLACK;
				this.color = Color.RED;
			}
			else
			{
				raizSubArbol = rotarIzquierda();
			}
		}
		
		if(izq != null && izq.color == Color.RED && izq.izquierdo != null )//Falta
		{
			raizSubArbol = rotarDerecha();
		}
		return raizSubArbol;
	}

	private NodoRBT<K, V> rotarDerecha() 
	{

		return null;
	}

	private NodoRBT<K, V> rotarIzquierda() 
	{
		return null;
	}

	public void cambiarColor(NodoRBT x) 
	{
		x.color = Color.RED;
		x.izquierdo.color = Color.BLACK ;
		x.derecho.color = Color.BLACK;
	}	
}
