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
import java.util.ArrayList;
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

	private int cantidadLP;

	private int numEdges;

	private ArregloDinamico<Pais> listaPaises;

	private ArregloDinamico<Vertex<String, LandingPoint>> listaVertices;

	private NoDirectedGraph<String, Vertex<String, LandingPoint>> graph;
	
	private DirectedGraph<String, Vertex<String, LandingPoint>> graphDirected;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		diasTendencia = 0;
		cantidadDuplas = 0;
		tiempoEjecucionPromedio = 0;
		cantidadLP = 0;
		numEdges = 0;
		listaPaises = new ArregloDinamico<>(7);
		listaVertices = new ArregloDinamico<>(10);
		graph = new NoDirectedGraph<>(20);
		graphDirected = new DirectedGraph<>(7);
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		listaVertices = new ArregloDinamico(capacidad);
	}

	public NoDirectedGraph<String, Vertex<String, LandingPoint>> darGraph()
	{
		return graph;
	}

	public ArregloDinamico<Pais> darPaises()
	{
		return listaPaises;
	}

	public ArregloDinamico<Vertex<String, LandingPoint>> darVertices()
	{
		return listaVertices;
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
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/connections.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel: separador)
			{	
				String origen = excel.get("origin");
				String destino = excel.get("destination");
				String nombreCable = excel.get("cable_name");
				String idCable = excel.get("cable_id");
				String longitudCable = excel.get("cable_length");


				Vertex<String, Vertex<String, LandingPoint>> orig = graph.getVertex(origen);
				Vertex<String, Vertex<String, LandingPoint>> des = graph.getVertex(destino);

				if(orig != null && des != null)
				{
					Float distancia = (float) distance(Double.parseDouble(orig.getInfo().getInfo().getLatitude()), Double.parseDouble(orig.getInfo().getInfo().getLongitude()), Double.parseDouble(des.getInfo().getInfo().getLatitude()), Double.parseDouble(des.getInfo().getInfo().getLongitude()));
					graph.addEdge(origen, destino, distancia);
					graphDirected.addEdge(origen, destino, distancia);
					orig.getInfo().getInfo().añadirAdyacente(des.getInfo().getInfo());
					numEdges++;
				}
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
				String[] partesName = name.split(", ");
				String pais = partesName[partesName.length-1];

				LandingPoint land = new LandingPoint(landing_point_id, id, name, latitude, longitude, pais);
				Vertex<String, LandingPoint> nuevo = new Vertex<String, LandingPoint>(landing_point_id, land);
				listaVertices.addLast(nuevo);
				graph.insertVertex(landing_point_id, nuevo);
				graphDirected.insertVertex(landing_point_id, nuevo);

				cantidadLP++;
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
				String poblacion = excel.get("Population");
				String cantUsuarios = excel.get("Internet users");


				Pais nuevo = new Pais(nombre, capital, capLatitude, capLongitude, codigo, poblacion, cantUsuarios);

				if(listaPaises.isPresent(nuevo) == -1)
				{
					listaPaises.addLast(nuevo);
					LandingPoint land = new LandingPoint(capital, codigo, capital, capLatitude, capLongitude, nombre);
					Vertex<String, LandingPoint> nuevo2 = new Vertex<String, LandingPoint>(capital, land);
					listaVertices.addLast(nuevo2);
					cantidadLP++;
					graph.insertVertex(capital, nuevo2);
					graphDirected.insertVertex(capital, nuevo2);
					ArregloDinamico<Vertex<String, LandingPoint>> verticesPais = darVarticesPais(nombre);
					for(int i = 0; i < verticesPais.size(); i++)
					{
						Vertex<String, LandingPoint> actual = verticesPais.getElement(i);
						try {
							Float distancia = (float) distance(Double.parseDouble(capLatitude), Double.parseDouble(capLongitude), Double.parseDouble(actual.getInfo().getLatitude()), Double.parseDouble(actual.getInfo().getLongitude()));
							graph.addEdge(capital,actual.getId(),distancia);
							graphDirected.addEdge(capital,actual.getId(),distancia);
							nuevo2.getInfo().añadirAdyacente(actual.getInfo());
							numEdges++;
						}
						catch(Exception e)
						{
							graph.addEdge(capital,actual.getId(),0);
							graphDirected.addEdge(capital,actual.getId(),0);
							nuevo2.getInfo().añadirAdyacente(actual.getInfo());
							numEdges++;
						}
					}
				}
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

	public double distance(double startLat, double startLong,
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

	public ArregloDinamico<Vertex<String, LandingPoint>> darVarticesPais(String pais)
	{
		ArregloDinamico<Vertex<String, LandingPoint>> vertices = new ArregloDinamico<>(10);
		for(int i = 0; i < cantidadLP; i++)
		{
			Vertex<String, Vertex<String, LandingPoint>> actual = graph.vertices().getElement(i);
			if(actual.getInfo().getInfo().getPais().equals(pais))
			{
				vertices.addLast(actual.getInfo());
			}
		}
		return vertices;
	}

	public int darCantidadLandingPoints() 
	{
		return cantidadLP;
	}

	public int darNumEdges() 
	{
		return numEdges;
	}
	public LandingPoint darLPNombre(String pNombre)
	{
		LandingPoint resp = null;
		for(int i = 0; i < listaVertices.size(); i++)
		{
			Vertex<String, LandingPoint> act = listaVertices.getElement(i);
			if(act !=null)
			{
				if(act.getInfo().getName().equals(pNombre))
				{
					resp = act.getInfo();
				}
			}
		}
		return resp;
	}

	public int gradoPuntoConeccion(String pNombre)
	{
		int resp = 0;
		LandingPoint parametro = darLPNombre(pNombre);
		if(parametro!=null)
			resp = parametro.darAdyacentes().size();
		return resp;
	}
	/**
	 * Requerimiento 1 cantidad de clústeres dentro de la red de cables submarinos y si dos landing points. 
	 */
	public TablaSimbolos<Integer, Boolean> cantidadClustersDentroRed(String pLand1, String pLand2)
	{
		TablaSimbolos<Integer, Boolean> resp = new TablaSimbolos<Integer, Boolean>();
		int resp1 = 0;
		boolean resp2 = false;
		if(graphDirected.getSCC().size()!= 0)
		{
			resp1 = graphDirected.getSCC().size();
		}
		for(int i = 0; i < graphDirected.getSCC().size(); i++)
		{
			if(graphDirected.getSCC().contains(pLand1) && graphDirected.getSCC().contains(pLand2))
				resp2 = true;
		}
		resp.put(resp1, resp2);
		return resp;
	}
	/**
	 * Requerimiento 2 landing point(s) que sirven como punto de interconexión a más cables en la red.
	 */
	public TablaSimbolos<ILista<String>, Integer> masCablesRed()
	{
		TablaSimbolos<ILista<String>, Integer>  respFinal = new TablaSimbolos<>();
		int max = 0;
		ILista<String> resp = new ArregloDinamico<>(7);
		int resp2 = 0;
		for (int i = 0; i < graph.vertices().size(); i++) 
		{
			if(graph.vertices().getElement(i).getEdges().size() > max)
			{
				resp.addLast(graph.vertices().getElement(i).toString());
				max = graph.vertices().getElement(i).getEdges().size();
				resp2 ++;
			}
		}
		respFinal.put(resp, resp2);
		return respFinal;
	}
}
