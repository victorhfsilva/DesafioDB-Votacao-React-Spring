import LoginRespostaModel from "../../models/LoginRespostaModel";
import salvarLoginService from "../../services/salvarLogin.service";

const mockSetAutenticado = jest.fn();
const mockSetAdmin = jest.fn();
const mockNavigate = jest.fn();

describe('Teste do salvarLoginService', () => {
    it('deveria salvar login de administrador corretamente', () => {
        const loginResposta: LoginRespostaModel = {
            token: 'token',
            papel: 'ADMIN'
        }
        
        salvarLoginService(loginResposta, mockSetAutenticado, mockSetAdmin, mockNavigate);
        
        expect(mockSetAutenticado).toHaveBeenCalledWith(true);
        expect(mockSetAdmin).toHaveBeenCalledWith(true);
        expect(localStorage.getItem('token')).toBe('token');
        expect(mockNavigate).toHaveBeenCalledWith('/');
    })

    it('deveria salvar login de usuário corretamente', () => {
        const loginResposta: LoginRespostaModel = {
            token: 'token',
            papel: 'USUARIO'
        }
        
        salvarLoginService(loginResposta, mockSetAutenticado, mockSetAdmin, mockNavigate);
        
        expect(mockSetAutenticado).toHaveBeenCalledWith(true);
        expect(mockSetAdmin).toHaveBeenCalledWith(false);
        expect(localStorage.getItem('token')).toBe('token');
        expect(mockNavigate).toHaveBeenCalledWith('/');
    })
})
