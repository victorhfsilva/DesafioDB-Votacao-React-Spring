import { Button, Flex, Heading, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text } from "@chakra-ui/react";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";
import {getCategoriaFormatada} from "../../mappers/CategoriasMappper";
import { getDecisaoFormatada } from "../../mappers/DecisoesMapper";

interface PautaFinalizadaProps {
    pauta: PautaFinalizadaRespostaModel;
    isOpen: boolean;
    onClose: () => void;
}

const PautaFinalizadaModal: React.FC<PautaFinalizadaProps> = ({pauta, isOpen, onClose}) => {
    return(
        <Modal isOpen={isOpen} onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
                <ModalHeader>
                    <Heading  
                        textAlign={'left'} 
                        color={'cinza4'} 
                        fontSize={'1.2em'} 
                        fontFamily={'Poppins'} 
                        fontWeight={'300'}
                        id={`titulo-${pauta.id}`}
                        className="titulo">
                        {pauta.titulo}
                    </Heading>
                </ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                    <Text color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'16em'}
                        id={`desc-${pauta.id}`}
                        className="desc">
                        <b>Resumo:</b> {pauta.resumo}
                        <br />
                        <br />
                        <b>Descrição:</b> {pauta.descricao}
                        <br />
                        <br />
                        <b>Categoria:</b> {getCategoriaFormatada(pauta.categoria)}
                    </Text>    
                    <Flex direction={'row'} justifyContent={'space-between'}>
                        <Text 
                            color={'cinza4'}
                            fontFamily={'Poppins'} 
                            padding={'0.8em 1em 0.8em 1em'}
                            id={`decisao-${pauta.id}`}
                            className="decisao">
                            <b> {getDecisaoFormatada(pauta.decisao)} </b>
                        </Text>
                        <Text  
                            color={'cinza4'}
                            fontFamily={'Poppins'} 
                            padding={'0.8em 1em 0.8em 1em'}
                            id={`votos-${pauta.id}`}
                            className="votos">
                            Sim: {pauta.votosSim} | Não: {pauta.votosNao}
                        </Text>
                    </Flex>
                    
                </ModalBody>
                <ModalFooter>
                    <Button 
                        colorScheme='gray' 
                        mr={3} 
                        onClick={onClose}
                        id={`fechar-${pauta.id}`}
                        className="fechar">
                        Fechar
                    </Button>
                </ModalFooter>
            </ModalContent>
        </Modal>
    )

}

export default PautaFinalizadaModal;