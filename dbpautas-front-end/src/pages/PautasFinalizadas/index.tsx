import { Grid, Tab, TabList, TabPanel, TabPanels, Tabs, useToast } from "@chakra-ui/react";
import PautaFinalizada from "./PautaFinalizada";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";
import {CategoriasMappper} from "../../mappers/CategoriasMappper";
import obterPautasFinalizadasService from "../../services/obterPautasFinalizadas.service";
import { useNavigate } from "react-router-dom";
import useAuthStore from "../../hooks/useAuthStore";
import { useEffect, useState } from "react";

const PautasFinalizadas = () => {


    const toast = useToast();
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();
    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaFinalizadaRespostaModel[]>([]);

    useEffect(() => {
        const categoria = CategoriasMappper[tabAtiva];
        obterPautasFinalizadasService(categoria.type, setAutenticado, setAdmin, navigate)
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
                                <PautaFinalizada key={idx} pauta={pauta} />
                            ))}
                        </Grid>
                    </TabPanel>
                ))}
            </TabPanels>
        </Tabs>
    );
};

export default PautasFinalizadas;