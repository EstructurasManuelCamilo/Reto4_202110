package model.logic;

import model.data_structures.*;

import model.utils.Ordenamientos;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	/**
	 * Atributos del modelo del mundo
	 */
	long TInicio, TFin, tiempo;
	long TInicio2, TFin2, tiempo2;

	private int diasTendencia;

	private int cantidadDuplas;

	private float tiempoEjecucionPromedio;

	private float tiempoEjecucionPromedio2;

	private int cantidadReproducciones;

	private ArregloDinamico<Pais> listaPaises;

	private ArregloDinamico<Vertex<String, LandingPoint>> listaVertices;//arreglar

	private NoDirectedGraph<String, Vertex<String, LandingPoint>> graph;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		diasTendencia = 0;
		cantidadDuplas = 0;
		tiempoEjecucionPromedio = 0;
		cantidadReproducciones = 0;
		listaPaises = new ArregloDinamico<>(7);
		listaVertices = new ArregloDinamico<>(10);
		graph = new NoDirectedGraph<>(20);
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		listaVertices = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoArreglo()
	{
		return listaVertices.size();
	}

	public int darDiasTendencia()
	{
		return diasTendencia;
	}

	public int darCantidadReproducciones()
	{
		return cantidadReproducciones;
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Vertex element)
	{	
		listaVertices.addLast(element); // es O(1)
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Vertex buscar(Vertex dato)
	{
		if(listaVertices.isPresent(dato)!= -1)
			return listaVertices.getElement(dato);
		else
			return null;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Vertex eliminar(Vertex dato)
	{
		return listaVertices.deleteElement(dato);
	}

	public void invertir()
	{
		listaVertices.invertir();
	}

	@Override 
	public String toString()
	{
		if (darTamanoArreglo() == 0)
			return "[]";
		else{
			String resp = "[";
			for (int i = 0; i < darTamanoArreglo() - 1; i++) 
			{
				resp += listaVertices.getElement(i) + ",";
			}
			resp += listaVertices.getElement(darTamanoArreglo() - 1) +"]";
			return resp;
		}
	}


	public int darDuplas()
	{
		return cantidadDuplas;
	}

	public float darTiempoEjecucionPromedio()
	{
		return tiempoEjecucionPromedio;
	}

	public float darTiempoEjecucionPromedio2()
	{
		return tiempoEjecucionPromedio2;
	}


	public void leerDatosGrafo()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/connetions.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel: separador)
			{	
				String origen = excel.get("origin");
				String destino = excel.get("destination");
				String nombreCable = excel.get("cable_name");
				String idCable = excel.get("cable_id");
				String longitudCable = excel.get("cable_length");
				String[] partesLon = longitudCable.split(" ");
				String[] partes2 = partesLon[0].split(",");
				String mil = partes2[0];
				String cien = partes2[1];
				String dis = mil + cien;
				Float distancia = Float.parseFloat(dis);

				graph.addEdge(origen, destino, distancia);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void leerLandingPoints()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/landing_points.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{	
				String landing_point_id = excel.get("landing_point_id");
				String id = excel.get("id");
				String name = excel.get("name");
				String latitude = excel.get("latitude");
				String longitude = excel.get("longitude");

				LandingPoint land = new LandingPoint(landing_point_id, id, name, latitude, longitude);
				Vertex<String, LandingPoint> nuevo = new Vertex<String, LandingPoint>(landing_point_id, land);
				listaVertices.addLast(nuevo);
				graph.insertVertex(landing_point_id, nuevo);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void leerPaises()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/countries.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{	
				String nombre = excel.get("CountryName");
				String capital = excel.get("CapitalName");
				String capLatitude = excel.get("CapitalLatitude");
				String capLongitude = excel.get("CapitalLongitude");
				String codigo = excel.get("CountryCode");

				Pais nuevo = new Pais(nombre, capital, capLatitude, capLongitude, codigo);
				listaPaises.addLast(nuevo);
				LandingPoint land = new LandingPoint(capital, codigo, capital, capLatitude, capLongitude);
				Vertex<String, LandingPoint> nuevo2 = new Vertex<String, LandingPoint>(capital, land);
				listaVertices.addLast(nuevo2);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Jason Winn
	 * http://jasonwinn.org
	 * Created July 10, 2013
	 *
	 * Description: Small class that provides approximate distance between
	 * two points using the Haversine formula.
	 *
	 * Call in a static context:
	 * Haversine.distance(47.6788206, -122.3271205,
	 *                    47.6788206, -122.5271205)
	 * --> 14.973190481586224 [km]
	 *
	 */
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	public static double distance(double startLat, double startLong,
			double endLat, double endLong) {

		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

}
