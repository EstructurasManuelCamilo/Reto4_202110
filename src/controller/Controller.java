package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.logic.Modelo;
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
		String pais = "";
		String categoria = "";
		int numero = 0;
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:
				if(!cargados)
				{
					view.printMessage("Inicio de lectura de los archivos.\n----------------"); 
					TInicio = System.currentTimeMillis();
					modelo.leerDatosTablasHash();
					tiempo = System.currentTimeMillis() - TInicio;
					System.out.println(tiempo);
					tiempo = 0;
					view.printMessage("El número total de reproducciones: " + modelo.darCantidadVideos());
					view.printMessage("El número de llaves presentes en el árbol RBT: " + modelo.darCantidadCategorias());
					view.printMessage("La altura del árbol RBT: " + modelo.darCantidadCategorias());
					view.printMessage("La llave menor es:" + modelo.darCantidadCategorias() + "y el total de sus reproducciones en el árbol RBT es: " + modelo.darCantidadCategorias());
					view.printMessage("La llave mayor es:" + modelo.darCantidadCategorias() + "y el total de sus reproducciones en el árbol RBT es: " + modelo.darCantidadCategorias());
					view.printMessage("El número de hojas en el árbol RBT: " + modelo.darCantidadCategorias());
				}
				cargados = true;
				break;

			case 3:
				break;
				
			case 4:
				break;
				
			case 5: 
				break;
				
			case 2: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
