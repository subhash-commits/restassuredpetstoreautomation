package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
    Faker faker;
    User userPayload;
    public String userName;
    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setUsername(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Initialize the logger
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Setup complete with user payload: " + userPayload);
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Starting testPostUser");
        logger.debug("Payload for creating user: " + userPayload);

        Response res = UserEndPoint.createUser(userPayload);
        logger.debug("Response: " + res.asString());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200, but got: " + res.getStatusCode());

        logger.info("User is created successfully");
    }

    @Test(priority = 2)
    public void testGetUser() {
        logger.info("Starting testGetUser");
        logger.debug("Username for reading user info: " + this.userPayload.getUsername());

        Response res = UserEndPoint.readUser(this.userPayload.getUsername());
        logger.debug("Response: " + res.asString());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200, but got: " + res.getStatusCode());

        logger.info("User info is displayed successfully");
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("Starting testUpdateUser");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        logger.debug("Updated payload: " + userPayload);

        Response res = UserEndPoint.updateUser(userPayload, this.userPayload.getUsername());
        logger.debug("Response after update: " + res.asString());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200, but got: " + res.getStatusCode());

        // Checking data after update
        Response resAfterUpdate = UserEndPoint.readUser(this.userPayload.getUsername());
        logger.debug("Response after reading updated user: " + resAfterUpdate.asString());
        resAfterUpdate.then().log().all();
        Assert.assertEquals(resAfterUpdate.getStatusCode(), 200, "Expected status code 200, but got: " + resAfterUpdate.getStatusCode());

        logger.info("User is updated successfully");
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("Starting testDeleteUser");
        logger.debug("Username for deleting user: " + this.userPayload.getUsername());

        Response res = UserEndPoint.deleteUser(this.userPayload.getUsername());
        logger.debug("Response: " + res.asString());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200, "Expected status code 200, but got: " + res.getStatusCode());

        logger.info("User is deleted successfully");
    }
}
