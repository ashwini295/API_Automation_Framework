package com.thetestingacademy.asserts;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class AssertActions
{
    //common assertions which can reuse
    public void verifyResponseBody(String actual, String expected, String description)
    {
        assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual, int expected, String description)
    {
        assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response, Integer expected)
    {
        assertEquals(response.getStatusCode(),expected);
    }

    public void verifyStringKey(String keyExpect,String keyActual)
    {
        //AssretJ
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotNull().isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);
    }
}
