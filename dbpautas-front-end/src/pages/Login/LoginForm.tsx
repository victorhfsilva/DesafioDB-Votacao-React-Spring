import { Button, Flex, Heading, Input } from "@chakra-ui/react";

const LoginForm = () => {

    const handleCpfChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        event.target.value = value.replace(/[^0-9]/g, "");
    };

    return (
        <Flex background={'cinza1'} height={'20em'} width={'24em'} align={'center'} margin={'4vw'} borderRadius={'1em'} direction={'column'}>
            <Flex width={'100%'} padding={'3vw'} direction={'column'}>
                <Heading  textAlign={'left'} color={'cinza4'} fontSize={'1.6em'} fontFamily={'Poppins'} fontWeight={'300'}>Login</Heading>
                <Input marginTop={'2vw'} placeholder='CPF' color={'cinza3'} background={'cinza1'} borderColor={'cinza3'} maxLength={11} type={'text'} onChange={handleCpfChange}/>
                <Input marginTop={'2vw'} placeholder='Senha' color={'cinza3'} background={'cinza1'} borderColor={'cinza3'} maxLength={30} type='password'/>
            </Flex>
            <Flex width={'100%'} direction={'column'} align={'flex-end'} paddingRight={'3vw'}>
                <Button width={'40%'} background={'cinza4'} color={'branco'} _hover={{background: 'cinza3'}} _active={{background: 'cinza3'}}>Entrar</Button>
            </Flex>
        </Flex>
    );
};

export default LoginForm;