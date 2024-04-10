import { Button, Flex, Heading, Input } from "@chakra-ui/react";
import { useState } from "react";
import LoginRequisicaoModel from "../../models/LoginRequisicaoModel";
import useAuthStore from "../../hooks/useAuthStore";
import loginService from "../../services/login.service";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {

    const { setAutenticado, setAdmin } = useAuthStore();

    const navigate = useNavigate();

    const [loginForm, setLoginForm] = useState<LoginRequisicaoModel>({    
        cpf: '',
        senha: ''
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLoginForm({
          ...loginForm,
          [name]: value,
        });
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = await loginService(loginForm);
        localStorage.setItem('token', data.token);
        setAutenticado(true);
        if(data.papel === "ADMIN"){
            setAdmin(true);
        } else {
            setAdmin(false);
        }
        navigate('/');
    };

    return (
        <form onSubmit={handleSubmit}>
            <Flex 
                background={'cinza1'} 
                height={'20em'} 
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
                            Login
                    </Heading>
                    <Input 
                        marginTop={'2vw'} 
                        placeholder='CPF' 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'} 
                        maxLength={11} 
                        type={'text'} 
                        name="cpf"
                        value={loginForm.cpf}
                        onChange={handleInputChange}/>
                    <Input 
                        marginTop={'2vw'} 
                        placeholder='Senha' 
                        color={'cinza3'} 
                        background={'cinza1'} 
                        borderColor={'cinza3'} 
                        maxLength={30} 
                        type='password'
                        name="senha"
                        value={loginForm.senha}
                        onChange={handleInputChange}/>
                </Flex>
                
                <Flex width={'100%'} direction={'column'} align={'flex-end'} paddingRight={'3vw'}>
                    <Button 
                        width={'40%'} 
                        background={'cinza4'} 
                        color={'branco'} 
                        _hover={{background: 'cinza3'}} 
                        _active={{background: 'cinza3'}}
                        type="submit">
                            Entrar
                    </Button>
                </Flex>
            </Flex>
        </form>

    );
};

export default LoginForm;