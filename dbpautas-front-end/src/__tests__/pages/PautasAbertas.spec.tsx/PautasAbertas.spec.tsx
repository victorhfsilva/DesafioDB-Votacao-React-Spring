import Page from "../../../models/PageModel";
import PautaEmAndamentoRespostaModel from "../../../models/PautaEmAndamentoRespostaModel";
import '@testing-library/jest-dom';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import PautasAbertas from "../../../pages/PautasAbertas";
import { ChakraProvider } from "@chakra-ui/react";
import defaultTheme from "../../../themes/default"
import obterPautasAbertasService from "../../../services/obterPautasAbertas.service";
import votarEmPautaService from "../../../services/votarEmPauta.service";

const mockSetAutenticado = jest.fn();
const mockSetAdmin = jest.fn();

const pautaEmAndamentoResposta: Page<PautaEmAndamentoRespostaModel> = {
    totalElements: 10,
    totalPages: 2,
    size: 6,
    content: [
        {
            id: 1,
            titulo: "Titulo 1",
            resumo: "Resumo 1",
            descricao: "Descricao 1",
            categoria: "FINANCAS"
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

jest.mock('../../../services/obterPautasAbertas.service', () => {
    return jest.fn().mockResolvedValue(pautaEmAndamentoResposta)
});

jest.mock('../../../services/votarEmPauta.service', () => {
    return jest.fn().mockResolvedValue(true)
});

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
}));

describe('Testes de obtenção de pautas abertas', () => {

    it('deveria carregar pautas abertas com sucesso', async (): Promise<void> => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <PautasAbertas />
            </ChakraProvider>
        );
    
        await waitFor(() => expect(obterPautasAbertasService).toHaveBeenCalledWith("", mockSetAutenticado, mockSetAdmin, mockNavigate, 0 ,6))
    
        const titulos = screen.queryAllByText('Titulo 1');
        expect(titulos[0]).toBeInTheDocument();
    
        const resumos = screen.queryAllByText('Resumo 1');
        expect(resumos[0]).toBeInTheDocument();
    });

    it('deveria abrir o modal e votar em sim com sucesso', async (): Promise<void> => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <PautasAbertas />
            </ChakraProvider>
        );
    
        await waitFor(() => expect(obterPautasAbertasService).toHaveBeenCalledWith("", mockSetAutenticado, mockSetAdmin, mockNavigate, 0 ,6))
    
        const botaoSobre = screen.queryAllByText('Sobre');
        expect(botaoSobre[0]).toBeInTheDocument();
        fireEvent.click(botaoSobre[0]);
            
        const botaoSim = screen.queryByText('Sim');
        expect(botaoSim).toBeInTheDocument();
        if (botaoSim) fireEvent.click(botaoSim);
        
        await waitFor(() => expect(votarEmPautaService).toHaveBeenCalledWith(1, "SIM", mockSetAutenticado, mockSetAdmin, mockNavigate))

    });


    it('deveria abrir o modal e votar em não com sucesso', async (): Promise<void> => {
        render(
            <ChakraProvider theme={defaultTheme}>
                <PautasAbertas />
            </ChakraProvider>
        );
    
        await waitFor(() => expect(obterPautasAbertasService).toHaveBeenCalledWith("", mockSetAutenticado, mockSetAdmin, mockNavigate, 0 ,6))
    
        const botaoSobre = screen.queryAllByText('Sobre');
        expect(botaoSobre[0]).toBeInTheDocument();
        fireEvent.click(botaoSobre[0]);
            
        const botaoNao = screen.queryByText('Não');
        expect(botaoNao).toBeInTheDocument();
        if (botaoNao) fireEvent.click(botaoNao);
        
        await waitFor(() => expect(votarEmPautaService).toHaveBeenCalledWith(1, "NAO", mockSetAutenticado, mockSetAdmin, mockNavigate))

    });
});