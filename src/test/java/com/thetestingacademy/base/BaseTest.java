package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    //common to all test cases
    //Base URL, Content Type -json-common

    public RequestSpecification rs;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse vr;

    @BeforeTest
    public void setUp() {
        //BASE URL, Content Type JSON
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        //this is one way
        rs = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON)
                .log()
                .all();

        //this is other way
       /* rs=new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();*/
    }

        public String getToken () {
            rs = RestAssured
                    .given()
                    .baseUri(APIConstants.BASE_URL)
                    .basePath(APIConstants.AUTH_URL);

            //Setting payload
            String payoad = payloadManager.setAuthPayload();

            //Get token
            response = rs.contentType(ContentType.JSON).body(payoad).when().post();

            //String Extraction
            String token = payloadManager.getTokenFromJSON(response.toString());

            return token;

        }

    }

