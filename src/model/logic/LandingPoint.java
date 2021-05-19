package model.logic;

public class LandingPoint implements Comparable<LandingPoint>
{
	private String landing_point_id; 
	private String id;
	private String name;
	private String latitude;
	private String longitude;
	private String pais;

	public LandingPoint(String pLand, String pId, String pName, String pLat, String pLong, String pPais)
	{
		setId(pId);
		setLanding_point_id(pLand);
		setName(pName);
		setLatitude(pLat);
		setLongitude(pLong);
		setPais(pPais);
	}

	public String getLanding_point_id() {
		return landing_point_id;
	}

	public void setLanding_point_id(String landing_point_id) {
		this.landing_point_id = landing_point_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public int compareTo(LandingPoint o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
