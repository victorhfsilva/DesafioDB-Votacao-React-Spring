import { Button, Flex, Heading, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { getCategoriaFormatada } from "../../mappers/CategoriasMappper";

interface PautaAbertaProps {
    pauta: PautaEmAndamentoRespostaModel;
    isOpen: boolean;
    onClose: () => void;
}

const PautaAbertaModal: React.FC<PautaAbertaProps> = ({pauta, isOpen, onClose}) => {
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
                        fontWeight={'300'}>
                        {pauta.titulo}
                    </Heading>
                </ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                    <Text color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}>
                        <b>Resumo:</b> {pauta.resumo}
                    </Text>
                    <Text color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}>
                        <b>Descrição:</b> {pauta.descricao}
                    </Text>
                    <Text color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}>
                        <b>Categoria:</b> {getCategoriaFormatada(pauta.categoria)}
                    </Text>
                    
                </ModalBody>
                <ModalFooter>
                    <Flex direction={'row'} justifyContent={'flex-end'}>
                        <Button colorScheme='red' variant={'outline'} mr={3} onClick={onClose}>
                            Não
                        </Button>
                        <Button colorScheme='green' mr={3} onClick={onClose}>
                            Sim
                        </Button>
                    </Flex>
                </ModalFooter>
            </ModalContent>
        </Modal>
    )

}

export default PautaAbertaModal;
