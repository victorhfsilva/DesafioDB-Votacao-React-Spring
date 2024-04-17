import { useNavigate } from "react-router-dom";
import useAuthStore from "./useAuthStore";
import { useToast } from "@chakra-ui/react";
import logoutService from "../services/logout.service";


function useHandleSessaoExpirada() {
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const toast = useToast();

    return function handleSessaoExpirada() {
        toast({
            title: "Sua sessão expirou",
            description: "Por favor, faça login novamente",
            status: "error",
            duration: 9000,
            isClosable: true,
        });

        logoutService(setAutenticado, setAdmin, () => navigate('/login'));
    }
}

export default useHandleSessaoExpirada;