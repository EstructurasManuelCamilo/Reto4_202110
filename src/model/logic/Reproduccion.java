package model.logic;

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
	}
	
	public double darDanceability()
	{
		return danceability;
	}

	@Override
	public int compareTo(Reproduccion o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}
