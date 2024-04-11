import axios from "axios";
import api from "../libs/api";
import CadastrarPautaRequisicaoModel from "../models/CadastrarPautaRequisicaoModel";
import logoutService from "./logout.service";

const cadastrarPautaService = async (cadastrarPautaRequisicao: CadastrarPautaRequisicaoModel, 
                                        setAutenticado: (isAutenticado: boolean) => void,
                                        setAdmin: (isAdmin: boolean) => void,
                                        navigate: (path: string) => void
                                      ) => {
    
        return api.post('/pauta/registrar', cadastrarPautaRequisicao , {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }}).then(response => {
                return response.data as boolean;
            }).catch((error) =>{
                if (axios.isAxiosError(error) && error.response && error.response.status === 403) {
                    logoutService(setAutenticado, setAdmin, navigate);
                }
                throw error;
            });
}

export default cadastrarPautaService;