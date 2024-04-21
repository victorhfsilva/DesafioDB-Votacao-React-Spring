package fixture.usuario;

import org.example.domain.usuario.LoginRequisicao;

public interface LoginFixture {

    static LoginRequisicao builderValido() {
        return builder().build();
    }

    static LoginRequisicao builderCpfInexistente() {
        return builder().cpf("cpf inexistent").build();
    }

    static LoginRequisicao builderSenhaErrada() {
        return builder().senha("senha errada").build();
    }

    private static LoginRequisicao.LoginRequisicaoBuilder builder() {
        return LoginRequisicao.builder()
                .cpf("admin")
                .senha("admin");
    }

}
