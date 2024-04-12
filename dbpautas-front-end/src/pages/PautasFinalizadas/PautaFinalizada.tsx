import { Button, Flex, Heading, Text, useDisclosure } from "@chakra-ui/react";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";
import PautaFinalizadaModal from "./PautaFinalizadaModal";
import { getDecisaoFormatada } from "../../mappers/DecisoesMapper";

interface PautaFinalizadaProps {
    pauta: PautaFinalizadaRespostaModel;
}

const PautaFinalizada: React.FC<PautaFinalizadaProps>  = ({pauta}) => {
    const {isOpen, onOpen, onClose} = useDisclosure();

    return (
        <>
            <PautaFinalizadaModal pauta={pauta} isOpen={isOpen} onClose={onClose}/>
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
                    <Text color={'cinza4'} fontFamily={'Poppins'} padding={'0.8em 1em 0.8em 1em'}>
                    {getDecisaoFormatada(pauta.decisao)}
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
                <Flex width={'100%'} direction={'row'} justifyContent={'space-between'}>
                    <Text color={'cinza4'} fontFamily={'Poppins'} padding={'0.8em 1em 0.8em 1em'}>
                        Sim: {pauta.votosSim} | Não: {pauta.votosNao}
                    </Text>
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

export default PautaFinalizada;