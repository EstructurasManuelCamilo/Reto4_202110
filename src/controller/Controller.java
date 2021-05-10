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
		Double valorMin = 0.0;
		Double valorMax = 0.0; 
		Double numero = 0.0;
		Double num2 = 0.0;
		Double num3 = 0.0;
		Double num4 = 0.0;

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
					view.printMessage("El número total de reproducciones: " + modelo.darCantidadReproducciones());
					view.printMessage("El número de llaves presentes en el árbol RBT: " + modelo.darArbolDance().size());
					view.printMessage("La altura del árbol RBT: " + modelo.darArbolDance().darAlturaTotal());
					view.printMessage("La llave menor es: " + modelo.darArbolDance().min() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbolDance().get(modelo.darArbolDance().min()).size());
					view.printMessage("La llave mayor es: " + modelo.darArbolDance().max() + " y el total de sus reproducciones en el árbol RBT es: " + modelo.darArbolDance().get(modelo.darArbolDance().max()).size());
					view.printMessage("El número de hojas en el árbol RBT: " + modelo.darArbolDance().darNumeroHojas());
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
				while(valorMin == 0)
				{
					valorMin = Double.parseDouble(lector.nextLine());
				}
				view.printMessage("Ingrese el valor máximo de la característica de contenido que desea consultar");
				while(valorMax == 0)
				{
					valorMax = Double.parseDouble(lector.nextLine());
				}
				ArregloDinamico<Reproduccion> solucion;
				try 
				{
					solucion = modelo.darReproduccionesPorCaracteristica(caracteristica, valorMin, valorMax);
					if ( solucion == null) 
						view.printMessage("No se pudo encontro respuesta al requerimiento");
					else
					{
						view.printMessage("El Total de los eventos de escucha es: " + solucion.size());
						view.printMessage("El número de artistas únicos es:" + modelo.darArtistasDiferentes(solucion).size());
					}
				}
				catch (Exception e1)
				{
					view.printMessage(e1.getMessage());
				}

				valorMin = 0.0;
				valorMax = 0.0;
				caracteristica = "";
				break;
			case 3:

				view.printMessage("Requerimiento 2.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de Energy que desea consultar"); 
				while(linea.equals(""))
				{
					linea = lector.nextLine();
				}
				numero = Double.parseDouble(linea);
				view.printMessage("Ingrese el valor máximo de Energy que desea consultar");
				while(num2 == 0)
				{
					num2 = Double.parseDouble(lector.nextLine());
				}
				view.printMessage("Ingrese el valor mínimo de Danceability que desea consultar"); 
				while(num3 == 0)
				{
					num3 = Double.parseDouble(lector.nextLine());
				}view.printMessage("Ingrese el valor máximo de Danceability que desea consultar"); 
				while(num4 == 0)
				{
					num4 = Double.parseDouble(lector.nextLine());
				}
				ArregloDinamico<Reproduccion> solucion2 = modelo.darListaPorPistasFestejar(numero, num2, num3, num4);
				if ( solucion2 == null) 
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					view.printMessage("El total de pistas únicas es: " + solucion2.size());

					for(int i = 0; i < 6; i++)
					{
						aleatorio = ThreadLocalRandom.current().nextInt(0, solucion2.size());
						view.printMessage("Id de reproduccion unica: " + i + " selecionada al azar es: "+ solucion2.getElement(aleatorio).darId());
						view.printMessage("El danceability de la reproduccion es: " + solucion2.getElement(aleatorio).darDanceability() + " y la energy es: " + solucion2.getElement(aleatorio).darEnergy());
					}
				}

				numero = 0.0;
				num2 = 0.0;
				num3 = 0.0;
				num4 = 0.0; 
				caracteristica = "";
				linea = "";
				aleatorio = 0;
				break;

			case 4:
				view.printMessage("Requerimiento 3.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de Instrumentalness que desea consultar"); 
				while(linea.equals(""))
				{
					linea = lector.nextLine();
				}
				numero = Double.parseDouble(linea);
				view.printMessage("Ingrese el valor máximo de Instrumentalness que desea consultar");
				while(num2 == 0)
				{
					num2 = Double.parseDouble(lector.nextLine());
				}
				view.printMessage("Ingrese el valor mínimo de Tempo que desea consultar"); 
				while(num3 == 0)
				{
					num3 = Double.parseDouble(lector.nextLine());
				}view.printMessage("Ingrese el valor máximo de Tempo que desea consultar"); 
				while(num4 == 0)
				{
					num4 = Double.parseDouble(lector.nextLine());
				}
				ArregloDinamico<Reproduccion> solucion3 = modelo.darListaPorPistasEstudiar(numero, num2, num3, num4);
				if ( solucion3 == null) 
					view.printMessage("No se pudo encontrar respuesta al requerimiento");
				else
				{
					view.printMessage("El total de pistas únicas es: " + solucion3.size());
					for(int i = 1; i < 6; i++)
					{
						aleatorio = ThreadLocalRandom.current().nextInt(0, solucion3.size());
						view.printMessage("Id de reproduccion unica: " + i + " selecionada al azar es: "+ solucion3.getElement(aleatorio).darId());
						view.printMessage("El valor de instrumentalidad de la reproduccion es: " + solucion3.getElement(aleatorio).darInstrumentalness() + " y el Tempo es: " + solucion3.getElement(aleatorio).darTempo());
					}
				}
				numero = 0.0;
				num2 = 0.0;
				num3 = 0.0;
				num4 = 0.0; 
				linea = "";
				aleatorio = 0;
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
						numero = Double.parseDouble(lector.nextLine());;
					}
					view.printMessage("Ingrese el valor máximo del Tempo del nuevo género musical"); 
					while(num2 ==  0)
					{
						num2 = Double.parseDouble(lector.nextLine());
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
				numero = 0.0;
				num2 = 0.0;
				num3 = 0.0;
				num4 = 0.0; 
				caracteristica = "";
				nuevoGeneroMusical = "";
				break;

			case 6:
				view.printMessage("Requerimiento 5.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de la hora del día (ej. 10:00 am) "); 
				while(numero == 0)
				{
					numero = Double.parseDouble(lector.nextLine());;
				}
				view.printMessage("Ingrese el valor máximo de la hora del día (ej. 10:00 pm)");
				while(num2 == 0)
				{
					num2 = Double.parseDouble(lector.nextLine());;
				}
				ArregloDinamico<Reproduccion> solucion5 = modelo.darEstimarReproduccionesPorGenero(caracteristica, nuevoGeneroMusical, numero, num2);
				if ( solucion5 == null) 
					view.printMessage("No se pudo encontrar respuesta al requerimiento");
				else
				{
					view.printMessage("El Total de los eventos de escucha de los géneros analizados es: " + solucion5.size());
					for(int i = 0; i < solucion5.size(); i++)
					{
						// Falta cambiar tiene que ser lista de listas
						view.printMessage("El Total de los eventos de escucha en el género "  + " género es: ");
						view.printMessage("El número de artistas únicos es: " );
					}
				}
				numero = 0.0;
				num2 = 0.0;
				num3 = 0.0;
				num4 = 0.0; 
				caracteristica = "";
				nuevoGeneroMusical = "";
				break;

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
