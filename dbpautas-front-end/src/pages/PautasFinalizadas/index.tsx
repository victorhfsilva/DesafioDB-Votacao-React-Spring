import { Grid, Tab, TabList, TabPanel, TabPanels, Tabs } from "@chakra-ui/react";
import PautaFinalizada from "./PautaFinalizada";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";
import {CategoriasMappper} from "../../mappers/CategoriasMappper";
import obterPautasFinalizadasService from "../../services/obterPautasFinalizadas.service";
import { useEffect, useState } from "react";

const PautasFinalizadas = () => {

    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaFinalizadaRespostaModel[]>([]);

    useEffect(() => {
        const categoria = CategoriasMappper[tabAtiva];
        obterPautasFinalizadasService(categoria.type)
            .then((data) => {
                setPautas(data);
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
                {CategoriasMappper.map((_item, index) => (
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