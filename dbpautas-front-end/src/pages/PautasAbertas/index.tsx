import { Grid } from "@chakra-ui/react";
import PautaAberta from "./PautaAberta";
import CategoriaModel from "../../models/CategoriaModel";

const pauta = {
    id: 1,
    titulo: "Titulo 1",
    resumo: "Resumo 1",
    descricao: "Descricao 1",
    categoria: "FINANCAS" as CategoriaModel
}

const PautasAbertas = () => {
    return (
        <Grid templateColumns='repeat(3, 1fr)'>
            <PautaAberta pauta={pauta}/>
        </Grid>
    );
};

export default PautasAbertas;