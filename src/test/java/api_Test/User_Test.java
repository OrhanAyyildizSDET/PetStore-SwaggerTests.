package api_Test;

import api_endpoints.User_Endpoints;
import api_Payloads.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class User_Test {

    Faker faker = new Faker();
    User user;
    public Logger logger;

    @BeforeClass
    public void setupData(){
        //Faker class'ı ile daha dinamic bir veri elde edebilirim. Her defasında farklı bir veri test etmiş olurum.
        user = new User(faker.idNumber().hashCode(),
                faker.name().username(),
                faker.name().firstName(),
                faker.address().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().cellPhone(),
                faker.number().randomDigit());

        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1) //Testde öncelik vermek istediğim methodalara priortiy atayarak ayarladım.
    public void postUser() throws JsonProcessingException {
        logger.info("************ Creating User **************");

        //Here we instantiate the response the response interface json
        //Burada restassured.response interface gelen cevabı atıyoruz.;

        Response response = User_Endpoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        //Here we created our expected body with hashmap
        //Burada response ile expected karşılaştırmak için kendi hasmap objemizi oluşturuyoruz.
        HashMap<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("code", 200);
        expectedBody.put("type", "unknown");

        ObjectMapper mapper = new ObjectMapper();
        //Here we use the object mapper for transform our response to any class(Pojo,Hashmap etc...)
        //Burada yanıtımızı herhangi bir sınıfa (Pojo, Hashmap vb...) dönüştürmek için object mapper kullanıyoruz
        HashMap<String, Object> actualResponse = mapper.readValue(response.asString(), HashMap.class);

        //TestNg assert classından methodlarla kontrol ediyoruz
        //We check with methods from the TestNg assert class
        Assert.assertEquals(actualResponse.get("code"), expectedBody.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedBody.get("type"));
        Assert.assertTrue(actualResponse.containsKey("message"));
        logger.info("************ User Created **************");

    }

    @Test(priority = 2)
    public void getUser() throws JsonProcessingException {
        logger.info("************ Getting User **************");

        Response response = User_Endpoints.readUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(response.asString(), HashMap.class);


        Assert.assertEquals(actualResponse.get("username"), user.getUsername());
        Assert.assertEquals(actualResponse.get("firstName"), user.getFirstName());
        Assert.assertEquals(actualResponse.get("lastName"), user.getLastName());
        Assert.assertEquals(actualResponse.get("email"), user.getEmail());
        Assert.assertEquals(actualResponse.get("password"), user.getPassword());
        Assert.assertEquals(actualResponse.get("phone"), user.getPhone());
        Assert.assertEquals(actualResponse.get("userStatus"), user.getUserStatus());

        logger.info("************ User is Displayed **************");

    }

    @Test(priority = 3)
    public void updateUser() throws JsonProcessingException {
        logger.info("************ Updating User **************");

        user.setLastName(faker.name().lastName());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setEmail(faker.internet().emailAddress());

        Response response = User_Endpoints.updateUser(user, user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate = User_Endpoints.readUser(user.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        logger.info("************ User is Updated **************");

        HashMap<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("code", 200);
        expectedBody.put("type", "unknown");

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(response.getBody().asString(), HashMap.class);

        Assert.assertEquals(actualResponse.get("code"), expectedBody.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedBody.get("type"));
        Assert.assertTrue(actualResponse.containsKey("message"));
    }

    @Test(priority = 4)
    public void deleteUser() throws JsonProcessingException {
        logger.info("************ Deleting User **************");

        Response response = User_Endpoints.deleteUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);


        HashMap<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("code", 200);
        expectedBody.put("type", "unknown");

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(response.asString(), HashMap.class);


        Assert.assertEquals(actualResponse.get("code"), expectedBody.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedBody.get("type"));
        Assert.assertTrue(actualResponse.containsKey("message"));

        logger.info("************ User is Deleted **************");

    }

    //Here we try to get deleted user and we expect 404 status code
    //Aşagıda silinmiş bir user'i getirmeye çalışıp 404 hata kodunu assert ediyrouz gelen mesajla birlikte.

    @Test(priority = 5)
    public void getNonExistUser() throws JsonProcessingException {
        logger.info("************ Get User **************");


        Response afterDelete = User_Endpoints.readUser(user.getUsername());
        Assert.assertEquals(afterDelete.getStatusCode(), 404);
        afterDelete.body().prettyPrint();

        HashMap<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("code", 1);
        expectedBody.put("type", "error");
        expectedBody.put("message", "User not found");

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(afterDelete.asString(), HashMap.class);


        Assert.assertEquals(actualResponse.get("code"), expectedBody.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedBody.get("type"));
        Assert.assertEquals(actualResponse.get("message"), expectedBody.get("message"));
        Assert.assertEquals(afterDelete.getStatusCode(), 404);
    }

}
