package pt.psoft.g1.psoftg1.authormanagement.AT;

import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.authormanagement.services.CreateAuthorRequest;

import static io.restassured.RestAssured.given;

public class AuthorAT {

    private final String baseUrl = "http://localhost:2228/api/authors";
    private final String bearerToken = authenticate("maria@gmail.com", "Mariaroberta!123");


    private String authenticate( String username, String password) {
        // Arrange
        // Act
        // Assert
        Response response = given()
                .log().all()
                .contentType("application/json").body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                .when()
                .post("http://localhost:2228/api/public/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        return response.getHeader("Authorization");
    }

    @Test
    public void shouldReturn201WhenGivenValidAuthor() {
        // Arrange
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Mia Couto", "Mia Couto, pseudónimo" +
                " de António Emílio Leite Couto, é um escritor e biólogo moçambicano",null, null);
        // Act + Assert

        given()
                .log().all()
                .contentType("multipart/form-data")
                .header("Authorization","Bearer "+ bearerToken.trim())
                .multiPart("name", createAuthorRequest.getName())
                .multiPart("bio", createAuthorRequest.getBio())
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void shouldReturn200WhenGivenValidAuthorId() throws JSONException {
        // Arrange
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Mia Couto", "Mia Couto, pseudónimo" +
                " de António Emílio Leite Couto, é um escritor e biólogo moçambicano",null, null);
        Response response = given()
                .log().all()
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + bearerToken.trim())
                .multiPart("name", createAuthorRequest.getName())
                .multiPart("bio", createAuthorRequest.getBio())
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(201).extract().response();

        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        // Act + Assert
        given()
                .log().all()
                .header("Authorization","Bearer "+ bearerToken.trim())
                .when()
                .get(String.format("%s/%s",baseUrl, jsonObject.get("authorNumber")))
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void shouldReturn404WhenGivenInvalidAuthorId() {
        // Arrange
        // Act + Assert
        given()
                .log().all()
                .header("Authorization","Bearer "+ bearerToken.trim())
                .when()
                .get(String.format("%s/%s",baseUrl, "123456789"))
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void shouldReturn401WhenUserIsNotAuthenticated() {
        // Arrange
        // Act + Assert

        // Arrange
        // Arrange
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Mia Couto", "Mia Couto, pseudónimo" +
                " de António Emílio Leite Couto, é um escritor e biólogo moçambicano",null, null);
        // Act + Assert

        given()
                .log().all()
                .contentType("multipart/form-data")
                .multiPart("name", createAuthorRequest.getName())
                .multiPart("bio", createAuthorRequest.getBio())
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void shouldReturn401WhenUserIsAuthenticatedButDoesNotHaveEnoughPrivileges() {

        // Arrange
        // Arrange
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Mia Couto", "Mia Couto, pseudónimo" +
                " de António Emílio Leite Couto, é um escritor e biólogo moçambicano",null, null);
        // Act + Assert

        given()
                .log().all()
                .header("Authorization","Bearer "+ authenticate("manuel@gmail.com", "Manuelino123!"))
                .contentType("multipart/form-data")
                .multiPart("name", createAuthorRequest.getName())
                .multiPart("bio", createAuthorRequest.getBio())
                .when()
                .post(baseUrl)
                .then()
                .log().all()
                .statusCode(403);
    }
}
