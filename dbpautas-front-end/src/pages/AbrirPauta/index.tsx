import { Grid, Tab, TabList, TabPanel, TabPanels, Tabs, useToast } from "@chakra-ui/react";
import useAuthStore from "../../hooks/useAuthStore";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { CategoriasMappper } from "../../mappers/CategoriasMappper";
import PautaFechada from "./PautaFechada";
import obterPautasFechadasService from "../../services/obterPautasFechadas.service";


const AbrirPautas = () => {
    const toast = useToast();
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaEmAndamentoRespostaModel[]>([]);

    useEffect(() => {
        const categoria = CategoriasMappper[tabAtiva];
        obterPautasFechadasService(categoria.type, setAutenticado, setAdmin, navigate)
            .then((data) => {
                setPautas(data);
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
    }, [tabAtiva]); 

    return (
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
    );
};

export default AbrirPautas;