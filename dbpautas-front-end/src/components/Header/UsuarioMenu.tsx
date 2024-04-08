import { Link } from 'react-router-dom';
import { Menu, MenuButton, MenuList, MenuItem } from '@chakra-ui/react';

const UsuarioMenu = () => {
    return (
        <Menu isLazy>
            <MenuButton color={'branco'} fontFamily={'Poppins'} paddingLeft={'1.6em'} fontSize={'1.0em'} data-testid='menu-pautas'>
                Usuário
            </MenuButton>
            <MenuList fontFamily={'Poppins'} fontSize={'.9em'}>
                <MenuItem>
                    <Link to={'/login'}>
                        Login
                    </Link>
                </MenuItem>
            </MenuList>
        </Menu>
    );
};

export default UsuarioMenu;