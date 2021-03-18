package controller;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Scanner;

import model.logic.Modelo;
import model.logic.YouTubeVideo;
import utils.Ordenamiento;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		
		modelo = new Modelo(999999999);
		
		
		
	}
		
	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String dato1="";
//		int dato2=0;
		String respuesta = "";
//		int answer=0;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
				case 1:
					view.printMessage("--------- \nCargando Categorias ");
					try {
						modelo.cargarCategorias(); 
//					    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");	
//						view.printMessage("datos cargados");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 					
					break;

				case 2:
//					view.printMessage("--------- \nCargar Arreglo Dinamico: ");
						
					try {
						view.printMessage("--------- \nCargando Tabla Hash... ");
						modelo.cargarArregloDinamico();
//						view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
//						view.printMessage("Arreglo Dinamico cargado");
//						view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 		
					break;

				case 3:
					view.printMessage("--------- \nDar categoria: ");
					dato = lector.next();
					view.printMessage("--------- \nDar pais: ");
					dato1 = lector.next();
					
					Scanner myInput = new Scanner( System.in );
//					view.printMessage("--------- \nDar numero de datos:\n---------");
					
					System.out.print( "Enter an integer: " );
					int a = myInput.nextInt();

					modelo.videosConMasViews(dato, dato1, a);
					
					break;

				case 4:
					view.printMessage("--------- \nReq2 ");
					
					dato = lector.next();
					
					modelo.videoConMasTrendingPais(dato);
											
					break;

				case 5: 
					view.printMessage("--------- \nReq3 ");
					
					dato = lector.next();
					
					modelo.videoConMasTrendingCategoria(dato);						
					break;	
					
				case 6: 
					view.printMessage("--------- \nReq4\n---------");
					
					view.printMessage("--------- \nDar pais: ");
					dato = lector.next();
					view.printMessage("--------- \nDar numero: ");
					Scanner myInput1 = new Scanner( System.in );
					int a1 = myInput1.nextInt();
					
					view.printMessage("--------- \nDar tag: ");
					dato1 = lector.next();
					modelo.videosConMasLikes(dato, a1 ,dato1 );
					lector.close();
					break;	
					
				case 7:
//					Scanner myInput = new Scanner( System.in );
					view.printMessage("--------- \nDar SubLista:\n---------");
					System.out.print( "Enter an integer: " );
//					int a1 = myInput.nextInt();
//					modelo.muestraDadaListaEncadenada(a);
//					modelo.muestraDadaArregloDinamico(a1);
//					System.out.println(answer);
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;
				
				case 8: 
					view.printMessage("--------- \nAplicar Selección :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorSeleccion(comparadorXLikes , true);
					lector.close();

					break;
					
				case 9: 
					view.printMessage("--------- \nAplicar Inserción :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorInsercion(comparadorXLikes , true);
					lector.close();

					break;
				case 10: 
					view.printMessage("--------- \nAplicar ShellSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorShellSort(comparadorXLikes , true);
					lector.close();

					break;
				case 11: 
					view.printMessage("--------- \nAplicar MergeSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorMergeSort(comparadorXLikes , true);
					lector.close();

					break;
				case 12: 
					view.printMessage("--------- \nAplicar QuickSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorQuickSort(comparadorXLikes , true);
					lector.close();

					break;
				case 13:
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
