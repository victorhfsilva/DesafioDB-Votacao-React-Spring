import { Link } from 'react-router-dom';
import { Flex, Image, Text } from '@chakra-ui/react';

const LogoButton = () => {
    return (
        <Link to={'/'}>
            <Flex as={'a'} padding={'0.75em 0.75em 0.625em 1.25em'} height={'3.3em'} data-testid='menu-home'>
                <Image src={'/logo.svg'} alt={'Logo DBPautas'} objectFit={'cover'}/>
                <Text color={'branco'} fontFamily={"Poppins"} paddingLeft={'0.4em'} fontSize={'1.3em'}>
                    DB Pautas
                </Text>
            </Flex>
        </Link>
    );
};

export default LogoButton;