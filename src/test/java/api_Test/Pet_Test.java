package api_Test;

import api_Payloads.Pet.*;
import api_endpoints.Pet_Endpoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class Pet_Test {
    Faker faker = new Faker();
    Pet pet;
    public Logger logger;

    @BeforeClass
    public void setupData(){
        List<Pet_Tags> tags = new ArrayList<>(Collections.singletonList(new Pet_Tags(faker.idNumber().hashCode(), faker.name().name())));
        List<String> urls = new ArrayList<>();
        Collections.addAll(urls, "url1", "url2");

        pet = new Pet(faker.idNumber().hashCode(), faker.name().name(), new Pet_Category(faker.idNumber().hashCode(), faker.name().name()),
                urls, tags,"avaliable");

        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void postPets() throws JsonProcessingException {

        logger.info("************ Creating Pet **************");
        Response response = Pet_Endpoints.createPet(pet);
        response.then().log().all();

        ObjectMapper mapper = new ObjectMapper();

        Pet actualResponse = mapper.readValue(response.asString(), Pet.class);


        logger.info("************ Pet Created **************");

        Assert.assertEquals(pet.getId(), actualResponse.getId());
        Assert.assertEquals(pet.getName(), actualResponse.getName());
        Assert.assertEquals(pet.getTags().get(0).getId(), actualResponse.getTags().get(0).getId());
        Assert.assertEquals(pet.getTags().get(0).getName(), actualResponse.getTags().get(0).getName());
        Assert.assertEquals(pet.getCategory().getId(), actualResponse.getCategory().getId());
        Assert.assertEquals(pet.getCategory().getName(), actualResponse.getCategory().getName());
        Assert.assertEquals(pet.getPhotoUrls(), actualResponse.getPhotoUrls());
        Assert.assertEquals(pet.getStatus(), actualResponse.getStatus());
    }

    @Test(priority = 2)
    public void deletePets() throws JsonProcessingException {

        logger.info("************ Deleting Pet **************");
        Response response = Pet_Endpoints.deletePet(pet.getId());
        response.then().log().all();

        HashMap<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("code", 200);
        expectedResponse.put("type", "unknown");

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(response.asString(), HashMap.class);
        Assert.assertEquals(actualResponse.get("code"), expectedResponse.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedResponse.get("type"));
        Assert.assertTrue(actualResponse.containsKey("message"));
        logger.info("************ Pet Deleted **************");

    }

    //After we deleted pet we call the pet with same id and we get 404 status code and pet not found message

    @Test(priority = 3)
    public void getPet() throws JsonProcessingException {
        /*
        {
            "code": 1,
            "type": "error",
            "message": "Pet not found"
        }
        */

        logger.info("************ Pet Displayed **************");
        Response response = Pet_Endpoints.readPet(pet.getId());
        response.then().log().all();

        HashMap<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("code", 1);
        expectedResponse.put("type", "error");
        expectedResponse.put("message", "Pet not found");

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> actualResponse = mapper.readValue(response.asString(), HashMap.class);


        Assert.assertEquals(actualResponse.get("code"), expectedResponse.get("code"));
        Assert.assertEquals(actualResponse.get("type"), expectedResponse.get("type"));
        Assert.assertEquals(actualResponse.get("message"), expectedResponse.get("message"));
        Assert.assertTrue(response.statusCode() == 404);
    }
}
