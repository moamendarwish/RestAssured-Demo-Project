package com.scrolltest.api.tests;

import com.scrolltest.api.framework.Person_APIS_Framework;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestUpdatePerson {
    private Person_APIS_Framework personAPISFramework;
    @BeforeClass
    public void init(){
        personAPISFramework = new Person_APIS_Framework();
    }
    @Parameters({"id", "firstName", "lastName", "age", "address"})
    @Test
    public void testUpdatePerson(Integer id, String firstName, String lastName, Integer age, String address){
        var response = personAPISFramework
                .updatePerson(id,firstName,lastName,age,address);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK,"updated");
        assertEquals(response.jsonPath().getString("firstname"),firstName,"error message");
    }
}
