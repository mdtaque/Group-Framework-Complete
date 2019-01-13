package EmployeeServices;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class AllEmployeeRestServices {

    @Test
    public void callAllEmployeeResources() {
        Response response = given().when().get("http://info.venturepulse.org:8080/service-webapp/api/AllEmployeeResources").then().statusCode(200).extract().response();
        String statusLine = response.getStatusLine();
        int statuscode = response.getStatusCode();
        String body = response.getBody().print();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        Assert.assertEquals(statuscode, 200);
    }
    @Test
    public void badCallAllEmployeeResources() {
        Response response = null;
        int statuscode = 0;
        String statusLine = "";
        try {
            response = given().when().get("http://info.venturepulse.org:8080/service-webapp/api/AllEmployeeResources76834");
            statuscode = response.getStatusCode();
            statusLine = response.getStatusLine();
        } catch (Exception ex) {
        }
        Assert.assertEquals(statusLine, "HTTP/1.1 404 Not Found");
        Assert.assertEquals(statuscode, 404);
    }
    @Test
    public void serverErrorCallAllEmployeeResources() {
        Response response = null;
        int statuscode = 0;
        String statusLine = null;
        try {
            response = given().when().get("http://info.venturepulse.org:8080/service-webapp/api/AllEmployeeResources").then().extract().response();
            statuscode = response.getStatusCode();
            //String body = response.getBody().prettyPrint();
            statusLine = response.getStatusLine();
        } catch (Exception ex) {
        }
        Assert.assertEquals(statuscode, 200);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
}
