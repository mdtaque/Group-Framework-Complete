package OpenWeatherMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequestTest {

    public static String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

    @Test
    public void testResponseCode(){
        Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
    @Test
    public void testResponseBody(){
        Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        String body = response.asString();
        System.out.println("the body is " + body);
        long time = response.getTime();
        System.out.println("get response time " + time);
    }
    @Test
    public void testResponseCity(){
        JsonPath jsonPath = RestAssured.get(url).jsonPath();
        String city = jsonPath.get("name");
        Assert.assertEquals(city, "London");
    }
    @Test
    public void testResponseID(){
        JsonPath jsonPath = RestAssured.get(url).jsonPath();
        int id = jsonPath.get("id");
        Assert.assertEquals(id, 2643743);
    }
}