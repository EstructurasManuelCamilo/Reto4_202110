package model.logic;

public class Vader implements Comparable<Vader>
{
	private String hashtag;
	private double vaderPromedio;
	
	public Vader(String pHashtag, double pVaderPromedio)
	{ 
		hashtag = pHashtag;
		vaderPromedio = pVaderPromedio;
	}
	
	public String darHashtag()
	{
		return hashtag;
	}
	
	public double vaderPromedio()
	{
		return vaderPromedio;
	}

	@Override
	public int compareTo(Vader o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
