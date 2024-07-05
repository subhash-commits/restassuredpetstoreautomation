package api.test;

import com.github.javafaker.Faker;

import api.payload.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 2; i++) {
			Faker faker = new Faker();
			int id = faker.idNumber().hashCode();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String userName = faker.name().username();
			String emailaddress = faker.internet().safeEmailAddress();
			String password = faker.internet().password();
			String cellPhone = faker.phoneNumber().cellPhone();
			System.out.println("MainTest: "+id + "\n" + firstName + "\n" + lastName + "\n" + userName + "\n" + emailaddress + "\n"
					+ password + "\n" + cellPhone);
		}
	}

}
