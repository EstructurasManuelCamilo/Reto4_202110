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
			System.out.println("2. Conocer el número y videos que pertenecen a un país y nombre de categoría");
			System.out.println("3. Conocer el video con más días en trendig dado la pais");
			System.out.println("4. Conocer el video con más días en trendig dado el categoría");
			System.out.println("5. Conocer los n videos diferentes con más likes dado un tag");
			System.out.println("6. Exit");
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

