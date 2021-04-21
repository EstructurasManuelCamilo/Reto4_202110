package model.logic;

public class Reproduccion implements Comparable<Reproduccion>
{
	double danceability;
	
	public Reproduccion(double pDanceability) 
	{
		danceability = pDanceability;
	}
	
	public double darDanceability()
	{
		return danceability;
	}

	@Override
	public int compareTo(Reproduccion o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
