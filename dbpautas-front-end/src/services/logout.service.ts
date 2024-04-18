const logoutService = (setAutenticado: (isAutenticado: boolean) => void,
                setAdmin: (isAdmin: boolean) => void,
                navigate: (path: string) => void) => {
    setAutenticado(false);
    setAdmin(false);
    localStorage.removeItem('token');
    navigate('/login');
}

export default logoutService;