package model.data_structures;

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
		return get(root, key);
	}

	@Override
	public int size() 
	{
	
		return size(root);
	}

	@Override
	public void put(K key, V val) 
	{
		if(root != null)
			root = (root.put(key, val));
		else
		{
			root = new NodoRBT<>(key, val);
			root.cambiarColor(root);
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
