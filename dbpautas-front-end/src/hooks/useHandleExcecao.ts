import { useToast } from "@chakra-ui/react";

function useHandleExcecao() {
    const toast = useToast();

    return function handleExcecao(titulo: string, mensagem: string) {    
        toast({
            title: titulo,
            description: mensagem,
            status: "error",
            duration: 9000,
            isClosable: true,
        });
    }
}

export default useHandleExcecao;