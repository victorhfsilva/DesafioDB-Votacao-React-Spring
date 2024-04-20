package fixture.usuario;

import org.example.domain.usuario.Login;

public interface LoginFixture {

    static Login builderValido() {
        return builder().build();
    }

    static Login builderCpfInexistente() {
        return builder().cpf("cpf inexistent").build();
    }

    static Login builderSenhaErrada() {
        return builder().senha("senha errada").build();
    }

    private static Login.LoginBuilder builder() {
        return Login.builder()
                .cpf("admin")
                .senha("admin");
    }

}
