import { Button, Flex, Heading, Text, useDisclosure } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import PautaAbertaModal from "./PautaAbertaModal";

interface PautaEmAndamentoProps {
    pauta: PautaEmAndamentoRespostaModel;
    navigate: (path: string) => void;
}

const PautaAberta: React.FC<PautaEmAndamentoProps>  = ({pauta, navigate}) => {
    const {isOpen, onOpen, onClose} = useDisclosure();

    return(
        <>
            <PautaAbertaModal pauta={pauta} 
                isOpen={isOpen} 
                onClose={onClose}
                navigate={navigate}/>
            <Flex 
            background={'cinza1'} 
            width={'20em'} 
            align={'flex-start'} 
            margin={'1.6vw'} 
            borderRadius={'1em'} 
            direction={'column'}
            justify={'space-between'}
            className="pauta"
            id={`pauta-${pauta.id}`}>
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
                    overflow={'hidden'}
                    className="titulo"
                    id={`titulo-${pauta.id}`}>
                        {pauta.titulo}
                </Heading>
                <Text color={'cinza4'} 
                    fontFamily={'Poppins'} 
                    padding={'0.8em 1em 0.8em 1em'}
                    maxWidth={'100%'}
                    overflow={'auto'}
                    maxHeight={'7em'}
                    id={`resumo-${pauta.id}`}
                    className="resumo">
                    <b>Resumo:</b> {pauta.resumo}
                </Text>
                <Flex width={'100%'} direction={'row'} justifyContent={'flex-end'}>
                    <Button 
                        width={'26%'} 
                        background={'cinza2'} 
                        color={'cinza4'}
                        _hover={{background: 'cinza5', color: 'cinza4'}} 
                        _active={{background: 'cinza5', color: 'cinza4'}}
                        onClick={onOpen}
                        id={`sobre-${pauta.id}`}
                        className="sobre">
                            Sobre
                    </Button>
                </Flex>
            </Flex>
        </>
    )
}

export default PautaAberta;