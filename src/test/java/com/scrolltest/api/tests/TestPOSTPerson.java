package com.scrolltest.api.tests;

import com.scrolltest.api.framework.Person_APIS_Framework;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class TestPOSTPerson {

    private Person_APIS_Framework personAPISFramework;

    @BeforeClass
    public void init() {
        personAPISFramework = new Person_APIS_Framework();
    }

    @Parameters({"firstName", "lastName", "age", "id", "address"})
    @Test
    public void testCreatePerson(String firstName, String lastName, Integer age, Integer id, String address) {
        var response = personAPISFramework
                .createPerson(firstName, lastName, age, id, address);
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "error message");
        assertEquals(response.jsonPath().getInt("id"), id, "error message");
    }
}
