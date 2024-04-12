import CategoriaModel from "./CategoriaModel";
import DecisaoModel from "./DecisaoModel";

type PautaFinalizadaRespostaModel = {
    id: number;
    titulo: string;
    resumo: string;
    descricao: string;
    categoria: CategoriaModel;
    votosSim: number;
    votosNao: number;
    decisao: DecisaoModel;
}

export default PautaFinalizadaRespostaModel