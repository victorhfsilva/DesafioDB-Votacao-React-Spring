import { useNavigate } from "react-router-dom";
import useAuthStore from "./useAuthStore";
import logoutService from "../services/logout.service";
import useHandleExcecao from "./useHandleExcecao";


function useHandleSessaoExpirada() {
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const handleExcecao = useHandleExcecao();

    return function handleSessaoExpirada() {
        handleExcecao("Falha de autenticação", "Por favor, faça login novamente.");
        logoutService(setAutenticado, setAdmin, () => navigate('/login'));
    }
}

export default useHandleSessaoExpirada;