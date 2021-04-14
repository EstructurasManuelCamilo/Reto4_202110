package model.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.data_structures.ArregloDinamico;

public class Video implements Comparable<Video>
{

	private String id;

	private Date trendingDate;

	private String title;

	private String channel;

	private int categoryId;

	private String categoria;

	private Date publishTime;

	private String fechaPublicacion;

	private String likes;

	private String disLikes;

	private String pais; // Agrego el pais

	private int vistas;

	private String etiqueta;

	private String listags;

	private ArregloDinamico<String> tags;
	
	private int diasTendencia;
	
	public Video(String pId, Date date, String pTitle, String pChannel, 
			int pCat, Date fecha2, String pFpub, String pListags, int pVistas, 
			String pLikes, String pDislikes, String pNomCat, String pPais)
	{
		id = pId;
		trendingDate = date;
		title = pTitle;
		channel = pChannel;
		categoryId = pCat;
		categoria = pNomCat;
		publishTime = fecha2;
		fechaPublicacion = pFpub;
		likes = pLikes;
		disLikes = pDislikes;
		pais = pPais;
		pais = pPais;
		vistas = pVistas;
		listags = pListags;
	}

	/** Comparación natural de acuerdo a algún atributo con identificación única
	 * @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro. 
	 * Numero positivo this es mayor a otro */
	public int compareTo(Video v) 
	{
		if (v.getId().equals(this.getId()) )
			return 0;
		else if (v.getId().compareTo(this.getId()) > 0)
			return -1;
		else
			return 1;
	}

	public int getDiasTendencia()
	{
		
		return diasTendencia;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public String darListags() 
	{
		return listags;
	}
	
	public Date getTrendingDate() 
	{
		return trendingDate;
	}

	public String getTitle() 
	{
		return title;
	}

	public String getChannel() 
	{
		return channel;
	}

	public int getCategoryId() 
	{
		return categoryId;
	}

	public Date getPublishTime() 
	{
		return publishTime;
	}

	public String darPublishTime()
	{
		return fechaPublicacion;
	}

	public String darNombreCategoria()
	{
		return categoria;
	}

	public String darLikes()
	{
		return likes;
	}

	public String darDisLikes()
	{
		return disLikes;
	}

	public String darPais()
	{
		return pais;
	}

	public int darVistas()
	{
		return vistas;
	}

	public boolean buscarEtiqueta(String pEt) 
	{
		boolean termino = false;
		for(int i = 0; i < darTags().size() && !termino; i++)
		{
			String act = darTags().getElement(i);
			if(act.equalsIgnoreCase(pEt))
			{
				etiqueta = act;
				termino = true;
			}
		}
		return termino;
	}

	public ArregloDinamico<String> darTags()
	{
		tags = new ArregloDinamico<>(20); 
		String[] partes = listags.split("\\|");
		
		for(int i = 0;i<Arrays.asList(partes).size(); i++)
		{
			tags.addLast(Arrays.asList(partes).get(i));
		}

		return tags;
	}
	
	public int darNumerDiasTrendig(Date fecha1, Date fecha2)
	{
		long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS) + 1;
	}
	
	
	public static class ComparadorXLikes implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			int lksV1 = Integer.parseInt(video1.darLikes());
			int lksV2 = Integer.parseInt(video2.darLikes());
			return lksV2 - lksV1;
		}
	}
	public static class ComparadorXVistas implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al número de vistas
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			
			int  pais1 = video1.darVistas();
			int pais2 = video2.darVistas();
			return pais2 - pais1;
		}	
	}
	
	public static class ComparadorXDia implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo a dias
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			
			int  pais1 = video1.darNumerDiasTrendig(video1.getPublishTime(), video1.getTrendingDate());
			int pais2 = video2.darNumerDiasTrendig(video2.getPublishTime(), video2.getTrendingDate());
			return pais2 - pais1;
		}	
	}

	public static class ComparadorXId implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al id
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			return video1.getId().compareTo(video2.getId());
		}	
	}
	public static class ComparadorXPais implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo al paiss
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			return video1.darPais().compareTo(video2.darPais());
		}	
	}
	
	public static class ComparadorXCategoria implements Comparator<Video> 
	{

		/** Comparador alterno de acuerdo a categoria
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
		 valor negativo si video1 tiene menos likes que video2.
		 valor positivo si video1 tiene más likes que video2. */
		public int compare(Video video1, Video video2) 
		{
			return video1.darNombreCategoria().compareTo(video2.darNombreCategoria());
		}	
	}

}
