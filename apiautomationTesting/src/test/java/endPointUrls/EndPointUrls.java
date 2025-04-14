	package endPointUrls;
	
	import utils.ConfigReader;
	
	public class EndPointUrls {
		public static final String Base_URL=ConfigReader.getProperty("base.url");
		 public static final String SIGNUP = Base_URL + "/signup";
		    public static final String LOGIN = Base_URL + "/login";
		    public static final String BOOKS = Base_URL + "/books/";
		    public static final String USERNAME=ConfigReader.getProperty("test.email");
		    public static final String PASSWORD=ConfigReader.getProperty("test.password");
	
	}
