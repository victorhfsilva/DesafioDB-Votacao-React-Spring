import { Button, Flex, Heading, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Text, useToast } from "@chakra-ui/react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { getCategoriaFormatada } from "../../mappers/CategoriasMappper";
import abrirPautaService from "../../services/abrirPauta.service";
import { useState } from "react";

interface PautaFechadaProps {
    pauta: PautaEmAndamentoRespostaModel;
    isOpen: boolean;
    onClose: () => void;
    setAutenticado: (isAutenticado: boolean) => void;
    setAdmin: (isAdmin: boolean) => void;
    navigate: (path: string) => void;
}

const PautaFechadaModal: React.FC<PautaFechadaProps> = ({pauta, isOpen, onClose, setAutenticado, setAdmin, navigate}) => {

    const toast = useToast();

    const [minutos, setMinutos] = useState(1);

    const handleMinutosChange = (valueAsString: string, valueAsNumber: number) => {
        setMinutos(valueAsNumber);
    };

    const onAbrir = () => {
    abrirPautaService(pauta.id, minutos, setAutenticado, setAdmin, navigate).then(() => {
            toast({
                title: "Pauta aberta com sucesso.",
                description: "A pauta está aberta para votação.",
                status: "success",
                duration: 9000,
                isClosable: true,
            });
        onClose();
        navigate("/");
    }).catch(() => {
        toast({
            title: "Não foi possível abrir a pauta.",
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
                    <NumberInput _placeholder={'Tempo de Votação (minutos)'} defaultValue={1} min={1} max={43200} value={minutos} onChange={handleMinutosChange}>
                        <NumberInputField />
                        <NumberInputStepper>
                            <NumberIncrementStepper />
                            <NumberDecrementStepper />
                        </NumberInputStepper>
                    </NumberInput>    
                    <Flex direction={'row'} justifyContent={'flex-end'}>
                        <Button colorScheme='gray' mr={3} onClick={onAbrir}>
                            Abrir
                        </Button>
                    </Flex>
                </ModalFooter>
            </ModalContent>
        </Modal>
    )

}

export default PautaFechadaModal;