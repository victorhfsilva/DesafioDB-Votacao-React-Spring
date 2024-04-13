import { Button, Flex, Heading, Text, useDisclosure } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import PautaFechadaModal from "./PautaFechadaModal";


interface PautaEmAndamentoProps {
    pauta: PautaEmAndamentoRespostaModel;
    setAutenticado: (isAutenticado: boolean) => void;
    setAdmin: (isAdmin: boolean) => void;
    navigate: (path: string) => void;
}

const PautaFechada: React.FC<PautaEmAndamentoProps>  = ({pauta, setAutenticado, setAdmin, navigate}) => {
    const {isOpen, onOpen, onClose} = useDisclosure();

    return(
        <>
            <PautaFechadaModal pauta={pauta} 
                isOpen={isOpen} 
                onClose={onClose}
                setAutenticado={setAutenticado}
                setAdmin={setAdmin}
                navigate={navigate}/>
            <Flex 
            background={'cinza1'} 
            width={'20em'} 
            align={'flex-start'} 
            margin={'1.6vw'} 
            borderRadius={'1em'} 
            direction={'column'}>
                <Flex width={'100%'} direction={'row'} justifyContent={'space-between'}>
                    <Text color={'cinza4'} fontFamily={'Poppins'} padding={'0.8em 1em 0.8em 1em'}>
                        #{pauta.id}
                    </Text>
                </Flex>
                <Heading  
                    textAlign={'left'}
                    padding={'0em 1em 0em 0.8em'} 
                    color={'cinza4'} 
                    fontSize={'1.2em'} 
                    fontFamily={'Poppins'} 
                    fontWeight={'700'}
                    maxHeight={'2.5em'}
                    overflow={'hidden'}>
                        {pauta.titulo}
                </Heading>
                <Text color={'cinza4'} 
                    fontFamily={'Poppins'} 
                    padding={'0.8em 1em 0.8em 1em'}
                    overflow={'auto'}
                    maxHeight={'7em'}>
                    <b>Resumo:</b> {pauta.resumo}
                </Text>
                <Flex width={'100%'} direction={'row'} justifyContent={'flex-end'}>
                    <Button 
                        width={'26%'} 
                        background={'cinza2'} 
                        color={'cinza4'}
                        _hover={{background: 'cinza5', color: 'cinza4'}} 
                        _active={{background: 'cinza5', color: 'cinza4'}}
                        onClick={onOpen}>
                            Sobre
                    </Button>
                </Flex>
            </Flex>
        </>
    )
}

export default PautaFechada;