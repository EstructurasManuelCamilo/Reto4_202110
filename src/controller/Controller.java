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
					modelo.leerDatosTablasHash();
					view.printMessage("El total de videos cargados es: " + modelo.darCantidadVideos());
					view.printMessage("El total de duplas en separate chaining es: " + modelo.darDuplas());
					view.printMessage("El tiempo de ejecución promedio del método put() por separate chaining es: "+ modelo.darTiempoEjecucionPromedio2());
					view.printMessage("El tiempo de ejecución promedio del método put() por linear proving es: "+ modelo.darTiempoEjecucionPromedio());
				}
				cargados = true;
				break;

			case 2:
				//REQ 1
				view.printMessage("Ingrese el numero de videos que desea consultar"); 
				while(numero == 0)
				{
					numero = lector.nextInt();
				}
				view.printMessage("Ingrese el país que desea consultar"); 
				while(pais.equals(""))
				{
					pais = lector.nextLine();
				}
				view.printMessage("Ingrese el nombre de la categoría que desea consultar");
				while(categoria.equals(""))
				{
					categoria = lector.nextLine();
				}

				ILista<Video> solucion = modelo.videoPorPaisLinear(numero, pais, categoria);
				
				if ( solucion == null) 
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					int i = 0;
					int j = 1;
					while(numero > i)
					{
						view.printMessage("----------------" + " \n Video " + j);
						view.printMessage("El título es: " + solucion.getElement(i).getTitle());
						view.printMessage("El número de views son: " + solucion.getElement(i).darVistas());
						view.printMessage("El número de likes son: " + solucion.getElement(i).darLikes());
						view.printMessage("El número de dislikes son: " + solucion.getElement(i).darDisLikes());
						i++;
						j++;
					}
				}
				numero = 0;
				pais = "";
				categoria = "";

				break;
			case 3:
				//int promedio = modelo.desempenioMetodoGet();
				view.printMessage("El tiempo promedio es: " + modelo.desempenioMetodoGetLlavesExistentes());

				break;

			case 4: 
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
