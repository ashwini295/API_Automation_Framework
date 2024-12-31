package com.thetestingacademy.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestIntegrationSample
{
    //Create a Booing, Create a Token
    //Get Booking
    //Update the Booking
    //Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Ashwini")
    @Description("TC#INT1- Step1. Verify that the Booking can be Created")
    public void testCreateBooking()
    {
      Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Ashwini")
    @Description("TC#INT1- Step2. Verify the booking by booking id")
    public void testVerifyGetBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Ashwini")
    @Description("TC#INT1- Step3. Verify Update Booking by ID")
    public void testPatchBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Ashwini")
    @Description("TC#INT1- Step4. Verify Delete Booking by ID")
    public void testDeleteBooking()
    {
        Assert.assertTrue(true);
    }

}
