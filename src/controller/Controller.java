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
					view.printMessage("El total de duplas es: " + modelo.darDuplas());
					view.printMessage("El tiempo de ejecución promedio del método put() es: "+ modelo.darTiempoEjecucionPromedio());
				}
				cargados = true;
				break;

			case 2:
				//REQ 1
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
				if ( modelo.requerimiento1(pais, categoria)== null) 
					view.printMessage("No se pudo reportar respuesta al requerimiento");
				else
				{
					int i = 0;
					int j = 1;
					view.printMessage("El número de videos es: " + modelo.requerimiento1(pais, categoria).size());
					while(modelo.requerimiento1(pais, categoria).size() > i)
					{
						view.printMessage("----------------" + " \n Video " + j);
						view.printMessage("El título es: " + modelo.requerimiento1(pais, categoria).getElement(i).getTitle());
						view.printMessage("El número de views son: " + modelo.requerimiento1(pais, categoria).getElement(i).darVistas());
						view.printMessage("El número de likes son: " + modelo.requerimiento1(pais, categoria).getElement(i).darLikes());
						view.printMessage("El número de dislikes son: " + modelo.requerimiento1(pais, categoria).getElement(i).darDisLikes());
						i++;
						j++;
					}
				}

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
