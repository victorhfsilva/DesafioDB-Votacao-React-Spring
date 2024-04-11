type RegistrarUsuarioRequisicaoModel = {
    nome: string;
    sobrenome: string;
    email: string;
    cpf: string;
    senha: string;
    papel: "ADMIN" | "USUARIO";
}

export default RegistrarUsuarioRequisicaoModel;