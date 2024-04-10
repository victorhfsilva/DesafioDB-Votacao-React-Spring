import CategoriaModel from "./CategoriaModel";

type CadastrarPautaRequisicaoModel =  {
    titulo: string;
    resumo: string;
    descricao: string;
    categoria: CategoriaModel;
}

export default CadastrarPautaRequisicaoModel;