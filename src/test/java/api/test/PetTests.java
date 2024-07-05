package api.test;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoint2;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import io.restassured.response.Response;

public class PetTests {

	Faker faker;
	Pet pet;
	Category category;
	Tag tag;

	@BeforeClass
	public void setup() {
		tag = new Tag();
		tag.setId(1);
		tag.setName("cute");

		List<Tag> tags = new ArrayList<>();
		tags.add(tag);

		faker = new Faker();
		pet = new Pet();
		category = new Category();
		pet.setId(faker.idNumber().hashCode());
		category.setId(5);
		category.setName(faker.dog().breed());
		pet.setCategory(category);
		pet.setTags(tags);
		pet.setStatus("available");

	}

	@Test(priority = 1)
	public void test_pet_post_url() {

		Response res = PetEndPoint2.post_pet(pet);
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(res.then().log().body());
		JSONObject jo = new JSONObject(res.asString());
		System.out.println(jo.get("tags"));
		JSONArray joarr = new JSONArray(jo.getJSONArray("tags"));
		System.out.println(joarr.length());
		for (int i = 0; i < joarr.length(); i++) {
			JSONObject jsontagobj = joarr.getJSONObject(i);
			System.out.println("Actual : " + jsontagobj.get("id") + " , Expected : " + this.tag.getId());
			System.out.println("Actual : " + jsontagobj.get("name") + " , Expected : " + this.tag.getName());
			Assert.assertEquals(jsontagobj.get("id"), this.tag.getId());
			Assert.assertEquals(jsontagobj.get("name"), this.tag.getName());
		}
	}

	@Test(priority = 2)
	public void test_pet_get_url() {

		Response res = PetEndPoint2.get_pet(this.pet.getId());
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(res.then().log().all());
		JSONObject jo = new JSONObject(res.asString());
		System.out.println("Actual : " + jo.getInt("id") + " , Expected : " + this.pet.getId());
		Assert.assertEquals(jo.getInt("id"), this.pet.getId());
	}

	@Test(priority = 3)
	public void test_pet_put_url() {

		String formData = "name=Romeo&status=pending";
		Response res = PetEndPoint2.put_pet(this.pet.getId(), formData);
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(res.then().log().all());
		res = PetEndPoint2.get_pet(this.pet.getId());
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(res.then().log().all());
		validateGetResponse(res);
	}
	
	@Test(priority = 4)
	public void test_pet_delete_url() {
		Response res = PetEndPoint2.delete_pet(this.pet.getId());
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(res.then().log().all());
	}

	public void validateGetResponse(Response res) {
		JSONObject jo = new JSONObject(res.asString());
		System.out.println("Actual : " + jo.getInt("id") + " , Expected : " + this.pet.getId());
		System.out.println("Actual : " + jo.get("name") + " , Expected : " + "Romeo");
		System.out.println("Actual : " + jo.get("status") + " , Expected : " + "pending");
		Assert.assertEquals(jo.getInt("id"), this.pet.getId());
		Assert.assertEquals(jo.get("name"), "Romeo");
		Assert.assertEquals(jo.get("status"), "pending");
	}
}
