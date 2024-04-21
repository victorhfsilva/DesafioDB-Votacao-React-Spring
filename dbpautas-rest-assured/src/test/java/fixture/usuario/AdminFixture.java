package fixture.usuario;

import com.github.javafaker.Faker;
import org.example.domain.usuario.UsuarioRequisicao;
import org.example.util.GeradorCpf;

public interface AdminFixture {

   static UsuarioRequisicao builderValido() {
        return builder().build();
    }

    static UsuarioRequisicao builderNomeInvalido() {
        return builder().nome("").build();
    }

    static UsuarioRequisicao builderNomeNulo() {
        return builder().nome(null).build();
    }

    static UsuarioRequisicao builderSobrenomeInvalido() {
        return builder().sobrenome("").build();
    }

    static UsuarioRequisicao builderSobrenomeNulo() {
        return builder().sobrenome(null).build();
    }

    static UsuarioRequisicao builderCpfInvalido() {
        return builder().cpf("12345678901").build();
    }

    static UsuarioRequisicao builderCpfNulo() {
        return builder().cpf(null).build();
    }

    static UsuarioRequisicao builderEmailInvalido() {
        return builder().email("emailinvalido").build();
    }

    static UsuarioRequisicao builderEmailNulo() {
        return builder().email(null).build();
    }

    static UsuarioRequisicao builderSenhaFraca() {
        return builder().senha("senha").build();
    }

    static UsuarioRequisicao builderSenhaNula() {
        return builder().senha(null).build();
    }

    static UsuarioRequisicao builderPapelInvalido() {
        return builder().papel("PAPEL_INVALIDO").build();
    }

    static UsuarioRequisicao builderPapelNulo() {
        return builder().papel(null).build();
    }

    private static UsuarioRequisicao.UsuarioRequisicaoBuilder builder() {
        Faker faker = new Faker();

        return UsuarioRequisicao.builder()
                .nome(faker.name().firstName())
                .sobrenome(faker.name().lastName())
                .cpf(GeradorCpf.cpf())
                .email(faker.internet().emailAddress())
                .senha("Senha@123")
                .papel("ADMIN");
    }

}
