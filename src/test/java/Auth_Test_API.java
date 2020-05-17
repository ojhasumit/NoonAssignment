import base.BaseClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import request.AuthRequest;
import response.InvalidMethodResponse;
import response.InvalidUserNameResponse;
import response.Oauthresponse;
import response.TransactionHistoryResponse;
import restclient.RestClient;

import java.io.IOException;

public class Auth_Test_API extends BaseClass{

    BaseClass base = new BaseClass();
    String baseURL;
    String uri;
    String service;
    Oauthresponse response;
    String token;
    RestClient client  = new RestClient();
    ObjectMapper mapper = new ObjectMapper();



    @BeforeMethod
    public void setUp() {
        baseURL = prop.getProperty("url");
        service = prop.getProperty("getService");
        uri = baseURL+service;
    }

        @Step
        @Test
        public void sucessfullValidationOfToken(ITestContext context) throws IOException {

            AuthRequest requestbody = new AuthRequest(prop.getProperty("email"),prop.getProperty("password"));
            Response response = client.postCall(prop.getProperty("url"),requestbody);

            //Assertion for status code
            Assert.assertEquals(response.getStatusCode(),Response_Status_Code_200);


            Oauthresponse oauthresponse = mapper.readValue(response.asString(), Oauthresponse.class);
            token = oauthresponse.getData().getToken();

            context.setAttribute("token",token);
            Assert.assertNotNull(token);

            String completeResource = prop.getProperty("geturl") + context.getAttribute("token");

            client.getCall(completeResource);
        }


        @Step
        @Description("Verification for Invslid Token")
        @Test(description = "Invalid Token")
        public void InvalidToken(ITestContext context) throws IOException {
        String baseuri = prop.getProperty("geturl");
        String completeResource = baseuri+context.getAttribute("token");

        Response response = client.getCall(completeResource);
        TransactionHistoryResponse historyResponse = mapper.readValue(response.asString(), TransactionHistoryResponse.class);

        //Assertion for invalid token message
        Assert.assertEquals(historyResponse.getErrorMessage(),Invalid_Token_Error_Message);


        Assert.assertFalse(historyResponse.getSuccess());
    }

    @Step
    @Description("Verification for Invslid User name")
    @Test
    public void invalidUserNameVerification() throws IOException {

        AuthRequest requestbody = new AuthRequest(prop.getProperty("invalidEmail"),prop.getProperty("password"));

        Response response = client.postCall(prop.getProperty("url"),requestbody);

        //Assertion for status code
        InvalidUserNameResponse invalidUserNameResponse = mapper.readValue(response.asString(),InvalidUserNameResponse.class);
        Assert.assertEquals(invalidUserNameResponse.getErrorMessage(),Invalid_UserName_Error_Message);
        Assert.assertEquals(invalidUserNameResponse.getData(),null);
        Assert.assertFalse(invalidUserNameResponse.getSuccess());
    }


    @Step
    @Description("Verification for Invalid Password")
    @Test
    public void invalidPasswordVerification() throws IOException {

        AuthRequest requestbody = new AuthRequest(prop.getProperty("email"),prop.getProperty("invalidpassword"));

        Response response = client.postCall(prop.getProperty("url"),requestbody);

        //Assertions
        InvalidUserNameResponse invalidPasswordResponse = mapper.readValue(response.asString(),InvalidUserNameResponse.class);
        Assert.assertEquals(invalidPasswordResponse.getErrorMessage(),Invalid_Password_Error_Message);
        Assert.assertEquals(invalidPasswordResponse.getData(),null);
        Assert.assertFalse(invalidPasswordResponse.getSuccess());
    }


    @Step
    @Description("Verification for Invalid method")
    @Test
    public void invalidMethodVerification() throws IOException {
        AuthRequest requestbody = new AuthRequest(prop.getProperty("email"),prop.getProperty("password"));
        Response response = client.getCall(prop.getProperty("url"));
        InvalidMethodResponse invalidMethodResponse = mapper.readValue(response.asString(),InvalidMethodResponse.class);
        Assert.assertEquals(invalidMethodResponse.getStatus(),Invalid_Method_Response_Status_Code);
        Assert.assertEquals(invalidMethodResponse.getMessage(),Invalid_Method_Message);
        Assert.assertEquals(invalidMethodResponse.getPath(),Invalid_Post_Path);

    }

    }
