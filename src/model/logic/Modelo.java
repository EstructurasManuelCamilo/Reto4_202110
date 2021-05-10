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
				insertarGeneros(nuevo, tempo);
				arbolDance.put(danceability, nuevo);
				arbolValencia.put(valence, nuevo);
				arbolSonoridad.put(loudness, nuevo);
				arbolInstrumentalidad.put(instrumentalness, nuevo);
				arbolEnergia.put(energy, nuevo);
				arbolTempo.put(tempo, nuevo);
				arbolAcustica.put(acousticness, nuevo);
				arbolhabla.put(speechiness, nuevo);

				Date created_at2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(excel.get("created_at"));
				int horas = created_at2.getHours();
				arbolHoras.put(horas, nuevo);

				cantidadReproducciones++;
				leerHashtag();
				leerVader();
				
				nuevo.asignarHashtag(hashtags);
				nuevo.asignarVader(vaders);
				
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
				double vaderPromedio = 0;
				if( !excel.get("vader_avg").equals(""))
				{
					 vaderPromedio = Double.parseDouble(excel.get("vader_avg"));
				}
				else
				{
					 vaderPromedio = 2;
				}
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
	public RedBlackTree<Double, Reproduccion> darArbolTempo() 
	{
		return arbolTempo;
	}
	public RedBlackTree<Double, Reproduccion> darArbolAcustica() 
	{
		return arbolAcustica;
	}
	public RedBlackTree<Double, Reproduccion> darArbolHabla() 
	{
		return arbolhabla;
	}



	// Requirimiento 1. Conocer cuántas reproducciones (eventos de escucha) se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darReproduccionesPorCaracteristica(String  pCaracteristica, double pMin, double pMax) throws Exception
	{
		ArregloDinamico<Reproduccion> resp = new ArregloDinamico<>(30);

		RedBlackTree<Double, Reproduccion> arbol = null;

		if(pCaracteristica.equals("Danceability"))
		{
			arbol = arbolDance;
		}
		else if(pCaracteristica.equals("Instrumentalness"))
		{
			arbol = arbolInstrumentalidad;
		}
		else if(pCaracteristica.equals("Valence"))
		{
			arbol = arbolValencia;
		}
		else if(pCaracteristica.equals("Energy"))
		{
			arbol = arbolEnergia;
		}
		else if(pCaracteristica.equals("Acousticness"))
		{
			arbol = arbolAcustica;
		}
		else if(pCaracteristica.equals("Speechiness"))
		{
			arbol = arbolhabla;
		}
		else
		{
			throw new Exception("No se encontro a categoria buscada. ");
		}
		ArregloDinamico<Double> arreglo = arbol.keysInRange(pMin, pMax);
		for(int i = 0; i < arreglo.size(); i++)
		{
			if(arreglo.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> lista  = arbol.get(arreglo.getElement(i));
				for(int j = 0; j < lista.size(); j++)
					resp.addLast(lista.getElement(j));
			}
		}

		return resp;
	}
	// Requerimiento 2. Encontrar la lista de pistas que se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darListaPorPistasFestejar(double pMinEnergy, double pMaxEnergy, double pMinDanceability, double pMaxDanceability)
	{
		ArregloDinamico<Double> llavesEnergy = arbolEnergia.keysInRange(pMinEnergy, pMaxEnergy);
		ArregloDinamico<Double> llavesDance = arbolDance.keysInRange(pMinDanceability, pMaxDanceability);

		ArregloDinamico<Reproduccion> reproduccionesEnergy = new ArregloDinamico<>(30);
		ArregloDinamico<Reproduccion> reproduccionesDance = new ArregloDinamico<>(30);

		ArregloDinamico<Reproduccion> solucion = new ArregloDinamico<>(30);

		for(int i = 0; i < llavesEnergy.size(); i++)
		{
			if(llavesEnergy.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> listaEn  = arbolEnergia.get(llavesEnergy.getElement(i));
				listaEn = darRepDiferentes(listaEn);
				for(int j = 0; j < listaEn.size(); j++)
					reproduccionesEnergy.addLast(listaEn.getElement(j));
			}
		}
		for(int i = 0; i < llavesDance.size(); i++)
		{
			if(llavesDance.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> listaDa  = arbolDance.get(llavesDance.getElement(i));
				listaDa = darRepDiferentes(listaDa);
				for(int j = 0; j < listaDa.size(); j++)
					reproduccionesDance.addLast(listaDa.getElement(j));
			}
		}

		for(int i = 0; i < reproduccionesEnergy.size(); i++)
		{
			Reproduccion energyActual = reproduccionesEnergy.getElement(i);
			for(int j = 0; j < reproduccionesDance.size(); j++)
			{
				Reproduccion danceActual = reproduccionesDance.getElement(j);
				if(energyActual.darId().equals(danceActual.darId()))
				{
					solucion.addLast(energyActual);
				}
			}
		}
		return solucion;

	}
	// Requerimiento 3. Encontrar la lista de pistas que se tienen en el sistema de recomendación
	public ArregloDinamico<Reproduccion> darListaPorPistasEstudiar(double pMinInstrumentalness, double pMaxInstrumentalness, double pMinTempo, double pMaxTempo)
	{
		ArregloDinamico<Double> llavesIns = arbolInstrumentalidad.keysInRange(pMinInstrumentalness, pMaxInstrumentalness);
		ArregloDinamico<Double> llavesTempo = arbolTempo.keysInRange(pMinTempo, pMaxTempo);

		ArregloDinamico<Reproduccion> reproduccionesIns = new ArregloDinamico<>(30);
		ArregloDinamico<Reproduccion> reproduccionesTempo = new ArregloDinamico<>(30);

		ArregloDinamico<Reproduccion> solucion = new ArregloDinamico<>(30);

		for(int i = 0; i < llavesIns.size(); i++)
		{
			if(llavesIns.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> listaIns  = arbolInstrumentalidad.get(llavesIns.getElement(i));
				listaIns = darRepDiferentes(listaIns);
				for(int j = 0; j < listaIns.size(); j++)
					reproduccionesIns.addLast(listaIns.getElement(j));
			}
		}
		for(int i = 0; i < llavesTempo.size(); i++)
		{
			if(llavesTempo.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> listaTe  = arbolTempo.get(llavesTempo.getElement(i));
				listaTe = darRepDiferentes(listaTe);
				for(int j = 0; j < listaTe.size(); j++)
					reproduccionesTempo.addLast(listaTe.getElement(j));
			}
		}

		for(int i = 0; i < reproduccionesIns.size(); i++)
		{
			Reproduccion insActual = reproduccionesIns.getElement(i);
			for(int j = 0; j < reproduccionesTempo.size(); j++)
			{
				Reproduccion tempoActual = reproduccionesTempo.getElement(j);
				if(insActual.darId().equals(tempoActual.darId()))
				{
					solucion.addLast(insActual);
				}
			}
		}
		return solucion;

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
	public NodoTS<String, ArregloDinamico<Reproduccion>> darEstimarReproduccionesPorTiempo(int pMinHora, int pMaxHora )
	{
		ArregloDinamico<Reproduccion> resp = new ArregloDinamico<>(7);
		ArregloDinamico<Integer> arreglo = arbolHoras.keysInRange(pMinHora, pMaxHora);

		TablaSimbolos<String, Integer> tabla = new TablaSimbolos<>();
		tabla.put("Reggae", 0);
		tabla.put("Down-tempo", 0);
		tabla.put("Chill-out", 0);
		tabla.put("Hip-hop", 0);
		tabla.put("Jazz and Funk", 0);
		tabla.put("Pop", 0);
		tabla.put("R&B", 0);
		tabla.put("Rock", 0);
		tabla.put("Metal", 0);

		for(int i = 0; i < arreglo.size(); i++)
		{
			if(arreglo.getElement(i)!=null)
			{
				ArregloDinamico<Reproduccion> lista  = arbolHoras.get(arreglo.getElement(i));
				for(int j = 0; j < lista.size(); j++)
					resp.addLast(lista.getElement(j));
			}
		}

		for(int i = 0; i < resp.size(); i++)
		{
			Reproduccion actual = resp.getElement(i);
			ArregloDinamico<String> listaGenAct = actual.darGeneros();

			for(int j = 0; j < listaGenAct.size(); j++)
			{
				String gen = listaGenAct.getElement(j);
				for(int k = 0; k < tabla.size(); k++)
				{
					if(gen.equals(tabla.get(k).getKey()))
					{
						int anterior = tabla.get(k).getValue();
						tabla.cambiarVal(k, anterior++);;
					}
				}
			}
		}
	
		String genMayor = tabla.darMayor();
		NodoTS<String, ArregloDinamico<Reproduccion>> repuesta = new NodoTS<String, ArregloDinamico<Reproduccion>>(genMayor, darPistasGenero(genMayor, resp));
		return repuesta;

	}

	private ArregloDinamico<Reproduccion> darPistasGenero(String genMayor, ArregloDinamico<Reproduccion> resp) 
	{
		
		ArregloDinamico<Reproduccion> solucion = new ArregloDinamico(30);
		for(int i = 0; i < resp.size(); i++)
		{
			Reproduccion actual = resp.getElement(i);
			Boolean yaEsta = false;
			for(int j = 0; j < actual.darGeneros().size() && !yaEsta; j ++)
			{
				if(actual.darGeneros().getElement(j).equals(genMayor))
				{
					solucion.addLast(actual);
					yaEsta = true;
				}
			}
		}
		return solucion;
	}

	public ArregloDinamico<Reproduccion> darRepDiferentes(ArregloDinamico<Reproduccion> lista) 
	{
		ArregloDinamico<Reproduccion> resp = new ArregloDinamico(30);
		for(int i = 0; i < lista.size(); i++)
		{
			Reproduccion actual = lista.getElement(i);
			Boolean yaEsta = false;
			for(int j = 0; j < resp.size() && !yaEsta; j++)
			{
				if(actual.darId().equals(resp.getElement(j)))
				{
					yaEsta = true;
				}
			}
			if(!yaEsta)
			{
				resp.addLast(actual);
			}
		}
		return resp;
	}
	public ArregloDinamico<String> darArtistasDiferentes(ArregloDinamico<Reproduccion> lista)
	{
		ArregloDinamico<String> resp = new ArregloDinamico(30);
		for(int i = 0; i < lista.size(); i++)
		{
			Reproduccion actual = lista.getElement(i);
			Boolean yaEsta = false;
			for(int j = 0; j < resp.size() && !yaEsta; j++)
			{
				if(actual.darArtistId().equals(resp.getElement(j)))
				{
					yaEsta = true;
				}
			}
			if(!yaEsta)
			{
				resp.addLast(actual.darArtistId());
			}
		}
		return resp;
	}

	public void insertarGeneros(Reproduccion nuevo, double tempo)
	{
		if(tempo>60 && tempo<90) 
		{
			nuevo.insertarGenero("Reggae");
		}
		if(tempo>70 && tempo<100) 
		{
			nuevo.insertarGenero("Down-tempo");
		}
		if(tempo>90 && tempo<120) 
		{
			nuevo.insertarGenero("Chill-out");
		}
		if(tempo>85 && tempo<115) 
		{
			nuevo.insertarGenero("Hip-hop");
		}
		if(tempo>120 && tempo<125) 
		{
			nuevo.insertarGenero("Jazz and Funk");
		}
		if(tempo>100 && tempo<130) 
		{
			nuevo.insertarGenero("Pop");
		}
		if(tempo>60 && tempo<80) 
		{
			nuevo.insertarGenero("R&B");
		}
		if(tempo>110 && tempo<140) 
		{
			nuevo.insertarGenero("Rock");
		}
		if(tempo>100 && tempo<160) 
		{
			nuevo.insertarGenero("Metal");
		}
	}
	
	public double darPromedioVaders( ArregloDinamico<Vader> pVader )
	{
		double suma = 0;
		int cont = 0;
		for(int i = 0; i < pVader.size(); i ++)
		{
			if(pVader.getElement(i).vaderPromedio() != 2)
			{
				suma += pVader.getElement(i).vaderPromedio();
				cont += 1;
			}
		}
		return suma/cont;
	}
}
