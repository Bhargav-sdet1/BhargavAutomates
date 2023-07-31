package restAssuredRSA;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import allPojoClasses.Login;
import allPojoClasses.LoginResponse;
import allPojoClasses.OrderDetails;
import allPojoClasses.Orders;

public class EcommerceProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Login
		RequestSpecification req =new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		Login login = new Login();
		login.setUserEmail("nov9@yopmail.com");
		login.setUserPassword("Asdfg1@34");
		
		RequestSpecification loginReqest =given().log().all().spec(req).body(login);
		
		LoginResponse loginResponse=loginReqest.when().post("/api/ecom/auth/login")
		.then().log().all().extract().as(LoginResponse.class);
		
		System.out.println(loginResponse.getToken());
		String token =loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String UserId=loginResponse.getUserId();
		System.out.println(loginResponse.getMessage());
		
		//Create Product
		
		RequestSpecification createProductReq=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		
		RequestSpecification addProduct =given().log().all().spec(createProductReq)
		.param("productName", "Laptop")
		.param("productAddedBy", UserId)
		.param("productCategory", "Electronics")
		.param("productSubCategory", "Computer")
		.param("productPrice", "36000")
		.param("productDescription", "Om Vinayaka")
		.param("productFor", "All")
		.multiPart("productImage",new File ("C:\\Users\\bharg\\Downloads\\Vinayaka.jpg"));
		
		String AddedProduct=addProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().asString();
		
		JsonPath js = new JsonPath(AddedProduct);
		String productId=js.get("productId");
		System.out.println(productId);
	
		//Create Order
		
		RequestSpecification createOrderBaseReq= new RequestSpecBuilder().
		setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
		.setContentType(ContentType.JSON).build();
		
		OrderDetails orderDetails=new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productId);
		
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetails);
		
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orders);
		
		String responseAddOrd=createOrderReq.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().toString();
		System.out.println(responseAddOrd);
		
		//Delete Order
		
		RequestSpecification deleteBaseReq=new RequestSpecBuilder().addHeader("Authorization", token)
				.setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		RequestSpecification deleteReq=given().log().all()
				.spec(deleteBaseReq).pathParam("productId",productId);
		
		String DeleteMsg= deleteReq.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(DeleteMsg);
		String message=js1.get("message");
		System.out.println(message);
		Assert.assertEquals("Product Deleted Successfully", message);

		
	}

}
