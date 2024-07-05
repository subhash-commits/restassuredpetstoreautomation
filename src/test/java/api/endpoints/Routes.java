package api.endpoints;


/*
 Swagger URI : https://petstore.swagger.io
 Create USer(Post) -- https://petstore.swagger.io/user
Get User(get) -- https://petstore.swagger.io/user/{username}
Update User(put) -- https://petstore.swagger.io/user/{username}
Delete User(delete) -- https://petstore.swagger.io/user/{username}
*/
public class Routes {

	public static String base_url="https://petstore.swagger.io/v2/";
	
	//User module
	public static String post_url = base_url + "user";
	public static String get_url = base_url + "user/{username}";
	public static String update_url = base_url + "user/{username}";
	public static String delete_url = base_url + "user/{username}";
	
}
