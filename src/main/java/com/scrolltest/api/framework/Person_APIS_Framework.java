package com.scrolltest.api.framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scrolltest.api.endPoints.EndPoints;
import com.scrolltest.api.pojoClasses.model.Person;
import com.scrolltest.api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.lang.reflect.Type;
import java.util.List;


public class Person_APIS_Framework {
    //we need to read the config variables
    // provide RestAssured with URL,Port
    //make a get request on this URL and deserialization of pojo class
    // create a new person object using post request and serialization of pojo class
    private static final String baseUrl = ConfigManager.getInstance().getString("base_url");
    private static final String port = ConfigManager.getInstance().getString("port");

    /**
     * provide RestAssured with URL,Port in any instance of this class
     */
    public Person_APIS_Framework() {
        RestAssured.baseURI = baseUrl;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.useRelaxedHTTPSValidation();
    }

    /**
     * make a get request on this URL and deserialization of pojo class
     */
    public List<Person> getAllPersons() {
        Response response = getResponseOfGetAllPersons();
        Type type = new TypeReference<List<Person>>() {}.getType();
        List<Person> personList = response.as(type);
        return personList;
    }
    public Response getResponseOfGetAllPersons(){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .get(EndPoints.getAllPersons)
                .andReturn();
    }

    /**
     * create a new person object and serialization of pojo class
     */
    public Response createPerson(String firstName, String lastName, Integer age, Integer id, String address) {
        Person person = new Person();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        person.setAge(age);
        person.setId(id);
        person.setAddress(address);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(person)
                .when().post(EndPoints.createPerson)
                .andReturn();
        if(response.getStatusCode() == 201)
            System.out.println("new person created successfully");
        return response;
    }
    public Response updatePerson(int personIdToUpdate,String firstName,String lastName,int age,String address){
        Person person = new Person();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        person.setAge(age);
        person.setAddress(address);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParams("id",personIdToUpdate)
                .body(person)
                .when().patch(EndPoints.updatePerson)
                .andReturn();
        if (response.getStatusCode() == 200) {
            System.out.println("Person updated successfully,Here is the updated person");
            response.then().log().body();
        }
        return response;
    }
    public Response deletePerson(Integer id){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().delete(EndPoints.deletePerson)
                .andReturn();
        if(response.getStatusCode() == 200)
            System.out.println("Person deleted successfully");
        return response;

    } public Response getOnePerson(Integer id){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().get(EndPoints.getSinglePerson)
                .andReturn();
        if (response.getStatusCode() == 200) {
            System.out.println("Here is the person you want");
            response.then().log().body();
        }
        return response;

    }
}
