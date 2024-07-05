package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//created to perform the create,read,update and delete the user api.
public class UserEndPoint2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
		return routes;
	}
	public static Response createUser(User payload){
		String posturl = getURL().getString("post_url");
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
		 	.post(posturl);
		
		return res;
	}
	
	public static Response readUser(String userName){
		String get_url = getURL().getString("get_url");
		Response res = given()
				.pathParam("username", userName)
		.when()
		 	.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(User payload,String userName){
		String update_url = getURL().getString("update_url");
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
		 	.put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName){
		String delete_url = getURL().getString("delete_url");
		Response res = given()
				.pathParam("username", userName)
		.when()
		 	.delete(delete_url);
		
		return res;
	}
}
