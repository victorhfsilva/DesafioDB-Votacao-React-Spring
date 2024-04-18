import api from "../libs/api";

const abrirPautaService = async (id: number,
    minutos: number,
) => {
    return api.patch('/pauta/abrir/'+`${id}`+`?minutos=${minutos}`).then(response => {
            return response.data as boolean;
        });
}

export default abrirPautaService;