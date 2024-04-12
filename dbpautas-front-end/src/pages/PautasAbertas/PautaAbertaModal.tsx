import { Button, Flex, Heading, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text, useToast } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { getCategoriaFormatada } from "../../mappers/CategoriasMappper";
import votarEmPautaService from "../../services/votarEmPauta.service";

interface PautaAbertaProps {
    pauta: PautaEmAndamentoRespostaModel;
    isOpen: boolean;
    onClose: () => void;
    setAutenticado: (isAutenticado: boolean) => void;
    setAdmin: (isAdmin: boolean) => void;
    navigate: (path: string) => void;
}

const PautaAbertaModal: React.FC<PautaAbertaProps> = ({pauta, isOpen, onClose, setAutenticado, setAdmin, navigate}) => {

    const toast = useToast();

    const onVotoSim = () => {
    votarEmPautaService(pauta.id, "SIM", setAutenticado, setAdmin, navigate).then(() => {
            toast({
                title: "Voto realizado com sucesso.",
                description: "Obrigado por sua votação.",
                status: "success",
                duration: 9000,
                isClosable: true,
            });
        onClose();
    }).catch(() => {
        toast({
            title: "Não foi possível realizar a votação.",
            description: "Por favor, tente novamente.",
            status: "error",
            duration: 9000,
            isClosable: true,
        })
    })};

    const onVotoNao = () => {
        votarEmPautaService(pauta.id, "NAO", setAutenticado, setAdmin, navigate).then(() => {
                toast({
                    title: "Voto realizado com sucesso.",
                    description: "Obrigado por sua votação.",
                    status: "success",
                    duration: 9000,
                    isClosable: true,
                });
            onClose();
        }).catch(() => {
            toast({
                title: "Não foi possível realizar a votação.",
                description: "Por favor, tente novamente.",
                status: "error",
                duration: 9000,
                isClosable: true,
            })
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
                        <Button colorScheme='red' variant={'outline'} mr={3} onClick={onVotoSim}>
                            Não
                        </Button>
                        <Button colorScheme='green' mr={3} onClick={onVotoNao}>
                            Sim
                        </Button>
                    </Flex>
                </ModalFooter>
            </ModalContent>
        </Modal>
    )

}

export default PautaAbertaModal;