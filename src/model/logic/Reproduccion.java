package model.logic;

import model.data_structures.ArregloDinamico;

public class Reproduccion implements Comparable<Reproduccion>
{
	double danceability;
	double instrumentalness;
	double liveness;
	double speechiness;
	double valence;
	double loudness; 
	double tempo;
	double acousticness;
	double energy;
	double mode;
	double key;
	String id;
	String artist_id;
	String track_id;
	String user_id;
	String created_at;
	ArregloDinamico<String> generos;
	ArregloDinamico<Vader> vadersAverage;
	ArregloDinamico<Hashtag> hashTags;
	
	public Reproduccion(double pDanceability, double pinstrumentalness, double pliveness, double pspeechiness, double pvalence, double ploudness, double ptempo, double pacousticness, double penergy, double pmode, double pkey, String pid, String partist_id, String ptrack_id, String puser_id, String pcreated_at) 
	{
		danceability = pDanceability;
		instrumentalness = pinstrumentalness;
		liveness = pliveness;
		speechiness = pspeechiness;
		valence = pvalence;
		loudness = ploudness;
		tempo = ptempo;
		acousticness = pacousticness;
		energy = penergy;
		mode = pmode;
		key = pkey;
		id = pid;
		artist_id = partist_id;
		track_id = ptrack_id;
		user_id = puser_id;
		created_at = pcreated_at;
		hashTags = new ArregloDinamico<Hashtag>(7);
		vadersAverage = new ArregloDinamico<>(7);
		generos = new ArregloDinamico<>(7);
	}
//	public void insertarVaderAverage(double pNum)
//	{
//		vadersAverage.addLast(pNum);
//	}
	public void insertarGenero(String pGen)
	{
		generos.addLast(pGen);
	}
	public ArregloDinamico<Hashtag> darHashtag()
	{
		return hashTags;
	}
	public ArregloDinamico<Vader> darVaderAveg()
	{
		return vadersAverage;
	}
	public ArregloDinamico<String> darGeneros()
	{
		return generos;
	}
	public double darDanceability()
	{
		return danceability;
	}
	public double darInstrumentalness()
	{
		return instrumentalness;
	}
	public double darLiveness()
	{
		return liveness;
	}
	public double darSpeechiness() 
	{
		return speechiness;
	}
	public double darValence()
	{
		return valence;
	}
	public double darLoudness()
	{
		return loudness;
	}
	public double darTempo()
	{
		return tempo;
	}
	public double darAcousticness()
	{
		return acousticness;
	}
	public double darEnergy()
	{
		return energy;
	}
	public double darMode()
	{
		return mode;
	}
	public double darKey()
	{
		return key;
	}
	public String darId()
	{
		return id;
	}
	public String darArtistId()
	{
		return artist_id;
	}
	public String darUserId()
	{
		return user_id;
	}
	public String darCreatedAt()
	{
		return created_at;
	}
	@Override
	public int compareTo(Reproduccion o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void asignarHashtag(ArregloDinamico<Hashtag> pHashtags) 
	{
		for(int i = 0; pHashtags.size() > i; i ++)
		{
			if (pHashtags.getElement(i).darUserId().equals(user_id))
			{
				hashTags.addLast(pHashtags.getElement(i));
			}
		}
	}
	public void asignarVader(ArregloDinamico<Vader> pVaders) 
	{
		for(int i = 0; pVaders.size() > i; i ++)
		{
			for(int j = 0; hashTags.size() > j; j ++)
			{
				if (pVaders.getElement(i).darHashtag().equals(hashTags.getElement(j)))
				{
					vadersAverage.addLast(pVaders.getElement(i));
				}
			}	
		}
	}
}
