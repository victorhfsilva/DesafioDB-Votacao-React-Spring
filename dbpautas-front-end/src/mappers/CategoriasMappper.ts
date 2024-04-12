import CategoriaModel from "../models/CategoriaModel";

interface CategoriaMapperInterface {
    categoria: string;
    type: CategoriaModel | "";
} 

const CategoriasMappper: CategoriaMapperInterface[] = [
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

const getCategoriaFormatada = (categoriaType: CategoriaModel): string => {
    const categoriaDaPauta = CategoriasMappper.find(c => c.type === categoriaType);
    return categoriaDaPauta ? categoriaDaPauta.categoria : '';
}

export {CategoriasMappper, getCategoriaFormatada};