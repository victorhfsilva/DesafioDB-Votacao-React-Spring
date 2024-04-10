import logoutService from "../../services/logout.service";

const mockSetAutenticado = jest.fn();
const mockSetAdmin = jest.fn();
const mockNavigate = jest.fn();

it('deveria fazer logout devidamente', () => {
    logoutService(mockSetAutenticado, mockSetAdmin, mockNavigate);
    
    expect(mockSetAutenticado).toHaveBeenCalledWith(false);
    expect(mockSetAdmin).toHaveBeenCalledWith(false);
    expect(localStorage.getItem('token')).toBeNull();
    expect(mockNavigate).toHaveBeenCalledWith('/login');
})