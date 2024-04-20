package fixture.usuario;

import com.github.javafaker.Faker;
import org.example.domain.usuario.Usuario;
import org.example.util.GeradorCpf;

public interface UsuarioFixture {

    static Usuario builderValido() {
        return builder().build();
    }

    static Usuario builderNomeInvalido() {
        return builder().nome("").build();
    }

    static Usuario builderNomeNulo() {
        return builder().nome(null).build();
    }

    static Usuario builderSobrenomeInvalido() {
        return builder().sobrenome("").build();
    }

    static Usuario builderSobrenomeNulo() {
        return builder().sobrenome(null).build();
    }

    static Usuario builderCpfInvalido() {
        return builder().cpf("12345678901").build();
    }

    static Usuario builderCpfNulo() {
        return builder().cpf(null).build();
    }

    static Usuario builderEmailInvalido() {
        return builder().email("emailinvalido").build();
    }

    static Usuario builderEmailNulo() {
        return builder().email(null).build();
    }

    static Usuario builderSenhaFraca() {
        return builder().senha("senha").build();
    }

    static Usuario builderSenhaNula() {
        return builder().senha(null).build();
    }

    static Usuario builderPapelInvalido() {
        return builder().papel("PAPEL_INVALIDO").build();
    }

    static Usuario builderPapelNulo() {
        return builder().papel(null).build();
    }

    private static Usuario.UsuarioBuilder builder() {
        Faker faker = new Faker();

        return Usuario.builder()
                .nome(faker.name().firstName())
                .sobrenome(faker.name().lastName())
                .cpf(GeradorCpf.cpf())
                .email(faker.internet().emailAddress())
                .senha("Senha@123")
                .papel("USUARIO");
    }

}
