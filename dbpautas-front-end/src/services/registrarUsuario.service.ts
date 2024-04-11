import RegistrarUsuarioRequisicaoModel from "../models/RegistrarUsuarioRequisicaoModel";
import axios from "axios";
import api from "../libs/api";
import logoutService from "./logout.service";

const registrarUsuarioService = async (registrarUsuarioRequisicao: RegistrarUsuarioRequisicaoModel, 
                                        setAutenticado: (isAutenticado: boolean) => void,
                                        setAdmin: (isAdmin: boolean) => void,
                                        navigate: (path: string) => void
  ) => {
    return api.post('/usuario/registrar', registrarUsuarioRequisicao , {
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

export default registrarUsuarioService;