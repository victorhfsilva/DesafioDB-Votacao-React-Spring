import PapelModel from "./PapelModel";

type RegistrarUsuarioRequisicaoModel = {
    nome: string;
    sobrenome: string;
    email: string;
    cpf: string;
    senha: string;
    papel: PapelModel;
}

export default RegistrarUsuarioRequisicaoModel;