import { Grid } from "@chakra-ui/react";
import PautaFinalizada from "./PautaFinalizada";
import PautaFinalizadaRespostaModel from "../../models/PautaFinalizadaRespostaModel";

const PautasFinalizadas = () => {

    const pauta: PautaFinalizadaRespostaModel = {
        id: 1,
        titulo: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        resumo: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer hendrerit convallis neque, a faucibus tellus. Nam in nisi nunc. Donec pellentesque leo eget iaculis ultricies. In pharetra sollicitudin est ac lacinia. Integer in laoreet quam. Nulla facilisi. Praesent id consequat diam.",
        descricao: "Morbi ante est, finibus id ultrices ac, convallis quis lacus. Nam at felis quis magna sollicitudin fringilla. Nam et orci commodo, viverra mi et, lobortis urna. Sed pretium sapien at libero rhoncus iaculis. Donec luctus vehicula molestie. Sed in urna non mi efficitur tempus. Aenean id fringilla neque. Vestibulum ut neque placerat, malesuada eros sed, euismod ante. Etiam dapibus sem eget sem accumsan posuere. Suspendisse dolor massa, porttitor at dapibus vel, lobortis vel risus. Praesent pharetra, elit sit amet pulvinar imperdiet, nibh magna blandit ipsum, et fermentum massa elit at tellus. Nunc molestie tincidunt congue. Nullam odio massa, tempor ac lorem quis, hendrerit maximus urna. Morbi tempus diam in tortor vulputate, pharetra egestas ipsum cursus. Integer posuere mollis nulla, nec eleifend risus eleifend in.",
        categoria: "SAUDE",
        votosSim: 1,
        votosNao: 1,
        decisao: "EMPATE"
    }

    return (
        <Grid>
            <PautaFinalizada pauta={pauta}/>
        </Grid>
    );
};

export default PautasFinalizadas;