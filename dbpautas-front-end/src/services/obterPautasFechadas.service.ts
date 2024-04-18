import api from "../libs/api";
import CategoriaModel from "../models/CategoriaModel";
import PautaEmAndamentoRespostaModel from "../models/PautaEmAndamentoRespostaModel";

const obterPautasFechadasService = async (categoria: CategoriaModel | "") => {
    return api.get('/pauta/fechada/'+categoria).then(response => {
            return response.data as PautaEmAndamentoRespostaModel[];
        });
}

export default obterPautasFechadasService;