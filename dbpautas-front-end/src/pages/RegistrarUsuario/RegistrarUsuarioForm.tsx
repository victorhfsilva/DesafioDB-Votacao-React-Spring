import { Button, Flex, Heading, Input, Select, Text} from '@chakra-ui/react';
import RegistrarUsuarioRequisicaoModel from '../../models/RegistrarUsuarioRequisicaoModel';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import registrarUsuarioService from '../../services/registrarUsuario.service';


const RegistrarUsuarioForm = () => {
    

    const navigate = useNavigate();

    const [registrarUsuarioForm, setRegistrarUsuarioForm] = useState<RegistrarUsuarioRequisicaoModel>({    
        nome: '',
        sobrenome: '',
        email: '',
        cpf: '',
        senha: '',
        papel: 'USUARIO'
    });

    const handleCpfChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        event.target.value = value.replace(/[^0-9]/g, "");
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setRegistrarUsuarioForm({
          ...registrarUsuarioForm,
          [name]: value,
        });
    };
    
    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        registrarUsuarioService(registrarUsuarioForm).then((data) => {
            if (data) navigate('/');
        });
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <Flex 
                    background={'cinza1'} 
                    height={'35em'}
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
                                Registrar Usuário
                        </Heading>
                        <Input 
                            marginTop={'2vw'} 
                            placeholder='Nome' 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'} 
                            maxLength={50} 
                            type={'text'} 
                            name="nome"
                            value={registrarUsuarioForm.nome}
                            onChange={handleInputChange}/>
                        <Input 
                            marginTop={'1vw'} 
                            placeholder='Sobrenome' 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'} 
                            maxLength={50} 
                            type={'text'} 
                            name="sobrenome"
                            value={registrarUsuarioForm.sobrenome}
                            onChange={handleInputChange}/>
                        <Input 
                            marginTop={'1vw'} 
                            placeholder='Email' 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'} 
                            maxLength={100} 
                            type={'email'} 
                            name="email"                            
                            value={registrarUsuarioForm.email}
                            onChange={handleInputChange}/>
                        <Input 
                            marginTop={'1vw'} 
                            placeholder='CPF' 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'} 
                            maxLength={11} 
                            type={'text'} 
                            name="cpf"
                            value={registrarUsuarioForm.cpf}
                            onChange={(event) => {
                                handleCpfChange(event);
                                handleInputChange(event);
                            }} />
                        <Input 
                            marginTop={'1vw'} 
                            placeholder='Senha' 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'} 
                            maxLength={30} 
                            type={'password'} 
                            name="senha"
                            value={registrarUsuarioForm.senha}
                            onChange={handleInputChange} />
                        <Select
                            marginTop={'1vw'} 
                            color={'cinza3'} 
                            background={'cinza1'} 
                            borderColor={'cinza3'}
                            name="papel"
                            value={registrarUsuarioForm.papel}
                            onChange={handleInputChange}>
                                <option value='USUARIO'>Usuário</option>
                                <option value='ADMIN'>Administrador</option>
                        </Select>
                        <Text color={'cinza4'} 
                            fontFamily={'Poppins'} 
                            padding={'0.8em 0em 0em 0em'}
                            fontSize={'0.6em'}>
                            A senha deve conter um número, uma letra maiúscula, uma letra minúscula, um caractere especial e no mínimo 8 dígitos.
                        </Text>
                    </Flex>
                    
                    <Flex width={'100%'} direction={'column'} align={'flex-end'} paddingRight={'3vw'}>
                        <Button 
                            width={'40%'} 
                            background={'cinza4'} 
                            color={'branco'} 
                            _hover={{background: 'cinza3'}} 
                            _active={{background: 'cinza3'}}
                            type="submit">
                                Registrar
                        </Button>
                    </Flex>
                </Flex>
            </form>
        </div>
    );
};

export default RegistrarUsuarioForm;