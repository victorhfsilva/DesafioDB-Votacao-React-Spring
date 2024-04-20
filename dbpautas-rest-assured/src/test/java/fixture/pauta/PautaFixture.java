package fixture.pauta;

import com.github.javafaker.Faker;
import org.example.domain.pauta.Pauta;

public interface PautaFixture {

    static Pauta builderValido() {
        return builder().build();
    }

    static Pauta builderTituloInvalido() {
        return builder().titulo("").build();
    }

    static Pauta builderTituloNulo() {
        return builder().titulo(null).build();
    }

    static Pauta builderResumoInvalido() {
        return builder().resumo("").build();
    }

    static Pauta builderResumoNulo() {
        return builder().resumo(null).build();
    }

    static Pauta builderDescricaoInvalida() {
        return builder().descricao("").build();
    }

    static Pauta builderDescricaoNula() {
        return builder().descricao(null).build();
    }

    static Pauta builderCategoriaInvalida() {
        return builder().categoria("CATEGORIA_INVALIDA").build();
    }

    static Pauta builderCategoriaNula() {
        return builder().categoria(null).build();
    }

    private static Pauta.PautaBuilder builder() {
        Faker faker = new Faker();

        return Pauta.builder()
                .titulo(faker.lorem().sentence(5))
                .resumo(faker.lorem().sentence(20))
                .descricao(faker.lorem().sentence(60))
                .categoria("FINANCAS");
    }
}
