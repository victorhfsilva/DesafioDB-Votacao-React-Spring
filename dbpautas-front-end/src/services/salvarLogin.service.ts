import LoginRespostaModel from "../models/LoginRespostaModel";

const salvarLoginService = (data: LoginRespostaModel,
                            setAutenticado: (isAutenticado: boolean) => void,
                            setAdmin: (isAdmin: boolean) => void,
                            navigate: (path: string) => void) => {
                                localStorage.setItem('token', data.token);
                                setAutenticado(true);
                                if(data.papel === "ADMIN"){
                                    setAdmin(true);
                                } else {
                                    setAdmin(false);
                                }
                                navigate('/');
                            }

export default salvarLoginService;