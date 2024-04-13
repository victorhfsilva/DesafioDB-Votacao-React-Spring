import { Heading, Text } from "@chakra-ui/react";

const NotFound = () => {
    return (
        <div>
            <Heading  
                textAlign={'left'}
                padding={'1em 1em 0em 1.8em'} 
                color={'cinza4'} 
                fontSize={'1.2em'} 
                fontFamily={'Poppins'} 
                fontWeight={'700'}
                maxHeight={'2.5em'}
                overflow={'hidden'}>
                    404: Not Found
            </Heading>
            <Text color={'cinza4'} 
                fontFamily={'Poppins'} 
                padding={'0.8em 1em 0.8em 2.2em'}
                overflow={'auto'}
                maxHeight={'7em'}>
                Não foi possível encontrar a página solicitada.
            </Text>
        </div>
    );
};

export default NotFound;