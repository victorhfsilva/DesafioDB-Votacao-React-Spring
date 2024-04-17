import api from "../libs/api";
import CategoriaModel from "../models/CategoriaModel";
import PautaEmAndamentoRespostaModel from "../models/PautaEmAndamentoRespostaModel";

const obterPautasAbertasService = async (categoria: CategoriaModel | "") => {
    return api.get('/pauta/aberta/'+categoria).then(response => {
            return response.data as PautaEmAndamentoRespostaModel[];
        });
}

export default obterPautasAbertasService;