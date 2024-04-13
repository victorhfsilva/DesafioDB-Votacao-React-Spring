import { Flex, Grid, Tab, TabList, TabPanel, TabPanels, Tabs, Text, useToast } from "@chakra-ui/react";
import useAuthStore from "../../hooks/useAuthStore";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { CategoriasMappper } from "../../mappers/CategoriasMappper";
import PaginaModel from "../../models/PaginaModel";
import PautaFechada from "./PautaFechada";
import obterPautasFechadasService from "../../services/obterPautasFechadas.service";


const AbrirPautas = () => {
    const toast = useToast();
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaEmAndamentoRespostaModel[]>([]);
    const [pagina, setPagina] = useState<PaginaModel>({
        paginaAtual: 0,
        isPrimeiro: true,
        isUltimo: false
    });

    useEffect(() => {
        const categoria = CategoriasMappper[tabAtiva];
        obterPautasFechadasService(categoria.type, setAutenticado, setAdmin, navigate, pagina.paginaAtual, 6)
            .then((data) => {
                setPautas(data.content);
                setPagina({
                    paginaAtual: data.number,
                    isPrimeiro: data.first,
                    isUltimo: data.last
                })
            })
            .catch(() => {
                setPautas([]);
                toast({
                    title: "Não foi possível carregar as pautas.",
                    description: "Por favor, tente novamente",
                    status: "error",
                    duration: 9000,
                    isClosable: true,
                });
            });
    }, [tabAtiva, pagina.paginaAtual]); 

    return (
        <>
            <Tabs color={'cinza4'} colorScheme="gray" onChange={(index) => setTabAtiva(index)}>
                <TabList>
                    {CategoriasMappper.map((item, index) => (
                        <Tab key={index}>{item.categoria}</Tab>
                    ))}
                </TabList>
                <TabPanels>
                    {CategoriasMappper.map((item, index) => (
                        <TabPanel key={index}>
                            <Grid templateColumns='repeat(3, 1fr)'>
                                {pautas.map((pauta, idx) => (
                                    <PautaFechada key={idx}     
                                        pauta={pauta} 
                                        setAutenticado={setAutenticado} 
                                        setAdmin={setAdmin} 
                                        navigate={navigate}  />
                                ))}
                            </Grid>
                        </TabPanel>
                    ))}
                </TabPanels>
            </Tabs>
            <Flex direction={'row'} justifyContent={'center'}>
                {!pagina.isPrimeiro && <Text as={'a'} 
                        color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}
                        _hover={{ textDecoration: 'underline' }}
                        onClick={() => setPagina({...pagina, paginaAtual: pagina.paginaAtual - 1})}>
                        Anterior
                </Text>} 
                {!pagina.isPrimeiro && !pagina.isUltimo && <Text color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}>
                        |
                </Text>}
                {!pagina.isUltimo && <Text as={'a'} 
                        color={'cinza4'} 
                        fontFamily={'Poppins'} 
                        padding={'0.8em 1em 0.8em 1em'}
                        overflow={'auto'}
                        maxHeight={'7em'}
                        _hover={{ textDecoration: 'underline' }}
                        onClick={() => setPagina({...pagina, paginaAtual: pagina.paginaAtual + 1})}>
                        Próximo
                </Text>}
            </Flex>
            </>
    );
};

export default AbrirPautas;