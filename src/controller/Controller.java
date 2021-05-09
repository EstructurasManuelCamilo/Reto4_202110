package controller;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
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
		int valorMin = 0;
		int valorMax = 0; 
		int numero = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
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
					view.printMessage("El número total de reproducciones: " + modelo.darCantidadReproducciones());
					view.printMessage("El número de llaves presentes en el árbol RBT: " + modelo.darArbol().size());
					view.printMessage("La altura del árbol RBT: " + modelo.darArbol().darAlturaTotal());
					view.printMessage("La llave menor es: " + modelo.darArbol().min() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbol().get(modelo.darArbol().min()).size());
					view.printMessage("La llave mayor es: " + modelo.darArbol().max() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbol().get(modelo.darArbol().max()).size());
					view.printMessage("El número de hojas en el árbol RBT: " + modelo.darArbol().darNumeroHojas());
				}
				cargados = true;
				break;
			case 2: 
				view.printMessage("Requerimiento 1.\n--------------------"); 
				view.printMessage("Ingrese la característica de contenido que desea consultar"); 
				while(caracteristica.equals(""))
				{
					caracteristica = lector.nextLine();
				}
				view.printMessage("Ingrese el valor mínimo de la característica de contenido que desea consultar"); 
				while(numero == 0)
				{
					numero = lector.nextInt();
				}
				view.printMessage("Ingrese el valor máximo de la característica de contenido que desea consultar");
				while(num2 == 0)
				{
					num2 = lector.nextInt();
				}
				ArregloDinamico<Reproduccion> solucion = modelo.darReproduccionesPorCaracteristica(caracteristica, numero, num2);
				if ( solucion == null) 
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					view.printMessage("El Total de los eventos de escucha es: " + solucion.size());
					view.printMessage("El número de artistas únicos es:" + solucion);
				}
				numero = 0;
				num2 = 0;
				caracteristica = "";
				break;
			case 3:
				
				view.printMessage("Requerimiento 2.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de Energy que desea consultar"); 
				while(numero == 0)
				{
					numero = lector.nextInt();
				}
				view.printMessage("Ingrese el valor máximo de Energy que desea consultar");
				while(num2 == 0)
				{
					num2 = lector.nextInt();
				}
				view.printMessage("Ingrese el valor mínimo de Danceability que desea consultar"); 
				while(num3 == 0)
				{
					num3 = lector.nextInt();
				}view.printMessage("Ingrese el valor mínimo de Danceability que desea consultar"); 
				while(num4 == 0)
				{
					num4 = lector.nextInt();
				}
				ArregloDinamico<Reproduccion> solucion2 = modelo.darListaPorPistasFestejar(numero, num2, num3, num4);
				if ( solucion2 == null) 
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					view.printMessage("El total de pistas únicas es: " + solucion2.size());
					int aleatorio = 0;
					for(int i = 0; i < 6; i++)
					{
						aleatorio = ThreadLocalRandom.current().nextInt(0, solucion2.size());
						view.printMessage("El número de artistas únicos es:" + solucion2.size());
						view.printMessage("El danceability de la canción " + i + " selecionada al azar es: " + solucion2.getElement(aleatorio).darDanceability() + " y la energy es: " + solucion2.getElement(aleatorio).darEnergy());
					}
				}
				
				numero = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0; 
				caracteristica = "";
				break;
				
			case 4:
				view.printMessage("Requerimiento 3.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de Instrumentalness que desea consultar"); 
				while(numero == 0)
				{
					numero = lector.nextInt();
				}
				view.printMessage("Ingrese el valor máximo de Instrumentalness que desea consultar");
				while(num2 == 0)
				{
					num2 = lector.nextInt();
				}
				view.printMessage("Ingrese el valor mínimo de Tempo que desea consultar"); 
				while(num3 == 0)
				{
					num3 = lector.nextInt();
				}view.printMessage("Ingrese el valor mínimo de Tempo que desea consultar"); 
				while(num4 == 0)
				{
					num4 = lector.nextInt();
				}
				ArregloDinamico<Reproduccion> solucion3 = modelo.darListaPorPistasEstudiar(numero, num2, num3, num4);
				if ( solucion3 == null) 
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					view.printMessage("El total de pistas únicas es: " + solucion3.size());
					int aleatorio2 = 0;
					for(int i = 0; i < 6; i++)
					{
						aleatorio2 = ThreadLocalRandom.current().nextInt(0, solucion3.size());
						view.printMessage("El número de artistas únicos es:" + solucion3.size());
						view.printMessage("El danceability de la canción " + i + " selecionada al azar es: " + solucion3.getElement(aleatorio2).darDanceability() + " y la energy es: " + solucion3.getElement(aleatorio2).darEnergy());
					}
				}
				numero = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0; 
				caracteristica = "";
				break;
				
			case 5: 
				view.printMessage("Requerimiento 4.\n--------------------"); 
				view.printMessage("Ingrese la lista de géneros musicales que se desea buscar. (ej.: Reggae, Hip-hop, Pop.)."); 
				while(caracteristica.equals(""))
				{
					caracteristica = lector.nextLine();
				}
				view.printMessage("Si desea ingresar un nuevo género presione 'si' de lo contrario prisione 'no'");
				while(caracteristica2.equals(""))
				{
					caracteristica2 = lector.nextLine();
				}
				if(caracteristica2.equals("si"))
				{
					view.printMessage("Ingrese el nombre único para el nuevo género musical"); 
					while(nuevoGeneroMusical.equals(""))
					{
						nuevoGeneroMusical = lector.nextLine();
					}
					view.printMessage("Ingrese el valor mínimo del Tempo del nuevo género musical"); 
					while(numero == 0)
					{
						numero = lector.nextInt();
					}
					view.printMessage("Ingrese el valor máximo del Tempo del nuevo género musical"); 
					while(num2 ==  0)
					{
						num2 = lector.nextInt();
					}
				}
				ArregloDinamico<Reproduccion> solucion4 = modelo.darEstimarReproduccionesPorGenero(caracteristica, nuevoGeneroMusical, numero, num2);
				if ( solucion4 == null) 
					view.printMessage("No se pudo encontrar respuesta al requerimiento");
				else
				{
					view.printMessage("El Total de los eventos de escucha de los géneros analizados es: " + solucion4.size());
					for(int i = 0; i < solucion4.size(); i++)
					{
						// Falta cambiar tiene que ser lista de listas
						view.printMessage("El Total de los eventos de escucha en el género "  + " género es: ");
						view.printMessage("El número de artistas únicos es: " );
					}
				}
				numero = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0; 
				caracteristica = "";
				nuevoGeneroMusical = "";
				break;
				
			case 6:
				
				
			case 7: 
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
