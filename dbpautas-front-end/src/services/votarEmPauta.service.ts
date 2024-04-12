import axios from "axios";
import api from "../libs/api";
import VotoModel from "../models/VotoModel";
import logoutService from "./logout.service";

const obterPautasAbertasService = async (id: number,
    voto: VotoModel,
    setAutenticado: (isAutenticado: boolean) => void,
    setAdmin: (isAdmin: boolean) => void,
    navigate: (path: string) => void,
) => {
    return api.get('/pauta/votar/'+id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        },
        params: {
            voto: voto
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

export default obterPautasAbertasService;