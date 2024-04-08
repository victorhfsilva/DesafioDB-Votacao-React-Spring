import { extendTheme } from "@chakra-ui/react";
import cores from "./tokens/cores";

const defaultTheme = {
    colors: cores,
}

export default extendTheme(defaultTheme);