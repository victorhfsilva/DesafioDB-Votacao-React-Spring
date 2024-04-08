import { Link } from 'react-router-dom';
import { Menu, MenuButton, MenuList, MenuItem } from '@chakra-ui/react';

const AdminMenu = () => {
    return (
        <Menu isLazy>
            <MenuButton color={'branco'} fontFamily={'Poppins'} paddingLeft={'1.6em'} fontSize={'1.0em'} data-testid='menu-pautas'>
                Admin
            </MenuButton>
            <MenuList fontFamily={'Poppins'} fontSize={'.9em'}>
                <MenuItem>
                    <Link to={'/registrarUsuario'}>
                        Registrar Usuário
                    </Link>
                </MenuItem>
                <MenuItem>
                    <Link to={'/cadastrarPauta'}>
                        Cadastrar Pauta
                    </Link>
                </MenuItem>
                <MenuItem>
                    <Link to={'/abrirPauta'}>
                        Abrir Pauta
                    </Link>
                </MenuItem>
            </MenuList>
        </Menu>
    );
};

export default AdminMenu;