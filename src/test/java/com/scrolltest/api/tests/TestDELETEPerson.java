package com.scrolltest.api.tests;

import com.scrolltest.api.framework.Person_APIS_Framework;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestDELETEPerson {
    private Person_APIS_Framework personAPISFramework;
    @BeforeClass
    public void init(){
        personAPISFramework = new Person_APIS_Framework();
    }

    @Parameters({"id"})
    @Test
    public void testDeletePerson(Integer id){
        var response = personAPISFramework.deletePerson(id);
        assertEquals(response.getStatusCode()
                , HttpStatus.SC_OK,"some thing wrong");
    }

}
