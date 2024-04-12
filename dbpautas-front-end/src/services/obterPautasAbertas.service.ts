import axios from "axios";
import api from "../libs/api";
import CategoriaModel from "../models/CategoriaModel";
import Page from "../models/PageModel";
import PautaEmAndamentoRespostaModel from "../models/PautaEmAndamentoRespostaModel";
import logoutService from "./logout.service";

const obterPautasAbertasService = async (categoria: CategoriaModel | "",
    setAutenticado: (isAutenticado: boolean) => void,
    setAdmin: (isAdmin: boolean) => void,
    navigate: (path: string) => void,
    pagina: number = 0,
    tamanho: number = 6
) => {
    return api.get('/pauta/aberta/'+categoria, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        },
        params: {
            pagina: pagina,
            tamanho: tamanho
        }
    }).then(response => {
            return response.data as Page<PautaEmAndamentoRespostaModel>;
        }).catch((error) =>{
            if (axios.isAxiosError(error) && error.response && error.response.status === 403) {
                logoutService(setAutenticado, setAdmin, navigate);
            }
            throw error;
    });
}

export default obterPautasAbertasService;