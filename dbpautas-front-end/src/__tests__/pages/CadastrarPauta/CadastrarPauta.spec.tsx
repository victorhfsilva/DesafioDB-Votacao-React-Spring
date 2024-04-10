import { ChakraProvider } from "@chakra-ui/provider";
import { fireEvent, render, waitFor } from "@testing-library/react";
import defaultTheme from "../../../themes/default"
import CadastrarPauta from "../../../pages/CadastrarPauta";
import CadastrarPautaRequisicaoModel from "../../../models/CadastrarPautaRequisicaoModel";
import cadastrarPautaService from "../../../services/cadastrarPauta.service";

const cadastrarPautaRequisicao: CadastrarPautaRequisicaoModel = {
    titulo: 'Título da Pauta',
    resumo: 'Resumo da Pauta',
    descricao: 'Descrição da Pauta',
    categoria: 'FINANCAS'
}

const mockSetAutenticado = jest.fn();
const mockSetAdmin = jest.fn();

jest.mock('../../../hooks/useAuthStore', () => ({
    __esModule: true,
    default: () => ({
      setAutenticado: mockSetAutenticado,
      setAdmin: mockSetAdmin,
    }),
  }));
  

jest.mock('../../../services/cadastrarPauta.service', () => {
    return jest.fn().mockResolvedValue(true)
});

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
}));

it("deveria cadastrar pauta com sucesso", async () => {
    render(
        <ChakraProvider theme={defaultTheme}>
            <CadastrarPauta />
        </ChakraProvider>
    );

    const tituloInput = document.querySelector('input[name="titulo"]');
    const resumoTextArea = document.querySelector('textarea[name="resumo"]');
    const descricaoTextArea = document.querySelector('textarea[name="descricao"]');
    const categoriaSelect = document.querySelector('select[name="categoria"]');
    const submitButton = document.querySelector('button[type="submit"]');
    
    if (tituloInput) fireEvent.change(tituloInput, { target: { value: 'Título da Pauta' } });
    if (resumoTextArea) fireEvent.change(resumoTextArea, { target: { value: 'Resumo da Pauta' } });
    if (descricaoTextArea) fireEvent.change(descricaoTextArea, { target: { value: 'Descrição da Pauta' } });
    if (categoriaSelect) fireEvent.change(categoriaSelect, { target: { value: 'FINANCAS' } });
    if (submitButton) fireEvent.click(submitButton);

    await waitFor( () => expect(cadastrarPautaService).toHaveBeenCalledWith(cadastrarPautaRequisicao, mockSetAutenticado, mockSetAdmin, mockNavigate))
    await waitFor(() => expect(mockNavigate).toHaveBeenCalledWith('/abrirPauta'));
})