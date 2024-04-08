import { extendTheme } from "@chakra-ui/react";
import cores from "./tokens/cores";
import fontes from "./tokens/fontes";

const defaultTheme = {
    colors: cores,
    fonts: fontes
}

export default extendTheme(defaultTheme);