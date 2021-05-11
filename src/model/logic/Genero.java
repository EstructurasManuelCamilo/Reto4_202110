package model.logic;

import model.data_structures.ArregloDinamico;

public class Genero implements Comparable<Genero>
{
	private double inicio;
	private double fin;
	private String nombre;
	private ArregloDinamico<Reproduccion> reproducciones;
	
	public Genero(double pInicio, double pFin, String pNombre)
	{
		inicio = pInicio;
		fin = pFin;
		nombre = pNombre;
		reproducciones = new ArregloDinamico<>(7);
	}
	public ArregloDinamico<Reproduccion> darReproducciones()
	{
		return reproducciones;
	}
	public double darInicio()
	{
		return inicio;
	}
	public double darFin()
	{
		return fin;
	}
	public String darNombre()
	{
		return nombre;
	}

	@Override
	public int compareTo(Genero o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	public void aniadirReproduccion(Reproduccion nuevo) 
	{
		reproducciones.addLast(nuevo);
	}
	
}
