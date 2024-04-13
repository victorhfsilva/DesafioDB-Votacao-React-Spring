import axios from "axios";
import api from "../libs/api";
import VotoModel from "../models/VotoModel";
import logoutService from "./logout.service";

const votarEmPautaService = async (id: number,
    voto: VotoModel,
    setAutenticado: (isAutenticado: boolean) => void,
    setAdmin: (isAdmin: boolean) => void,
    navigate: (path: string) => void,
) => {
    return api.patch('/pauta/votar/'+`${id}`+`?voto=${voto}`, {}, {
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

export default votarEmPautaService;