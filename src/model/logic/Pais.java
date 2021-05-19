package model.logic;

import java.util.Date;

public class Pais implements Comparable<Pais>
{

	private String nombre;
	private String capital;
	private String latCap;
	private String longCap;
	private String codigo;
	public Pais(String pNombre, String pCapital, String pCapLat, String pCapLong, String pCode, String pPoblacion, String pCantUsuarios)
	{
		nombre = pNombre;
		capital = pCapital;
		latCap = pCapLat;
		longCap = pCapLong;
		codigo = pCode;
		poblacion = pPoblacion;
		cantUsuarios = pCantUsuarios;
	}


	public String getNombre() {
		return nombre;
	}


	public String getCapital() {
		return capital;
	}


	public String getLatCap() {
		return latCap;
	}


	public String getLongCap() {
		return longCap;
	}


	public String getCodigo() {
		return codigo;
	}

	private String poblacion;
	public String getPoblacion() {
		return poblacion;
	}


	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	public String getCantUsuarios() {
		return cantUsuarios;
	}


	public void setCantUsuarios(String cantUsuarios) {
		this.cantUsuarios = cantUsuarios;
	}


	private String cantUsuarios;


	@Override
	public int compareTo(Pais o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
