import { Button, Flex, Heading, Input } from "@chakra-ui/react";
import { useState } from "react";

const LoginForm = () => {
    interface  ILoginForm {
        cpf: string;
        senha: string;
    }

    const [loginForm, setLoginForm] = useState<ILoginForm>({    
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

    const handleCpfChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        event.target.value = value.replace(/[^0-9]/g, "");
    };

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        console.log(loginForm);
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
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            handleCpfChange(event);
                            handleInputChange(event);
                        }}/>
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
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                            handleInputChange(event);
                        }}/>
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