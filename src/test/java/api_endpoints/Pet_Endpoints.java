package api_endpoints;

import api_Payloads.Pet.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Pet_Endpoints {

    //Here ı created methods for resuable and dont want to repeat some process
    //Burada bazı işlemleri tekrar etmemek için CRUD hazır methodlar oluşturdum
    public static Response createPet(Pet payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url_pet);
        return response;
    }

    public static Response readPet(int id){
        Response response = given()
                .pathParam("id",id)
                .when()
                .get(Routes.get_url_pet);
        return response;
    }

    public static Response updatePet(Pet payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.update_url_pet);
        return response;
    }

    public static Response deletePet(int id){
        Response response = given()
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url_pet);
        return response;
    }
}
