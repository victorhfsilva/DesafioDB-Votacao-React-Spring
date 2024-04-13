import axios from "axios";
import api from "../libs/api";
import logoutService from "./logout.service";

const abrirPautaService = async (id: number,
    minutos: number,
    setAutenticado: (isAutenticado: boolean) => void,
    setAdmin: (isAdmin: boolean) => void,
    navigate: (path: string) => void,
) => {
    return api.patch('/pauta/abrir/'+`${id}`+`?minutos=${minutos}`, {}, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    }).then(response => {
            return response.data as boolean;
        }).catch((error) => {
            if (axios.isAxiosError(error) && error.response && error.response.status === 403) {
                logoutService(setAutenticado, setAdmin, navigate);
            }
            throw error;
    });
}

export default abrirPautaService;