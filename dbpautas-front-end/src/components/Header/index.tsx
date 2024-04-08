import { Flex } from "@chakra-ui/react";
import LogoButton from "./LogoButton";
import PautasMenu from "./PautasMenu";
import AdminMenu from "./AdminMenu";
import UsuarioMenu from "./UsuarioMenu";

const Header = () => {
    return (
        <Flex backgroundColor={'cinza4'} justifyContent="space-between">
            <LogoButton/>
            <Flex padding={'0.9em 1.2em 0.9em 1.2em'} height={'3.3em'}>
                <PautasMenu />
                <AdminMenu />
                <UsuarioMenu />
            </Flex>
        </Flex>
    );
};

export default Header;