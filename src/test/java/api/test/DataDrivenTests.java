package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	User userPayload;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class) // if dataprovider class is not
																						// in current class then we need
																						// to menion and import it
	public void test_postUsers(String userid, String firstName, String lastName, String Username, String emailAddress,
			String password, String ceelPhone) {
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setUsername(Username);
		userPayload.setEmail(emailAddress);
		userPayload.setPassword(password);
		userPayload.setPhone(ceelPhone);
		Response res = UserEndPoint.createUser(userPayload);
		System.out.println(res.then().log().all());
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void test_getUsers(String Username) {
		Response res = UserEndPoint.readUser(Username);
		System.out.println("Retrieved User is : " + Username);
		System.out.println(res.then().log().all());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 4, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void test_deleteUsers(String Username) {
		Response res = UserEndPoint.deleteUser(Username);
		System.out.println("Deleted User is : " + Username);
		System.out.println(res.then().log().all());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
