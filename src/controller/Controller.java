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
					modelo.leerHashtag();
					modelo.leerVader();
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
						view.printMessage("El número de artistas únicos es:" + (modelo.darArtistasDiferentes(solucion).size()-1));
					}
				}
				catch (Exception e1)
				{
					view.printMessage(e1.getMessage());
				}

				valorMin = 0.0;
				valorMax = 0.0;
				linea = "";
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
					view.printMessage("El total de pistas únicas es: " + (modelo.darRepDiferentes(solucion2).size()-1));

					for(int i = 1; i < 6; i++)
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
					view.printMessage("No se pudo encontro respuesta al requerimiento");
				else
				{
					view.printMessage("El total de pistas únicas es: " + (modelo.darRepDiferentes(solucion3).size()-1));
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
						numero = lector.nextInt();
					}
					view.printMessage("Ingrese el valor máximo del Tempo del nuevo género musical"); 
					while(num2 ==  0)
					{
						num2 = lector.nextInt();
					}
					modelo.asigarNuevoGenero(numero, num2, nuevoGeneroMusical);
					caracteristica = caracteristica + ", " + nuevoGeneroMusical;
				}
				String[] cortadito = caracteristica.split(", ");
				ArregloDinamico<NodoTS<Integer, ArregloDinamico<Reproduccion>>> solucion4 = modelo.darEstimarReproduccionesPorGenero(cortadito);
				if ( solucion4 == null) 
					view.printMessage("No se pudo encontrar respuesta al requerimiento");
				else
				{
					double suma = 0;
					for(int i = 0; i < solucion4.size(); i++)
					{
						view.printMessage("El Total de los eventos de escucha en el género: " + cortadito[i]  + " es: " + solucion4.getElement(i).getKey());
						suma += solucion4.getElement(i).getKey();
						view.printMessage("El número de artistas únicos es: " + (solucion4.getElement(i).getValue().size()-1));
						for(int j = 1; j < 11; j ++)
						{
							view.printMessage("El ID del artista: " + j + " es" + solucion4.getElement(i).getValue().getElement(j).darArtistId());
						}	
					}
						view.printMessage("El Total de los eventos de escucha de los géneros analizados es: " + suma);
					
				}
				numero = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0; 
				caracteristica = "";
				caracteristica2 = "";
				nuevoGeneroMusical = "";
				break;

			case 6:
				view.printMessage("Requerimiento 5.\n--------------------"); 
				view.printMessage("Ingrese el valor mínimo de la hora del día (ej. 10:00:00) "); 
				while(caracteristica.equals(""))
				{
					caracteristica = lector.nextLine();
				}
				String[] partes = caracteristica.split(":");
				String horas = partes[0];
				String minutos = partes[1];
				String segundos = partes[2];
				String union = horas + minutos + segundos;
				int resp = Integer.parseInt(union);

				view.printMessage("Ingrese el valor máximo de la hora del día (ej. 12:00:00)");
				while(caracteristica2.equals(""))
				{
					caracteristica2 = lector.nextLine();
				}
				String[] partes2 = caracteristica2.split(":");
				String horas2 = partes2[0];
				String minutos2 = partes2[1];
				String segundos2 = partes2[2];
				String union2 = horas2 + minutos2 + segundos2;
				int resp2 = Integer.parseInt(union2);


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
