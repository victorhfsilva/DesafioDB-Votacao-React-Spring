package fixture.usuario;

import org.example.domain.usuario.LoginRequisicao;
import org.example.domain.usuario.UsuarioRequisicao;

public interface LoginFixture {

    static LoginRequisicao builderDoUsuario(UsuarioRequisicao usuario) {
        return builder().cpf(usuario.getCpf()).senha(usuario.getSenha()).build();
    }
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
