import { Flex, Grid, Tab, TabList, TabPanel, TabPanels, Tabs, Text, useToast } from "@chakra-ui/react";
import PautaFinalizada from "./PautaFinalizada";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";
import categoriasMap from "../../models/CategoriasMap";
import obterPautasFinalizadasService from "../../services/obterPautasFinalizadas.service";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../../hooks/useAuthStore";
import { useEffect, useState } from "react";

const PautasFinalizadas = () => {

    interface PaginaInterface {
        paginaAtual: number,
        isPrimeiro: boolean,
        isUltimo: boolean
    }

    const toast = useToast();
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaFinalizadaRespostaModel[]>([]);
    const [pagina, setPagina] = useState<PaginaInterface>({
        paginaAtual: 0,
        isPrimeiro: true,
        isUltimo: false
    });

    useEffect(() => {
        const categoria = categoriasMap[tabAtiva];
        obterPautasFinalizadasService(categoria.type, setAutenticado, setAdmin, navigate, pagina.paginaAtual, 6)
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
                {categoriasMap.map((item, index) => (
                    <Tab key={index}>{item.categoria}</Tab>
                ))}
            </TabList>
            <TabPanels>
                {categoriasMap.map((item, index) => (
                    <TabPanel key={index}>
                        <Grid templateColumns='repeat(3, 1fr)'>
                            {pautas.map((pauta, idx) => (
                                <PautaFinalizada key={idx} pauta={pauta} />
                            ))}
                        </Grid>
                    </TabPanel>
                ))}
            </TabPanels>
        </Tabs>
        <Flex direction={'row'} justifyContent={'center'}>
            {!pagina.isPrimeiro && <><Text as={'a'} 
                    color={'cinza4'} 
                    fontFamily={'Poppins'} 
                    padding={'0.8em 1em 0.8em 1em'}
                    overflow={'auto'}
                    maxHeight={'7em'}
                    _hover={{ textDecoration: 'underline' }}
                    onClick={() => setPagina({...pagina, paginaAtual: pagina.paginaAtual - 1})}>
                    Anterior
            </Text>
            <Text color={'cinza4'} 
                    fontFamily={'Poppins'} 
                    padding={'0.8em 1em 0.8em 1em'}
                    overflow={'auto'}
                    maxHeight={'7em'}>
                    |
            </Text></>}
            { !pagina.isUltimo && <Text as={'a'} 
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

export default PautasFinalizadas;