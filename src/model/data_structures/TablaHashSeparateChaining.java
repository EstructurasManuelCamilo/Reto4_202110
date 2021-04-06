package model.data_structures;

public class TablaHashSeparateChaining <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos <K,V>
{
	private ILista <ILista<NodoTS<K, V>>> listaNodos;
	private int tamanioActual;
	private int tamanioTabla;
	
	public TablaHashSeparateChaining(int pTamanioIncial, double pFactorCarga) 
	{
		// Cuando llegue al factor de carga se tiene que hacer rehash
		listaNodos = new ArregloDinamico<> (pTamanioIncial);
		tamanioActual = 0;
		tamanioTabla = pTamanioIncial;
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
	public ILista<V> valueSet() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) 
	{
		V resp = null;
		int posicion = hash(key);
		ILista<NodoTS<K, V>> lista = listaNodos.getElement(posicion);
		if(lista != null )
		{
			for( int i = 1; i <= lista.size() && resp == null; i ++)
			{
				if(lista.getElement(i).getKey().compareTo(key) == 0)
				{
					resp = lista.getElement(i).getValue();
				}
			}
		}
		return resp;
	}

	@Override
	public int size() 
	{
		return tamanioTabla;
	}

	@Override
	public void put(K key, V val)
	{
		int posicion = hash(key);
		ILista<NodoTS<K, V>> lista = listaNodos.getElement(posicion);
		if(lista.isEmpty() && !contains(key))
		{
			lista.addLast(new NodoTS<K, V>(key, val));
		}
		else
		{
			listaNodos.changeInfo(posicion, new ArregloDinamico<NodoTS<K, V>>(10)); 
			//DEPENDE DE FACTOR DE CARGA
			listaNodos.getElement(posicion).addLast(new NodoTS<K, V>(key, val));
		}
		tamanioActual ++;
	}

	@Override
	public void remove(K key) 
	{
		int posicion = hash(key);
		ILista<NodoTS<K, V>> lista = listaNodos.getElement(posicion);
		if(lista != null)
		{
			for( int i = 1; i <= lista.size(); i ++)
			{
				if(lista.getElement(i).getKey().compareTo(key) == 0)
				{
					lista.changeInfo(i, null);
				}
			}
		}
		tamanioActual --;
	}

	@Override
	public boolean contains(K key) 
	{
		boolean resp = false;
		int posicion = hash(key);
		ILista<NodoTS<K, V>> lista = listaNodos.getElement(posicion);
		if(lista != null )
		{
			for( int i = 1; i <= lista.size(); i ++)
			{
				if(lista.getElement(i).getKey().compareTo(key) == 0)
				{
					resp = true;
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
	

}
