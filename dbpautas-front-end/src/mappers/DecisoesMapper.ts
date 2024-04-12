import DecisaoModel from "../models/DecisaoModel";

interface DecisoesMapperInterface {
    decisao: string;
    type: DecisaoModel;
}

const DecisoesMapper: DecisoesMapperInterface[] = [
    { decisao: "Aprovado", type: "APROVADO" },
    { decisao: "Reprovado", type: "REPROVADO" },
    { decisao: "Empate", type: "EMPATE" }
];

const getDecisaoFormatada = (decisaoType: DecisaoModel): string  => {
    const decisaoTomada = DecisoesMapper.find(c => c.type === decisaoType);
    return decisaoTomada ? decisaoTomada.decisao : '';
}

export {DecisoesMapper, getDecisaoFormatada} ;