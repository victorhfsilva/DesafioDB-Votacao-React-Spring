import CategoriaModel from "./CategoriaModel";

type PautaEmAndamentoRespostaModel = {
    id: number,
    titulo: string,
    resumo: string,
    descricao: string,
    categoria: CategoriaModel
}

export default PautaEmAndamentoRespostaModel;