import { fireEvent, render, waitFor } from "@testing-library/react";
import { ChakraProvider } from "@chakra-ui/provider";
import defaultTheme from "../../../themes/default"
import RegistrarUsuarioRequisicaoModel from "../../../models/RegistrarUsuarioRequisicaoModel";
import RegistrarUsuario from "../../../pages/RegistrarUsuario";
import registrarUsuarioService from "../../../services/registrarUsuario.service";

const registrarUsuarioRequisicao: RegistrarUsuarioRequisicaoModel = {
    nome: 'João',
    sobrenome: 'Silveira',
    email: 'joao_silveira@email.com',
    cpf: '12345678900',
    senha: 'senha123',
    papel: 'USUARIO'
}

jest.mock('../../../services/registrarUsuario.service', () => {
    return jest.fn().mockResolvedValue(true)
});

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
}));

describe("Testes da página de Registrar Usuário", () => {

    it("deveria registrar usuário com sucesso", async () => {

        render(
            <ChakraProvider theme={defaultTheme}>
                <RegistrarUsuario />
            </ChakraProvider>
        )
        
        const nomeInput = document.querySelector('input[name="nome"]');
        const sobrenomeInput = document.querySelector('input[name="sobrenome"]');
        const emailInput = document.querySelector('input[name="email"]');
        const cpfInput = document.querySelector('input[name="cpf"]');
        const senhaInput = document.querySelector('input[name="senha"]');
        const papelSelect = document.querySelector('select[name="papel"]');
        const submitButton = document.querySelector('button[type="submit"]');
        
        if (nomeInput) fireEvent.change(nomeInput, { target: { value: 'João' } });
        if (sobrenomeInput) fireEvent.change(sobrenomeInput, { target: { value: 'Silveira' } });
        if (emailInput) fireEvent.change(emailInput, { target: { value: 'joao_silveira@email.com' } });
        if (cpfInput) fireEvent.change(cpfInput, { target: { value: '12345678900' } });
        if (senhaInput) fireEvent.change(senhaInput, { target: { value: 'senha123' } });
        if (papelSelect) fireEvent.change(papelSelect, { target: { value: 'USUARIO' } });
        if (submitButton) fireEvent.click(submitButton);
    
        await waitFor(() => expect(mockNavigate).toHaveBeenCalledWith('/'));
        await waitFor(() => expect(registrarUsuarioService).toHaveBeenCalledWith(registrarUsuarioRequisicao));
    });

})