package com.scrolltest.api.tests;

import com.scrolltest.api.framework.Person_APIS_Framework;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestGETPerson {
    private Person_APIS_Framework personAPISFramework;

    @BeforeClass
    public void init() {
        personAPISFramework = new Person_APIS_Framework();
    }

    //    @Test
//    public void testGetAllPerson(){
//        var personsList = personServiceHelper.getAllPersons();
//        personServiceHelper.getResponseOfGetAllPersons().then().log().all();
//        assertEquals(personServiceHelper.getResponseOfGetAllPersons().getStatusCode()
//                , HttpStatus.SC_OK,"some thing wrong");
//        assertNotNull(personsList,"persons list is not empty");
//    }
    @Parameters({"id"})
    @Test
    public void testGetOnePerson(Integer id) {
        var response = personAPISFramework.getOnePerson(id);

        assertEquals(response.getStatusCode()
                , HttpStatus.SC_OK, "some thing wrong");
    }

}
