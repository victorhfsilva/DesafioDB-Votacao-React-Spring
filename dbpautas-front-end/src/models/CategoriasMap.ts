import CategoriaModel from "./CategoriaModel";

interface CategoriaMapInterface {
    categoria: string;
    type: CategoriaModel | "";
} 

const categoriasMap: CategoriaMapInterface[] = [
    { categoria: "Todos", type: ""},
    { categoria: "Educação", type: "EDUCACAO" },
    { categoria: "Finanças", type: "FINANCAS" },
    { categoria: "Infraestrutura", type: "INFRAESTRUTURA" },
    { categoria: "Meio Ambiente", type: "MEIO_AMBIENTE" },
    { categoria: "Saúde", type: "SAUDE" },
    { categoria: "Segurança Pública", type: "SEGURANCA_PUBLICA" },
    { categoria: "Tecnologia", type: "TECNOLOGIA" },
    { categoria: "Transporte", type: "TRANSPORTE" }
];

export default categoriasMap