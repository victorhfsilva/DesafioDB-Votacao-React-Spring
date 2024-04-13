import { Link } from 'react-router-dom';
import { Menu, MenuButton, MenuList, MenuItem } from '@chakra-ui/react';

const PautasMenu = () => {
    return (
        <Menu isLazy>
            <MenuButton 
                color={'branco'} 
                fontFamily={'Poppins'} 
                paddingLeft={'1.6em'} 
                fontSize={'1.0em'} 
                data-testid='menu-pautas'>
                    Pautas
            </MenuButton>
            <MenuList fontFamily={'Poppins'} fontSize={'.9em'}>
                <MenuItem data-testid='menu-pautas-finalizadas'>
                    <Link to={'/pautasFinalizadas'}>
                        Pautas Finalizadas
                    </Link>
                </MenuItem>
            </MenuList>
        </Menu>
    );
};

export default PautasMenu;