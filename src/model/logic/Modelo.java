package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.data_structures.TablaHashLinearProbing;
import model.logic.YouTubeVideo.ComparadorXLikes;
import utils.ComparadorXDiasTendencia;
import utils.ComparadorXViews;

import utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<String> datos;
	
	private ListaEncadenada<YouTubeVideo> videos;
	
	private ArregloDinamico<YouTubeVideo> vidios;
	
//	private ArrayList<String> categorias;
	
	private ILista<YouTubeVideo> subLista;
	
	private ArrayList<Categoria> categorias;
	
	private TablaHashLinearProbing<String,String> linearVideos;
	
//	private Ordenamiento<YouTubeVideo> ordenamiento;
	
//	private Ordenamiento<YouTubeVideo> ordenamiento;
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int pCapacidad)
	{
		datos = new ArregloDinamico<String>(pCapacidad);
		videos = new ListaEncadenada<YouTubeVideo>();
		vidios = new ArregloDinamico<YouTubeVideo>(100);
//		categorias = new ArrayList<String>(100);
		categorias = new ArrayList<Categoria>(100);
		linearVideos = new TablaHashLinearProbing<>(1000000);
	}
	
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

//	public void cargarListaEnlazada()throws Exception
//	{
//		try
//		{
////			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
////			
////			FileReader filereader = new FileReader("./data/videos-all.csv");
////		     
////			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
////                     .withCSVParser(parser) 
////                     .build();
//			 
//			 csvReader.readNext();         
//			 String [] data;
//			 int contador =0;
//		     while ((data = csvReader.readNext()) != null) 
//		     {
//		       
//					
//					int k = 0; 
//					
//
//					String videoID = data[k];
//					k++;
//					
//					String trendingDate = data[k];
//					k++;
//					
//					String channelTitle = data[k];
//					k++;
//					
//					String categoryID = data[k];
//					k++;
//					
//					String publishTime = data[k];
//					k++;
//					
//					String tags = data[k];
//					k++;
//					
//					String views = data[k];
//					k++;
//					
//					String likes = data[k];
//					k++;
//					
//					String dislikes = data[k];
//					k++;
//					
//					String commentCount = data[k];
//					k++;
//					
//					String link =data[k];
//					k++;
//					
//					String commentsDisabled = data[k];
//					k++;
//					
//					String ratingsDisabled = data[k];
//					k++;
//					
//					String errorRemoved = data[k];
//					k++;
//					
//					String description =data[k];
//					k++;
//
//					String Country =data[k];
//					k++;
//					
//					
//					
//					
//					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate, channelTitle, categoryID, publishTime, 
//							tags, views, likes, dislikes, commentCount, link,
//							commentsDisabled, ratingsDisabled, errorRemoved, description, Country);
//					
//			        
//					videos.addLast(video);
//					contador++;
//					
//					System.out.println(contador);
//		    	 
//		     }
//		     System.out.println("Numero de datos leidos: " + contador);
//		
//		}     
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//	}
	public void cargarCategorias() throws FileNotFoundException
	{
		try
		{
			
		Reader in = new FileReader("./data/category-id.csv");
		 
//		CSVFormat format = CSVFormat.RFC4180.withDelimiter('\t');
		
		Iterable<CSVRecord> records = CSVFormat.TDF.withDelimiter('\t').withFirstRecordAsHeader().parse(in);
	

//		 int contador = 0;
		 
		 	for (CSVRecord record : records) 
		 	{
		 		String iD = record.get(0);
			 
		 		String name = record.get(1);
			 
			 
		 		Categoria nuevo = new Categoria(iD, name);
			 
			 
		 		categorias.add(nuevo);
			 
//		 		System.out.println(nuevo.getiD()+"-"+nuevo.getName());
			 
		 	}
		 System.out.println(categorias.size());
		}
		catch(Exception e)
		{
			
		}
		 
		 
	}
	public void cargarArregloDinamico() throws Exception
	{
		
		try
		{
//			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//			
//			FileReader filereader = new FileReader("./data/videos-all.csv");
//		     
//			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
//                     .withCSVParser(parser) 
//                     .build();
//			for(int i =0 ; i< categorias.size();i++)
//			 {
//				 System.out.println(categorias.darElemento(i));
//			 }
			
			
			 Reader in = new FileReader("./data/videos-all.csv");
			 
			 Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
			 
//			 String [] data = null;
			 int contador = 0;
			 
			 for (CSVRecord record : records) 
			 {
				 
//			 csvReader.readNext();         
			 
//		     while ((data = csvReader.readNext()) != null) 
//		     {
		       
					
//					int k = 0; 
					

					String videoID = record.get(0);
					
					
					String trendingDate = record.get(1);
					
					String title = record.get(2);
					
					String channelTitle = record.get(3);
					
					
					String categoryID = record.get(4);
					
					
					String publishTime = record.get(5);
					
					
					String tags = record.get(6);
					
					
					String views = record.get(7);
					
					
					String likes = record.get(8);
					
					
					String dislikes = record.get(9);
					
					
					String commentCount = record.get(10);
					
					
					String link = record.get(11);
					
					
					String commentsDisabled = record.get(12);
				
					
					String ratingsDisabled = record.get(13);
					
					
					String errorRemoved = record.get(14);
					
					
					String description = record.get(15);
					

					String country = record.get(16);
					
					
					
					
					
					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate,title, channelTitle, categoryID, publishTime, 
							tags, views, likes, dislikes, commentCount, link,
							commentsDisabled, ratingsDisabled, errorRemoved, description, country);
					
			        
					vidios.addLast(video);
					contador++;
//					linearVideos.keySet(video);
					
					String llave = country ;
					
					
					
					if(linearVideos.contains(llave) != true)
					{
						String nueroActualVideos = linearVideos.get(llave);
						linearVideos.put(llave, nueroActualVideos +1 ); 
						for  (int i=0;i< categorias.size();i++)
						{
							String iDActual = categorias.get(i).getiD();
							String categoryActual = categorias.get(i).getName();
							
							if(iDActual.equals(categoryID))
							{
								llave.concat(categoryActual);
								linearVideos.put(llave, categoryActual);
							}
						}  
					}
					else
					{		
						
						
					}
					
					
//					if(categorias.contains(categoryID))
//					{
//						
//					}
//					else if(contador >= 2) 
//					{
//						categorias.add(categoryID);
//					}
					
//					System.out.println(categoryID);
//					ordenamiento.ordenarQuickSort(vidios, vidios.darElemento(1).getCategoryID(), true);
					
//					if(contador==2)
//					{
//						System.out.println(country);
//						System.out.println("Primer Video :- \n" + "Titulo:" + title +"\n Titulo Canal:" +channelTitle +"\n fecha Trending:"+ trendingDate +"\n pais:"+ country +"\n # Vistas:"+ views +"\n # Likes:"+ likes +"\n # Dislikes:"+ dislikes);
//					}
						 
		     }
			 System.out.println(linearVideos.size());
//			 System.out.println("\nLista de categorias:- ");
//			 for (int i =0; i < categorias.size();i++)
//			 {
//				 System.out.println(categorias.get(i).getiD() + "-" + categorias.get(i).getName());
//			 }

			 
		     System.out.println("Numero de datos leidos: " + contador);
		     
		
		}     
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	
	public String esCategoria(String pNombreCategoria)
	{
		String respuesta = "";
		for(int i = 0; i < categorias.size(); i++)
		{
			String nombre = categorias.get(i).getName();
			if(nombre.equals(pNombreCategoria))
			{
				String iD = categorias.get(i).getiD();
				respuesta= iD;
			}

		}
		return respuesta;
	}
	
	//	public void darViews()
	//	{
	//		
	//	}
	
	
	/*
	 * REQ. 1 
	 * n videos con mas views que son tendencia en un determinado pais, dada una pCategoria
	 * especifica
	 */
	public void videosConMasViews(String pNombreCategoria, String pPais, int pNumero)
	{
		

		String pCategoria = esCategoria(pNombreCategoria);
		
		ArregloDinamico<YouTubeVideo> videosPorCategoria = new ArregloDinamico<>(100); 
		
		for (int i =0; i < vidios.darTamano(); i++)
		{
			String categoria = vidios.darElemento(i).getCategoryID();
			
			String pais = vidios.darElemento(i).getCountry();
			
			YouTubeVideo video = vidios.getElement(i);
			
			if (categoria.equals(pCategoria) && pais.equals(pPais))
			{		
				videosPorCategoria.addLast(video);
				
//				
			}
		}
		ordenarPorQuickSort((Comparator<YouTubeVideo>) videosPorCategoria, false);
		
		for(int i = 0; i < pNumero;i++)
		{
			YouTubeVideo videoCategory = videosPorCategoria.getElement(i);
			System.out.println(videoCategory.getTrendingDate()+videoCategory.getTitle()+videoCategory.getChannelTitle()
			+videoCategory.getPublishTime()+videoCategory.getViews()+videoCategory.getLikes()
			+videoCategory.getDislikes());
			
	
		}
		
		
	}
	
	
	
	
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public ILista<YouTubeVideo> muestraDadaListaEncadenada(int pNumero)
	{
		subLista = videos.subList( 1 , pNumero);
		
		if(subLista.size()> videos.size())
		{
			subLista = videos;
		}
		
//		System.out.println(subLista.size());
		
		return subLista;
		
	}
	public ILista<YouTubeVideo> muestraDadaArregloDinamico(int pNumero)
	{
		subLista = vidios.subList( 1 , pNumero);
		
		if(subLista.size()> vidios.size())
		{
			subLista = vidios;
		}
		
//		System.out.println(subLista.size());
		
		return subLista;
		
	}
	
	
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return (String) datos.buscar(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return (String) datos.eliminar(dato);
	}
	
	public void invertir()
	{
		 datos.invertir();
	}
	
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorSeleccion(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{	
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXViews = new ComparadorXViews();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarSeleccion(subLista, comparadorXViews, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorInsercion(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarInsercion(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorShellSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarShellSort(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorMergeSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarMergeSort(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorQuickSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		
		
		
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		Comparator<YouTubeVideo> comparadorXViews = new ComparadorXViews();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarSeleccion(subLista, comparadorXViews, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		System.out.println(elapsed_time);
	}
	public boolean buscarTag(YouTubeVideo video, String tag)
	{
		String[] tags = video.getTags().split("|");
		boolean cent = false;
		for (int i = 0; i < tags.length && !cent; i++) 
		{
			if (tags[i].equals(tag))
			{
				cent = true;
			}
		}
		return cent;
	}
	public ILista videosDelPaisTag(String pais, String tag)
	{
		ListaEncadenada<YouTubeVideo> videosPaisTag = new ListaEncadenada<>();
		for (int i = 1; i <= vidios.size() ; i++) 
		{
			if (vidios.getElement(i).getCountry().equals(pais))
			{
				if (buscarTag(vidios.getElement(i), tag))
				{
					videosPaisTag.addLast(vidios.getElement(i));
				}
			}
		}
		return videosPaisTag;		
	}
	public void videosConMasLikes(String pais, int numero, String tags)
	{
		ListaEncadenada<YouTubeVideo> videosDevolver = (ListaEncadenada<YouTubeVideo>) videosDelPaisTag(pais, tags);
		Ordenamiento ordenar = new Ordenamiento<>();
		ComparadorXLikes comparador = new ComparadorXLikes();
		ordenar.ordenarShellSort(videosDevolver, comparador, false);
		ListaEncadenada<YouTubeVideo> videosDeTodo = (ListaEncadenada<YouTubeVideo>) videosDevolver.subList(0, numero);
		for (int i = 1; i <= numero ;i++) 
		{
			YouTubeVideo videox = videosDeTodo.getElement(i);
			System.out.println("Titulo: " + videox.getTitle() + ", Canal: " + videox.getChannelTitle() 
								+ " Se publico en: " + videox.getPublishTime() + ", Tiene estas views: " +
								videox.getViews() + ", likes: " + videox.getLikes() + ", dislikes: "
								 + videox.getDislikes() + ", tags: " + videox.getTags());
		}
		
	}
	public ILista subListaPais(String pais)
	{
		ListaEncadenada<YouTubeVideo> listaPais = new ListaEncadenada<>();
		for (int i = 1; i <= vidios.size(); i++) 
		{
			if(vidios.getElement(i).getCountry().equals(pais))
			{
				listaPais.addLast(vidios.getElement(i));
			}
		}
		return listaPais;
	}
	public void videoConMasTrendingPais(String pais) throws ParseException
	{
		ListaEncadenada<YouTubeVideo> listaPais =new ListaEncadenada<>();
		listaPais = (ListaEncadenada<YouTubeVideo>) subListaPais(pais);
		ComparadorXDiasTendencia comparador = new ComparadorXDiasTendencia();
		Ordenamiento ordenar = new Ordenamiento<>();
		ordenar.ordenarShellSort(listaPais, comparador, false);
		YouTubeVideo video = listaPais.getElement(1);
		System.out.println(" titulo: " + video.getTitle() + " canal: " + video.getChannelTitle() +" pais: "
				+ video.getCountry() + " dias tendencia:" + video.diasEnTendencia());
	}
	public ILista subListaCategoria(String categoria)
	{
		ListaEncadenada<YouTubeVideo> listaCategoria = new ListaEncadenada<>();
		
		String pCategoria = esCategoria(categoria);
		
		for (int i = 1; i <= vidios.size(); i++) 
		{
			if(vidios.getElement(i).getCategoryID().equals(pCategoria))
			{
				listaCategoria.addLast(vidios.getElement(i));
			}
		}
		return listaCategoria;
	}
	public void videoConMasTrendingCategoria(String categoria) throws ParseException
	{
		ListaEncadenada<YouTubeVideo> listaCategoria =new ListaEncadenada<>();
		listaCategoria = (ListaEncadenada<YouTubeVideo>) subListaPais(categoria);
		ComparadorXDiasTendencia comparador = new ComparadorXDiasTendencia();
		Ordenamiento ordenar = new Ordenamiento<>();
		ordenar.ordenarShellSort(listaCategoria, comparador, false);
		YouTubeVideo video = listaCategoria.getElement(1);
		System.out.println(" titulo: " + video.getTitle() + " canal: " + video.getChannelTitle() +" category id: "
				+ video.getCategoryID() + " dias tendencia:" + video.diasEnTendencia());
	}
}
