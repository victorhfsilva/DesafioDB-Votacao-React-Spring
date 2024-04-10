import { Link, useNavigate } from 'react-router-dom';
import { Menu, MenuButton, MenuList, MenuItem } from '@chakra-ui/react';
import useAuthStore from '../../hooks/useAuthStore';
import logoutService from '../../services/logout.service';

const UsuarioMenu = () => {
    const isAutenticado = useAuthStore((state) => state.isAutenticado);
    const { setAutenticado, setAdmin } = useAuthStore();
    const navigate = useNavigate();

    const handleLogout = () => {
        logoutService(setAutenticado, setAdmin, navigate);
    } 
     
    return (
        <Menu isLazy>
            <MenuButton 
                color={'branco'} 
                fontFamily={'Poppins'} 
                paddingLeft={'1.6em'} 
                fontSize={'1.0em'} 
                data-testid='menu-pautas'>
                    Usuário
            </MenuButton>
            <MenuList fontFamily={'Poppins'} fontSize={'.9em'}>
                { !isAutenticado ? <MenuItem data-testid='menu-login'>
                    <Link to={'/login'}>
                        Login
                    </Link>
                </MenuItem> :
                <MenuItem data-testid='menu-logout' onClick={handleLogout}>
                        Logout
                </MenuItem>
                }
            </MenuList>
        </Menu>
    );
};

export default UsuarioMenu;