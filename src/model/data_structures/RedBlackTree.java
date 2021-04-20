package model.data_structures;

import java.awt.Color;

public class RedBlackTree <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolosOrdenada <K,V>
{
	private NodoRBT root;
	
	@Override
	public ILista<K> keySet() 
	{
		return null;
	}

	@Override
	public ILista<V> valueSet() 
	{
		return null;
	}

	@Override
	public V get(K key) 
	{
		return null;
	}

	@Override
	public int size() 
	{
	
		return null;
	}

	@Override
	public void put(K key, V val) 
	{
		if(root != null)
			root = ((NodoRBT<K, V>)root.put(key, val));
		else
		{
			root = new NodoRBT<>(key, val);
			((NodoRBT<K, V>)root).cambiarColor(Color.BLACK);
		}
	}

	@Override
	public void remove(K key) 
	{
		
	}

	@Override
	public boolean contains(K key)
	{
		return false;
	}

	@Override
	public boolean isEmpty() 
	{
		return false;
	}

	@Override
	public int hash(K key) 
	{
		return 0;
	}

	@Override
	public int getHeight(K key) 
	{
		return 0;
	}

	@Override
	public int height() 
	{
		return 0;
	}

	@Override
	public K min() 
	{
		return null;
	}

	@Override
	public K max() 
	{
		return null;
	}

}
