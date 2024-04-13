import axios from "axios";
import api from "../libs/api";
import CategoriaModel from "../models/CategoriaModel";
import PautaFinalizadaRespostaModel from "../models/PautaFinalizadaRespostaModel";
import logoutService from "./logout.service";

const obterPautasFinalizadasService = async (categoria: CategoriaModel | "",
    setAutenticado: (isAutenticado: boolean) => void,
    setAdmin: (isAdmin: boolean) => void,
    navigate: (path: string) => void,
) => {
    return api.get('/pauta/finalizada/'+categoria, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    }).then(response => {
            return response.data as PautaFinalizadaRespostaModel[];
        }).catch((error) =>{
            if (axios.isAxiosError(error) && error.response && error.response.status === 403) {
            logoutService(setAutenticado, setAdmin, navigate);
        }
        throw error;
    });
}

export default obterPautasFinalizadasService;