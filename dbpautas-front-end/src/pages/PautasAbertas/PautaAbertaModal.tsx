import { Button, Flex, Heading, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text, useToast } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { getCategoriaFormatada } from "../../mappers/CategoriasMappper";
import votarEmPautaService from "../../services/votarEmPauta.service";

interface PautaAbertaProps {
    pauta: PautaEmAndamentoRespostaModel;
    isOpen: boolean;
    onClose: () => void;
    navigate: (path: string) => void;
}

const PautaAbertaModal: React.FC<PautaAbertaProps> = ({pauta, isOpen, onClose, navigate}) => {

    const toast = useToast();

    const onVotoSim = () => {
    votarEmPautaService(pauta.id, "SIM").then(() => {
            toast({
                title: "Voto realizado com sucesso.",
                description: "Obrigado por sua votação.",
                status: "success",
                duration: 9000,
                isClosable: true,
            });
        onClose();
        navigate("/");
    })};

    const onVotoNao = () => {
        votarEmPautaService(pauta.id, "NAO").then(() => {
                toast({
                    title: "Voto realizado com sucesso.",
                    description: "Obrigado por sua votação.",
                    status: "success",
                    duration: 9000,
                    isClosable: true,
                });
            onClose();
        })};

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
                        maxHeight={'16em'}>
                        <b>Resumo:</b> {pauta.resumo}
                        <br />
                        <br />
                        <b>Descrição:</b> {pauta.descricao}
                        <br />
                        <br />
                        <b>Categoria:</b> {getCategoriaFormatada(pauta.categoria)}
                    </Text>    
                    
                </ModalBody>
                <ModalFooter>
                    <Flex direction={'row'} justifyContent={'flex-end'}>
                        <Button colorScheme='red' variant={'outline'} mr={3} onClick={onVotoNao}>
                            Não
                        </Button>
                        <Button colorScheme='green' mr={3} onClick={onVotoSim}>
                            Sim
                        </Button>
                    </Flex>
                </ModalFooter>
            </ModalContent>
        </Modal>
    )

}

export default PautaAbertaModal;