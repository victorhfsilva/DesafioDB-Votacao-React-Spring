import { fireEvent, render, waitFor } from "@testing-library/react";
import LoginRespostaModel from "../../../models/LoginRespostaModel";
import { ChakraProvider } from "@chakra-ui/react";
import defaultTheme from "../../../themes/default"
import Login from "../../../pages/Login";
import loginService from "../../../services/login.service";

const loginResposta: LoginRespostaModel = {
    token: 'token',
    papel: 'ADMIN'
}

jest.mock('../../../services/login.service', () => {
    return jest.fn().mockResolvedValue(loginResposta)
});

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
  }));
  
describe("Testes da página de login", () => {

    it('deveria obter token com sucesso', async () => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <Login />
            </ChakraProvider>
        );
    
        const cpfInput = document.querySelector('input[name="cpf"]');
        const senhaInput = document.querySelector('input[name="senha"]');
        const submitButton = document.querySelector('button[type="submit"]');
    
        if (cpfInput) fireEvent.change(cpfInput, { target: { value: '22797475291' } });
        if (senhaInput) fireEvent.change(senhaInput, { target: { value: 'senha123' } });
        if (submitButton) fireEvent.click(submitButton);
    
        await waitFor(() => expect(mockNavigate).toHaveBeenCalledWith('/'));
        await waitFor( () => expect(loginService).toHaveBeenCalledWith({cpf: "22797475291", senha: "senha123"}))
        await waitFor( () => expect(localStorage.getItem('token')).toBe('token'))
    
    });
    
})
