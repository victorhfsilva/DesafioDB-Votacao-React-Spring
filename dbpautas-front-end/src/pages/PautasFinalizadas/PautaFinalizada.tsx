import { Flex, Heading, Text } from "@chakra-ui/react";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";

interface PautaFinalizadaProps {
    pauta: PautaFinalizadaRespostaModel;
}

const PautaFinalizada: React.FC<PautaFinalizadaProps>  = ({pauta}) => {
    return (
        <Flex 
        background={'cinza1'} 
        height={'17em'} 
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
                {(() => {
                    switch (pauta.decisao) {
                        case 'APROVADO':
                            return 'Aprovado';
                        case 'REPROVADO':
                            return 'Reprovado';
                        case 'EMPATE':
                            return 'Empate';
                    }
                })()}
                </Text>
            </Flex>

            <Heading  
                textAlign={'left'}
                padding={'0em 1em 0em 0.6em'} 
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
                <Text color={'cinza4'} fontFamily={'Poppins'} padding={'0.8em 1em 0.8em 1em'}>
                    Sim: {pauta.votosSim} | N: {pauta.votosNao}
                </Text>
            </Flex>
        </Flex>
    )
}

export default PautaFinalizada;