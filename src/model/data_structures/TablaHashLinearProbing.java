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
		int p = nextPrime(tamanioTabla);
		int a = (int) (Math.random()* (p-1));
		int b = (int) (Math.random()* (p-1));
		return Math.abs((key.hashCode()*a + b) % p) % tamanioTabla;
	}
	
	// Function that returns true if n
    // is prime else returns false
    static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
            return false;
        return true;
    }

    // Function to return the smallest
    // prime number greater than N
    static int nextPrime(int N)
    {
        // Base case
        if (N <= 1)
            return 2;
        int prime = N;
        boolean found = false;
        // Loop continuously until isPrime returns
        // true for a number greater than n
        while (!found)
        {
            prime++;
            if (isPrime(prime))
                found = true;
        }
        return prime;

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
