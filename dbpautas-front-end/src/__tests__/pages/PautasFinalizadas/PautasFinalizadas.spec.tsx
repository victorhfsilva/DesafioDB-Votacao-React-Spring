import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import Page from "../../../models/PageModel";
import PautaFinalizadaRespostaModel from "../../../models/PautaFinalizadaRespostaModel";
import { ChakraProvider } from "@chakra-ui/react";
import PautasFinalizadas from "../../../pages/PautasFinalizadas";
import defaultTheme from "../../../themes/default"
import obterPautasFinalizadasService from "../../../services/obterPautasFinalizadas.service";
import '@testing-library/jest-dom';

const mockSetAutenticado = jest.fn();
const mockSetAdmin = jest.fn();

const pautaFinalizadaResposta: Page<PautaFinalizadaRespostaModel> = {
    totalElements: 10,
    totalPages: 2,
    size: 6,
    content: [
        {
            id: 1,
            titulo: "Titulo 1",
            resumo: "Resumo 1",
            descricao: "Descricao 1",
            categoria: "FINANCAS",
            votosSim: 5,
            votosNao: 2,
            decisao: "APROVADO"
        }
    ],
    number: 0,
    sort: {
        empty: false,
        unsorted: false,
        sorted: true
    },
    pageable: {
        offset: 0,
        sort: {
            empty: false,
            unsorted: false,
            sorted: true
        },
        paged: true,
        unpaged: false,
        pageSize: 6,
        pageNumber: 0
    },
    first: true,
    last: false,
    numberOfElements: 6,
    empty: false
};

jest.mock('../../../hooks/useAuthStore', () => ({
    __esModule: true,
    default: () => ({
      setAutenticado: mockSetAutenticado,
      setAdmin: mockSetAdmin,
    }),
}));

jest.mock('../../../services/obterPautasFinalizadas.service', () => {
    return jest.fn().mockResolvedValue(pautaFinalizadaResposta)
});

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
}));

describe("Teste de obtenção de pautas finalizadas", () => {
    it('deveria carregar pautas finalizadas com sucesso', async (): Promise<void> => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <PautasFinalizadas />
            </ChakraProvider>
        );
    
        await waitFor(() => expect(obterPautasFinalizadasService).toHaveBeenCalledWith("", mockSetAutenticado, mockSetAdmin, mockNavigate, 0 , 6))
    
        const titulos = screen.queryAllByText('Titulo 1');
        expect(titulos[0]).toBeInTheDocument();
    
        const resumos = screen.queryAllByText('Resumo 1');
        expect(resumos[0]).toBeInTheDocument();
    
        const decisoes = screen.queryAllByText('Aprovado');
        expect(decisoes[0]).toBeInTheDocument();
    });
  
    it('deveria abrir o modal de uma pauta com sucesso', async (): Promise<void> => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <PautasFinalizadas />
            </ChakraProvider>
        );
    
        await waitFor(() => expect(obterPautasFinalizadasService).toHaveBeenCalledWith("", mockSetAutenticado, mockSetAdmin, mockNavigate, 0 , 6))
    
        const botaoSobre = screen.queryAllByText('Sobre');
        expect(botaoSobre[0]).toBeInTheDocument();
        fireEvent.click(botaoSobre[0]);

        const descricao = screen.queryAllByText('Descricao 1');
        expect(descricao[0]).toBeInTheDocument();
    
    });
})


