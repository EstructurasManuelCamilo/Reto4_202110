package controller;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.data_structures.NodoTS;
import model.logic.Modelo;

import model.utils.Ordenamientos;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

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
					modelo.leerLandingPoints();
					modelo.leerPaises();
					modelo.leerDatosGrafo();
					tiempo = System.currentTimeMillis() - TInicio;
					System.out.println(tiempo);
					tiempo = 0;
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
