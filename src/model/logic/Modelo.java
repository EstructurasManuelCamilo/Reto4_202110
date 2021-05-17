package model.logic;

import model.data_structures.*;
import model.logic.Video.ComparadorXLikes;
import model.logic.Video.ComparadorXVistas;
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

	private ArregloDinamico<Video> datosArreglo; 

	private ListaEncadenada<Video> datosLista;

	private TablaSimbolos<String, ILista<Video>> datosTablaSimbolos;

	private TablaHashLinearProbing<String,Video> datosLinearProbing;

	private TablaHashSeparateChaining<String, ILista<Video>> datosSeparateChaining;

	private Ordenamientos<Video> ordenamientos;

	private ArregloDinamico<Categoria> categorias; 

	private int diasTendencia;

	private int cantidadDuplas;

	private float tiempoEjecucionPromedio;

	private float tiempoEjecucionPromedio2;

	private int cantidadReproducciones;

	private ArregloDinamico<String> listaPaises;

	

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosArreglo = new ArregloDinamico<Video>(501);
		datosLista = new ListaEncadenada<Video>();
		ordenamientos = new Ordenamientos<>();
		diasTendencia = 0;
		cantidadDuplas = 0;
		tiempoEjecucionPromedio = 0;
		cantidadReproducciones = 0;
		datosTablaSimbolos = new TablaSimbolos<>();
		datosLinearProbing = new TablaHashLinearProbing<>(5013);//14, 5013
		datosSeparateChaining = new TablaHashSeparateChaining<>(75189);//201, 75189
		listaPaises = new ArregloDinamico<>(7);

	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datosArreglo = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoArreglo()
	{
		return datosArreglo.size();
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
	public void agregar(Video element)
	{	
		datosArreglo.addLast(element); // es O(1)
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Video buscar(Video dato)
	{
		if(datosArreglo.isPresent(dato)!= -1)
			return datosArreglo.getElement(dato);
		else
			return null;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Video eliminar(Video dato)
	{
		return datosArreglo.deleteElement(dato);
	}

	public void invertir()
	{
		datosArreglo.invertir();
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
				resp += datosArreglo.getElement(i) + ",";
			}
			resp += datosArreglo.getElement(darTamanoArreglo() - 1) +"]";
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
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/context_content_features-small.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel: separador)
			{	
				double instrumentalness = Double.parseDouble(excel.get("instrumentalness"));
				double danceability = Double.parseDouble(excel.get("danceability"));
				double liveness = Double.parseDouble(excel.get("liveness"));
				double speechiness = Double.parseDouble(excel.get("speechiness"));
				double valence = Double.parseDouble(excel.get("valence"));
				double loudness = Double.parseDouble(excel.get("loudness"));
				double tempo = Double.parseDouble(excel.get("tempo"));
				double acousticness = Double.parseDouble(excel.get("acousticness"));
				double energy = Double.parseDouble(excel.get("energy"));
				double mode = Double.parseDouble(excel.get("mode"));
				double key = Double.parseDouble(excel.get("key"));
				String artist_id = excel.get("artist_id");
				String tweet_lang = excel.get("tweet_lang");
				String track_id = excel.get("track_id");
				String created_at = excel.get("created_at");
				String lang = excel.get("lang");
				String time_zone = excel.get("time_zone");
				String user_id = excel.get("user_id");
				String id = excel.get("id");

				Reproduccion nuevo = new Reproduccion(danceability, instrumentalness, liveness, speechiness, valence, loudness, tempo, acousticness, energy, mode, key, id, artist_id, track_id, user_id, created_at);



				Date created_at2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(excel.get("created_at"));

				String horas = String.valueOf(created_at2.getHours());
				String minutos = String.valueOf(created_at2.getMinutes());
				String segundo = String.valueOf(created_at2.getSeconds());
				String union = horas + minutos + segundo;
				int resp = Integer.parseInt(union);
				cantidadReproducciones++;				
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
