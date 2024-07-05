package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;

public class PetEndPoint2 {
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
		return routes;
	}
	public static Response post_pet(Pet pet)
	{
		String post_pet_url = getURL().getString("pet_post_url");
		Response res = given()
		 .accept(ContentType.JSON)
		 .contentType(ContentType.JSON)
		 .body(pet)
		.when()
		 .post(post_pet_url);
		
		return res;
		 
	}
	
	public static Response get_pet(int petId)
	{
		String get_pet_url = getURL().getString("pet_get_url");
		Response res = given()
		 .accept(ContentType.JSON)
		 .pathParam("petId", petId)
		.when()
		 .get(get_pet_url);
		
		return res;
		 
	}
	
	public static Response put_pet(int petId,String formData)
	{
		String pet_put_url = getURL().getString("pet_put_url");
		Response res = given()
		 .accept(ContentType.JSON)
		 .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
		 .pathParam("petId", petId)
		 .body(formData)
		.when()
		 .post(pet_put_url);
		
		return res;
		 
	}
	
	public static Response delete_pet(int petId)
	{
		String delete_pet_url = getURL().getString("delete_post_url");
		Response res = given()
		 .accept(ContentType.JSON)
		 .pathParam("petId", petId)
		.when()
		 .delete(delete_pet_url);
		
		return res;
		 
	}
}
