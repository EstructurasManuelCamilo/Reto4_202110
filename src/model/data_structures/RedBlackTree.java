package model.data_structures;

import java.awt.Color;

public class RedBlackTree <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolosOrdenada <K,V>
{
	private NodoRBT<K, V> root;

	@Override
	public ILista<K> keySet() 
	{
		//Recorrido inroden
		ArregloDinamico<K> lista = new ArregloDinamico<>(502);
		if(root != null)
		{
			root.insertarSiguienteLLave(lista);
		}

		return lista;
	}

	@Override
	public ILista<V> valueSet() 
	{
		//Recorrido inroden
		ArregloDinamico<V> lista = new ArregloDinamico<>(502);
		if(root != null)
		{
			root.insertarSiguienteValor(lista);
		}

		return lista;
	}

	@Override
	public ArregloDinamico<V> get(K key) 
	{
		ArregloDinamico<V> val = null;
		if(root != null)
			val = root.get(key);
		return val;
	}

	@Override
	public int size() 
	{
		int size = 0;
		if(root != null)
			size = root.size(size);
		return size;
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
	public boolean contains(K key)
	{
		if(get(key) != null)
			return true; 
		else
			return false;
	}

	@Override
	public boolean isEmpty() 
	{
		if(size()==0)
			return true;
		else
			return false;
	}
	@Override
	public int getHeight(K key) 
	{
		int h = -1;
		if(root != null)
		{
			h = root.getHeight(key, 0);
		}
		if(h == 0)
		{
			h = -1;
		}
		return h;
	}

	@Override
	public int height() 
	{
		int h = 0;
		if(root != null)
		{
			h = root.height(0);
		}
		return h;
	}

	@Override
	public K min() 
	{
		K min = null;
		if(root != null)
		{
			min = (K) root.min();
		}
		return min;
	}

	@Override
	public K max() 
	{
		K max = null;
		if(root != null)
		{
			max = (K) root.max();
		}
		return max;
	}

	@Override
	public ILista<K> keysInRange(K init, K end) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<V> valuesInRange(K init, K end) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int darAlturaTotal() 
	{
		int altura = 0;
		if(root != null)
		{
			altura++;
			root.darAltura();
		}
		return altura;
	}

	public int darNumeroHojas() 
	{
		int numeroHojas = 0;
		if(root != null)
		{
			root.darNumeroHojas(numeroHojas);
		}
		return numeroHojas;
	}

}
