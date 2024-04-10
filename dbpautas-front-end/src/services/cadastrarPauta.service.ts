import api from "../libs/api";
import CadastrarPautaRequisicaoModel from "../models/CadastrarPautaRequisicaoModel";

const cadastrarPautaService = async (CadastrarPautaRequisicao: CadastrarPautaRequisicaoModel) => {
    const response = await api.post('/pauta/registrar', CadastrarPautaRequisicao , {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    });
    return response.data as boolean;
}

export default cadastrarPautaService;