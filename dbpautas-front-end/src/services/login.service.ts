import api from '../libs/api';
import LoginRequisicaoModel from '../models/LoginRequisicaoModel';
import LoginRespostaModel from '../models/LoginRespostaModel';


const loginService = async (loginForm: LoginRequisicaoModel) => {
    const response = await api.post('/login', loginForm);
    return response.data as LoginRespostaModel;
}

export default loginService;