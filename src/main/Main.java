package main;
import controller.Controller;
import model.data_structures.RedBlackTree;

public class Main {

	public static void main(String[] args) 
	{

		//		RedBlackTree<Integer, String> arbol = new RedBlackTree<>();
		//		arbol.put(62, "62");
		//		arbol.put(58, "58");
		//		arbol.put(33, "33");
		//		arbol.put(86, "86");
		//		arbol.put(56, "56");
		//		arbol.put(85, "85");
		//		arbol.put(8, "8");

		Controller controler = new Controller();
		controler.run();
	}
}
