import api from "../libs/api";
import CategoriaModel from "../models/CategoriaModel";
import PautaFinalizadaRespostaModel from "../models/PautaFinalizadaRespostaModel";

const obterPautasFinalizadasService = async (categoria: CategoriaModel | "") => {
    return api.get('/pauta/finalizada/'+categoria).then(response => {
            return response.data as PautaFinalizadaRespostaModel[];
        });
}

export default obterPautasFinalizadasService;