package domain.usuario;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.domain.usuario.Login;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/login";
    }

    @BeforeEach
    public void setRequest() {
        request = RestAssured.given().contentType("application/json");
    }

    @Test
    @DisplayName("Dado um cpf e senha válidos, quando o usuário tenta logar, então o sistema deve retornar status 200, o token e o papel do usuário.")
    void testFelizLogin() {
        Login login = Login.builder().cpf("admin").senha("admin").build();
        request.body(login)
                .when().post()
                .then().statusCode(200)
                .and().assertThat().body("token", org.hamcrest.Matchers.notNullValue())
                .and().assertThat().body("papel", org.hamcrest.Matchers.equalTo("ADMIN"));
    }
}