import RegistrarUsuarioRequisicaoModel from "../models/RegistrarUsuarioRequisicaoModel";
import api from "../libs/api";

const registrarUsuarioService = async (registrarUsuarioRequisicao: RegistrarUsuarioRequisicaoModel
  ) => {
    return api.post('/usuario/registrar', registrarUsuarioRequisicao).then(response => {
            return response.data as boolean;
        });
}

export default registrarUsuarioService;