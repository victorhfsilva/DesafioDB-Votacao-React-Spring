package fixture.pauta;

import com.github.javafaker.Faker;
import org.example.domain.pauta.PautaRequisicao;

public interface PautaFixture {

    static PautaRequisicao builderValido() {
        return builder().build();
    }

    static PautaRequisicao builderTituloInvalido() {
        return builder().titulo("").build();
    }

    static PautaRequisicao builderTituloNulo() {
        return builder().titulo(null).build();
    }

    static PautaRequisicao builderResumoInvalido() {
        return builder().resumo("").build();
    }

    static PautaRequisicao builderResumoNulo() {
        return builder().resumo(null).build();
    }

    static PautaRequisicao builderDescricaoInvalida() {
        return builder().descricao("").build();
    }

    static PautaRequisicao builderDescricaoNula() {
        return builder().descricao(null).build();
    }

    static PautaRequisicao builderCategoriaInvalida() {
        return builder().categoria("CATEGORIA_INVALIDA").build();
    }

    static PautaRequisicao builderCategoriaNula() {
        return builder().categoria(null).build();
    }

    private static PautaRequisicao.PautaRequisicaoBuilder builder() {
        Faker faker = new Faker();

        return PautaRequisicao.builder()
                .titulo(faker.lorem().sentence(5))
                .resumo(faker.lorem().sentence(20))
                .descricao(faker.lorem().sentence(60))
                .categoria("FINANCAS");
    }
}
