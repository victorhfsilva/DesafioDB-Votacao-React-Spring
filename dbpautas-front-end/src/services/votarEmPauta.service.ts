import api from "../libs/api";
import VotoModel from "../models/VotoModel";

const votarEmPautaService = async (id: number,
    voto: VotoModel
) => {
    return api.patch('/pauta/votar/'+`${id}`+`?voto=${voto}`).then(response => {
            return response.data as boolean;
        });
}

export default votarEmPautaService;