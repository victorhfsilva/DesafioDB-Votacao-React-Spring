import { Grid, Tab, TabList, TabPanel, TabPanels, Tabs } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import PautaEmAndamentoRespostaModel from "../../models/PautaEmAndamentoRespostaModel";
import { CategoriasMappper } from "../../mappers/CategoriasMappper";
import PautaFechada from "./PautaFechada";
import obterPautasFechadasService from "../../services/obterPautasFechadas.service";


const AbrirPautas = () => {
    const navigate = useNavigate();
    const [tabAtiva, setTabAtiva] = useState(0);
    const [pautas, setPautas] = useState<PautaEmAndamentoRespostaModel[]>([]);

    useEffect(() => {
        const categoria = CategoriasMappper[tabAtiva];
        obterPautasFechadasService(categoria.type)
            .then((data) => {
                setPautas(data);
            })
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