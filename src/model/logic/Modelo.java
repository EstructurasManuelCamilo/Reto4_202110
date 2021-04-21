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
	
	private RedBlackTree<Double, Reproduccion> arbol;

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


	public void leerDatosRBT()
	{
		try 
		{
			final Reader pDatos = new InputStreamReader (new FileInputStream(new File("./data/context_content_features-small.csv")),"UTF-8");
			final CSVParser separador = new CSVParser(pDatos, CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(','));
			for(final CSVRecord excel : separador)
			{		
				String dance = excel.get("danceability");
				double danceability = Double.parseDouble(dance);
				Reproduccion nuevo = new Reproduccion(danceability);
				arbol.put(danceability, nuevo);
				cantidadReproducciones++;
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

	public RedBlackTree<Double, Reproduccion> darArbol() 
	{
		return arbol;
	}

}
