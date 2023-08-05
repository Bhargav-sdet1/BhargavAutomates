package resources;

import allPojoClasses.Login;

public class LoginPayload {
	Login login;
	public Login loginPayl()
	{
		login = new Login();
		login.setUserEmail("nov9@yopmail.com");
		login.setUserPassword("Asdfg1@34");
		
		return login;
	}

}
