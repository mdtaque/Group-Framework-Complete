package JSONServer;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostResource {

    @Test
    public void postTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 77777);
        jsonObject.put("title", "Doctor");
        jsonObject.put("author", "Fahim");
        request.body(jsonObject.toJSONString());
        Response response = request.post("http://localhost:3000/posts");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }
    @Test
    public void putTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 188);
        jsonObject.put("title", "MTA");
        jsonObject.put("author", "Uzzal");
        request.body(jsonObject.toJSONString());
        Response response = request.put("http://localhost:3000/posts/188");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test
    public void deleteTest(){
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("http://localhost:3000/posts/77777");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test
    public void putNullTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 000);
        jsonObject.put("title", "Engineer");
        jsonObject.put("author", "Rohan");
        request.body(jsonObject.toJSONString());
        Response response = request.put("http://localhost:3000/posts/000");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }
}