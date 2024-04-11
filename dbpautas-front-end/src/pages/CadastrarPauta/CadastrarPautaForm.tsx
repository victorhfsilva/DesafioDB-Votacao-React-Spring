import { Button, Flex, Heading, Input, Select, Textarea } from "@chakra-ui/react";
import CadastrarPautaRequisicaoModel from "../../models/CadastrarPautaRequisicaoModel";
import { useState } from "react";
import cadastrarPautaService from "../../services/cadastrarPauta.service";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../../hooks/useAuthStore";
import { useToast } from "@chakra-ui/react";

const CadastrarPautaForm = () => {

    const toast = useToast();
    
    const { setAutenticado, setAdmin } = useAuthStore();

    const navigate = useNavigate();

    const [cadastrarPautaForm, setCadastrarPautaForm] = useState<CadastrarPautaRequisicaoModel>({    
        titulo: '',
        resumo: '',
        descricao: '',
        categoria: "FINANCAS"
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setCadastrarPautaForm({
          ...cadastrarPautaForm,
          [name]: value,
        });
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        cadastrarPautaService(cadastrarPautaForm, setAutenticado, setAdmin, navigate).then((data) => {
            if (data) navigate('/abrirPauta');
        }).catch(() => {
            toast({
                title: "Não foi possível cadastrar a pauta.",
                description: "Por favor, tente novamente",
                status: "error",
                duration: 9000,
                isClosable: true,
            });
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
            <Flex 
                background={'cinza1'} 
                height={'29.5em'}
                width={'24em'} 
                align={'center'} 
                margin={'4vw'} 
                borderRadius={'1em'} 
                direction={'column'}>
                
                <Flex width={'100%'} padding={'3vw'} direction={'column'}>
                    <Heading  
                        textAlign={'left'} 
                        color={'cinza4'} 
                        fontSize={'1.6em'} 
                        fontFamily={'Poppins'} 
                        fontWeight={'300'}>
                            Cadastrar Pauta
                    </Heading>
                    <Input 
                        marginTop={'2vw'} 
                        placeholder='Título' 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'} 
                        maxLength={255} 
                        type={'text'} 
                        name="titulo"
                        value = {cadastrarPautaForm.titulo}
                        onChange={handleInputChange}/>
                    <Textarea 
                        marginTop={'1vw'} 
                        placeholder='Resumo' 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'} 
                        maxLength={500} 
                        name="resumo"
                        value={cadastrarPautaForm.resumo}
                        onChange={handleInputChange}/>
                    <Textarea 
                        marginTop={'1vw'} 
                        placeholder='Descrição' 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'} 
                        name="descricao"
                        value={cadastrarPautaForm.descricao}
                        onChange={handleInputChange}/>
                    <Select 
                        marginTop={'1vw'} 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'}
                        name="categoria"
                        value={cadastrarPautaForm.categoria}
                        onChange={handleInputChange}>
                            <option value='FINANCAS'>Finanças</option>
                            <option value='SAUDE'>Saúde</option>
                            <option value='EDUCACAO'>Educação</option>
                            <option value='TECNOLOGIA'>Tecnologia</option>
                            <option value='MEIO_AMBIENTE'>Meio Ambiente</option>
                            <option value='TRANSPORTE'>Transporte</option>
                            <option value='SEGURANCA_PUBLICA'>Segurança Pública</option>
                            <option value='INFRAESTRUTURA'>Infraestrutura</option> 
                    </Select>
                </Flex>
                
                <Flex width={'100%'} direction={'column'} align={'flex-end'} paddingRight={'3vw'}>
                    <Button 
                        width={'40%'} 
                        background={'cinza4'} 
                        color={'branco'} 
                        _hover={{background: 'cinza3'}} 
                        _active={{background: 'cinza3'}}
                        type="submit">
                            Cadastrar
                    </Button>
                </Flex>
            </Flex>
        </form>
        </div>
    );
};

export default CadastrarPautaForm;