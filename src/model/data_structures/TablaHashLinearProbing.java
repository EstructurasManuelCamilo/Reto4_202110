package model.data_structures;

public class TablaHashLinearProbing <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos <K,V>
{
	private ILista <NodoTS<K, V>> listaNodos;
	private int tamanioActual;
	private int tamanioTabla;
	
	public TablaHashLinearProbing(int pTamanioIncial) 
	{
		tamanioTabla = nextPrime(pTamanioIncial);
		listaNodos = new ArregloDinamico<NodoTS<K,V>> (pTamanioIncial);
		tamanioActual = 0;
		for (int i = 0 ; i < tamanioTabla; i++)
		{
			listaNodos.addLast(null);
		}
	}
	

	@Override
	public ILista<K> keySet() 
	{ 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<V> valueSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) 
	{
		int posicion = hash(key);
		V resp = null;
		boolean encontroNull = false;
		while(resp == null && !encontroNull )
		{
			NodoTS<K, V> nodoActual = listaNodos.getElement(posicion);
			if(nodoActual == null)
			{
				encontroNull = true;
			}
			else if( ! nodoActual.isEmpty() && nodoActual.getKey().compareTo(key) == 0)
			{
				resp = nodoActual.getValue();
			}
			else 
			{
				posicion ++;
				if(posicion > tamanioActual)
				{
					posicion = 1;
				}
			}
		}
		return resp;
	}

	@Override
	public int size() 
	{
		return tamanioActual;
	}

	@Override
	public void put(K key, V val) 
	{
		int posicion = hash(key);
		NodoTS<K, V> nodo = listaNodos.getElement(posicion);
		if(nodo != null && nodo.isEmpty())
		{
			posicion = getNextEmpty(posicion);
		}
		listaNodos.changeInfo(posicion, new NodoTS<K, V>(key, val));
		tamanioActual ++;
	}

	@Override
	public void remove(K key) 
	{
		int posicion = hash(key);
		V resp = null;
		boolean encontroNull = false;
		while(resp == null && !encontroNull )
		{
			NodoTS<K, V> nodoActual = listaNodos.getElement(posicion);
			if(nodoActual == null)
			{
				encontroNull = true;
			}
			else if( ! nodoActual.isEmpty() && nodoActual.getKey().compareTo(key) == 0)
			{
				resp = nodoActual.getValue();
			}
			else 
			{
				posicion ++;
				if(posicion > tamanioActual)
				{
					posicion = 1;
				}
			}
		}
		
		if(resp != null)
		{
			listaNodos.getElement(posicion).setEmpty();
		}
	}

	@Override
	public boolean contains(K key) 
	{
		int posicion = hash(key);
		V valor = null;
		boolean resp = false;
		boolean encontroNull = false;
		while(valor == null && !encontroNull )
		{
			NodoTS<K, V> nodoActual = listaNodos.getElement(posicion);
			if(nodoActual == null)
			{
				encontroNull = true;
			}
			else if( ! nodoActual.isEmpty() && nodoActual.getKey().compareTo(key) == 0)
			{
				valor = nodoActual.getValue();
				resp = true;
			}
			else 
			{
				posicion ++;
				if(posicion > tamanioActual)
				{
					posicion = 1;
				}
			}
		}
		return resp;
	}

	@Override
	public boolean isEmpty() 
	{
		return listaNodos.size() == 0;
	}

	@Override
	public int hash(K key) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int nextPrime(int num) 
	{
		num++;
	      for (int i = 2; i < num; i++) {
	         if(num%i == 0) {
	            num++;
	            i=2;
	         } else {
	            continue;
	         }
	      }
	      return num;
	}
	
	private int getNextEmpty(int posicion)
	{
		int p1 = posicion;
		while(listaNodos.getElement(p1) != null && !listaNodos.getElement(p1).isEmpty())
		{
			p1 ++;
			if (p1 > tamanioTabla) {
				p1 = 1;
			}
		}
		return p1;
	}

}
