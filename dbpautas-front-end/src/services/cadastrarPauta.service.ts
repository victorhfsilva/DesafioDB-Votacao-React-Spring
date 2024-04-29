import api from "../libs/api";
import CadastrarPautaRequisicaoModel from "../models/CadastrarPautaRequisicaoModel";

const cadastrarPautaService = async (cadastrarPautaRequisicao: CadastrarPautaRequisicaoModel) => {
    
    return api.post('/pautas', cadastrarPautaRequisicao).then(response => {
            return response.data as boolean;
        });
}

export default cadastrarPautaService;