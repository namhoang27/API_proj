package Resource;
//enum is special class in java which has collection of constants or  methods

public enum APIResources {

	
	CreateToken("/auth"),
	CreateBooking("/booking"),
	GetBooking("/booking/");



	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;

	}
	
	public String getResource()
	{
		return resource;
	}
	

}
