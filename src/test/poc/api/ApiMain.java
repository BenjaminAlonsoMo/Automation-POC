package challenge.api;

import challenge.api.factory.Person;
import challenge.api.factory.PersonFull;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class ApiMain {
    Person person = new Person();
    Person personUpdated = new Person();
    RequestSpecification httpRequest;


    @BeforeSuite
    public void prepareData() {
        person.setName("Benjamin Alonso");
        person.setEmail("benjamin.alonso_new@ultra2.com");
        person.setGender("male");
        person.setStatus("active");
        personUpdated.setName("Benjamin Alonso_Updated");
        personUpdated.setEmail("benjamin.alonso_Updated@ultra2.com");
        personUpdated.setGender("male");
        personUpdated.setStatus("active");
    }

    @BeforeMethod
    public void prepareRequest() {
        httpRequest = RestAssured.given()
                .auth().oauth2("547545a3d3fbf07100f8568dd6098b1bfcbcb3a079c00bbc7c4dd1b82e64525d")
                .header("Content-Type", "application/json")
                .baseUri("https://gorest.co.in/public/v2/users");
    }

    @Test
    public void getListUsers() {
        List<PersonFull> listPerson = httpRequest.given()
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", PersonFull.class);
        System.out.println(isTherePerson(listPerson, person));
    }

    @Test
    public void addUser() {
        String idPerson = httpRequest.given()
                .body(getJsonStringFromObject(person))
                .when()
                .post()
                .then()
                .extract()
                .body()
                .jsonPath()
                .get("id")
                .toString();

        httpRequest.given()
                .body(getJsonStringFromObject(personUpdated))
                .when()
                .put("/" + idPerson)
                .then()
                .statusCode(anyOf(is(200), is(204)));

        httpRequest.given()
                .when()
                .delete("/" + idPerson)
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    private boolean isTherePerson(List<PersonFull> listPerson, Person person) {
        boolean flag = false;
        for (PersonFull tempPerson : listPerson) {
            if (//person.getId().equals(tempPerson.getId()) &&
                    person.getName().equals(tempPerson.getName()) &&
                            person.getEmail().equals(tempPerson.getEmail()) &&
                            person.getGender().equals(tempPerson.getGender()) &&
                            person.getStatus().equals(tempPerson.getStatus())) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    private String getJsonStringFromObject(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
