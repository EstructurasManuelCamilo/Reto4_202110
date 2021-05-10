package model.logic;

import java.util.Date;

public class Hashtag implements Comparable<Hashtag>
{
	
	private String user_id;
	private String track_id;
	private String hashtag;
	private Date created_at;
	public Hashtag(String pUserId, String pTrackId, String pHashtag, Date pCreatedat)
	{
		user_id = pUserId;
		track_id = pTrackId;
		hashtag = pHashtag;
		created_at = pCreatedat;
	}
	
	public String darUserId()
	{
		return user_id;
	}
	public String darTrackid()
	{
		return user_id;
	}
	public String darHashtag()
	{
		return hashtag;
	}
	public Date darCreatedAt()
	{
		return created_at;
	}

	@Override
	public int compareTo(Hashtag o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
