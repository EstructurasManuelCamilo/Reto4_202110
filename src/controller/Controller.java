package controller;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.data_structures.NodoTS;
import model.logic.Modelo;
import model.logic.Reproduccion;
import model.logic.Video;
import model.logic.Video.ComparadorXLikes;
import model.utils.Ordenamientos;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	private Ordenamientos<Video> ordenamientos;

	private ComparadorXLikes comparar;

	private boolean cargados;

	long TInicio, TFin, tiempo;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
		ordenamientos = new Ordenamientos<Video>();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String caracteristica = "";
		String caracteristica2 = "";
		String nuevoGeneroMusical = "";
		double valorMin = 0;
		double valorMax = 0; 
		double numero = 0;
		double num2 = 0;
		double num3 = 0;
		double num4 = 0;
		String linea = "";
		int aleatorio = 0;
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:
				if(!cargados)
				{
					view.printMessage("Inicio de lectura de los archivos.\n----------------"); 
					TInicio = System.currentTimeMillis();
					modelo.leerDatosRBT();
					tiempo = System.currentTimeMillis() - TInicio;
					System.out.println(tiempo);
					tiempo = 0;
					view.printMessage("El número total de conexiones (arcos) en el grafo es: ");
					view.printMessage("El número total de puntos de conexión (landing points) en el grafo es: " + modelo.darArbolDance().size());
					view.printMessage("La altura del árbol RBT: " + modelo.darArbolDance().darAlturaTotal());
					view.printMessage("La llave menor es: " + modelo.darArbolDance().min() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbolDance().get(modelo.darArbolDance().min()).size());
					view.printMessage("La llave mayor es: " + modelo.darArbolDance().max() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbolDance().get(modelo.darArbolDance().max()).size());
					view.printMessage("El número de hojas en el árbol RBT: " + modelo.darArbolDance().darNumeroHojas());
				}
				cargados = true;
				break;
				
			case 2:
				view.printMessage("Requerimiento 1.\n--------------------"); 
				view.printMessage("Ingrese el nombre de un punto de conexión) "); 
				while(caracteristica.equals(""))
				{
					caracteristica = lector.nextLine();
				}
				NodoTS<String, ArregloDinamico<Reproduccion>> solucion5 = modelo.darEstimarReproduccionesPorTiempo(resp, resp2);
				if ( solucion5 == null) 
					view.printMessage("No se pudo encontrar respuesta al requerimiento");
				else
				{
					view.printMessage("El género más referenciado en el rango de horas es: " + solucion5.getKey() + "--"+ solucion5.getValue().size());
					ArregloDinamico<Reproduccion> unicos = new ArregloDinamico<Reproduccion>(7);
					unicos = modelo.darRepDiferentes(solucion5.getValue());
					for(int i = 1; i < 10; i++)
					{
						Reproduccion act = unicos.getElement(i);
						modelo.asignarHashtags(act);
						view.printMessage("La reproducción " + i + " tiene: " + (act.darHashtag().size()) + " Hashtags" + " Vader promedio: " + modelo.darPromedioVaders(act.darHashtag()));
					}
				}
				numero = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0; 
				linea = "";
				caracteristica = "";
				caracteristica2 = "";
				nuevoGeneroMusical = "";
				break;
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
