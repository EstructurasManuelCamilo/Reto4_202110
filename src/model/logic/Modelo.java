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
	
	private RedBlackTree<Double, Reproduccion> arbolDance;

	private RedBlackTree<Double, Reproduccion> arbolValencia;
	
	private RedBlackTree<Double, Reproduccion> arbolSonoridad;
	
	private RedBlackTree<Double, Reproduccion> arbolTempo;
	
	private RedBlackTree<Double, Reproduccion> arbolEnergia;
	
	private RedBlackTree<Double, Reproduccion> arbolInstrumentalidad;
	
	private RedBlackTree<Double, Reproduccion> arbolViveza;

	private RedBlackTree<Double, Reproduccion> arbolClave;
	
	private RedBlackTree<Double, Reproduccion> arbolModo;
	
	private RedBlackTree<Double, Reproduccion> arbolhabla;
	
	private RedBlackTree<Double, Reproduccion> arbolAcustica;
	
	private RedBlackTree<Integer, Reproduccion> arbolHoras;
	
	private ArregloDinamico<Hashtag> hashtags; 
	
	private ArregloDinamico<Vader> vaders; 
	
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
		
		arbolAcustica = new RedBlackTree<>();
		arbolClave = new RedBlackTree<>();
		arbolDance = new RedBlackTree<>();
		arbolEnergia = new RedBlackTree<>();
		arbolhabla = new RedBlackTree<>();
		arbolInstrumentalidad = new RedBlackTree<>();
		arbolModo = new RedBlackTree<>();
		arbolSonoridad = new RedBlackTree<>();
		arbolTempo = new RedBlackTree<>();
		arbolValencia = new RedBlackTree<>();
		arbolViveza = new RedBlackTree<>();
		
		arbolHoras = new RedBlackTree<>();
		
		hashtags = new ArregloDinamico<>(30);
		vaders = new ArregloDinamico<>(30);
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
	
	public ArregloDinamico<Hashtag> darHashtags()
	{
		return hashtags;
	}
	
	public ArregloDinamico<Vader> darVaders()
	{
		return vaders;
	}


	public void leerDatosRBT()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/context_content_features-small.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
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
				arbolDance.put(danceability, nuevo);
				arbolValencia.put(valence, nuevo);
				arbolSonoridad.put(loudness, nuevo);
				arbolInstrumentalidad.put(instrumentalness, nuevo);
				arbolEnergia.put(energy, nuevo);
				
				Date created_at2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(excel.get("created_at"));
				int horas = created_at2.getHours();
				arbolHoras.put(horas, nuevo);
				
				cantidadReproducciones++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void leerHashtag()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/user_track_hashtag_timestamp-small.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{	
				String user_id = excel.get("user_id");
				String track_id = excel.get("track_id");
				String hashtag = excel.get("hashtag");
				Date created_at = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(excel.get("created_at"));
				Hashtag nuevo = new Hashtag(user_id, track_id, hashtag, created_at);
				hashtags.addLast(nuevo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void leerVader()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/sentiment_values.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{	
				double vaderPromedio = Double.parseDouble(excel.get("vader_avg"));
				String hashtag = excel.get("hashtag");
				Vader nuevo = new Vader(hashtag, vaderPromedio);
				vaders.addLast(nuevo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public float desempenioMetodoGetLlavesExistentes() 
	{
		int i = 0;
		int prom1 = 0;
		int prom2 = 0;
		tiempoEjecucionPromedio = 0;
		while(i < 700)
		{
			int min = 0;
			int max = datosTablaSimbolos.keySet().size() - 1;
			int random_int = (int)(Math.random() * (max - min + 1) + min);
			String llaveTemp = datosTablaSimbolos.keySet().getElement(random_int);
			
			TInicio = System.currentTimeMillis();
			datosTablaSimbolos.get(llaveTemp);
			tiempo = System.currentTimeMillis() - TInicio;
			prom1 += tiempo;
			i ++;
		}
		prom1 /= 700;
		while(i < 300)
		{
			String llaveTemp = "";
			TInicio = System.currentTimeMillis();
			datosTablaSimbolos.get(llaveTemp);
			tiempo = System.currentTimeMillis() - TInicio;
			prom2 += tiempo;
			i ++;
		}
		prom2 /= 300;
		tiempoEjecucionPromedio = (prom1 + prom2)/2;
		return tiempoEjecucionPromedio;
	}

	public RedBlackTree<Double, Reproduccion> darArbolDance() 
	{
		return arbolDance;
	}
	public RedBlackTree<Double, Reproduccion> darArbolValencia() 
	{
		return arbolValencia;
	}
	public RedBlackTree<Double, Reproduccion> darArbolSonoridad() 
	{
		return arbolSonoridad;
	}
	public RedBlackTree<Double, Reproduccion> darArbolInstrumentalidad() 
	{
		return arbolInstrumentalidad;
	}
	public RedBlackTree<Double, Reproduccion> darArbolEnergia() 
	{
		return arbolEnergia;
	}
	
	
	
	// Requirimiento 1. Conocer cuántas reproducciones (eventos de escucha) se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darReproduccionesPorCaracteristica(String  pCaracteristica, double pMin, double pMax)
	{
		ArregloDinamico<Double> arreglo = new ArregloDinamico<>(30);
		ArregloDinamico<Double> resp = new ArregloDinamico<>(30);
		arreglo = arbolDance.keysInRange(pMin, pMax);
		for(int i = 0; i < arreglo.size(); i++ )
		{
			
		}
		// for arreglo
			// mirar cuales tiene la caract
		return null;
	}
	// Requerimiento 2. Encontrar la lista de pistas que se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darListaPorPistasFestejar(double pMinEnergy, double pMaxEnergy, double pMinDanceability, double pMaxDanceability)
	{
		ArregloDinamico<Double> arreglo = new ArregloDinamico<>(7);
		ArregloDinamico<Double> resp = new ArregloDinamico<>(7);
		return null;
		
	}
	// Requerimiento 3. Encontrar la lista de pistas que se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darListaPorPistasEstudiar(double pMinInstrumentalness, double pMaxInstrumentalness, double pMinTempo, double pMaxTempo)
	{
		ArregloDinamico<Double> arreglo = new ArregloDinamico<>(7);
		ArregloDinamico<Double> resp = new ArregloDinamico<>(7);
		return null;
		
	}
	// Requerimiento 4. Encontrar la lista de pistas que se tienen en el sistema de recomendación
	// Toca hacer un arreglo dinámico de arreglos dinámicos
	public ArregloDinamico<Reproduccion> darEstimarReproduccionesPorGenero(String pListaGenero, String pNombreGenero, double pMinTempo, double pMaxTempo )
	{
		ArregloDinamico<Double> arreglo = new ArregloDinamico<>(7);
		ArregloDinamico<Double> resp = new ArregloDinamico<>(7);
		return null;
		
	}
	// Requerimiento 5. indicar el género de música más escuchado en un rango teniendo en cuenta
	// todos los días disponibles e informar el promedio VADER
	public ArregloDinamico<Reproduccion> darEstimarReproduccionesPorGenero(double pMinHora, double pMaxHora )
	{
		ArregloDinamico<Double> arreglo = new ArregloDinamico<>(7);
		ArregloDinamico<Double> resp = new ArregloDinamico<>(7);
		return null;
		
	}

}
