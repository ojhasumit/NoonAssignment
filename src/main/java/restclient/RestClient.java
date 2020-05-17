package restclient;

import base.BaseClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import request.AuthRequest;

import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.given;


public class RestClient extends BaseClass {

    Response response;

    public Response getCall(String url){

        Response response = given().get(url);
            return response;
}



    public  Response postCall(String baseURI, AuthRequest requestBody) throws IOException {
        RequestSpecification request = RestAssured.given();

        ObjectMapper mapper = new ObjectMapper();

        request.header("Content-Type", "application/json");
        mapper.writeValue(new File("C:\\Users\\ril\\IdeaProjects\\NoonAssignment\\src\\main\\java\\request\\authrequest.json"),requestBody);
        Response response = request.body(requestBody).post(baseURI);
        return response;
    }
}
