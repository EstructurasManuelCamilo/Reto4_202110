package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar datos");
			System.out.println("2. Req 1. Caracterizar las reproducciones");
			System.out.println("3. Req 2. Encontrar música para festejar");
			System.out.println("4. Req 3. Encontrar música para estudiar/trabajar");
			System.out.println("5. Req 4. Estimar las reproducciones de los géneros musicales");
			System.out.println("6. Req 5. Indicar el género musical más escuchado en un tiempos");
			System.out.println("7. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			System.out.println(modelo.toString());
		}
		
}

