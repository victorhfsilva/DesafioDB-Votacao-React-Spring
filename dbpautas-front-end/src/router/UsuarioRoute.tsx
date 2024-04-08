import { Navigate } from "react-router-dom";
import useAuthStore from "../hooks/useAuthStore";
import { ReactNode } from "react";

interface UsuarioRouteProps {
    children: ReactNode;
  }
  
const UsuarioRoute = ({ children }: UsuarioRouteProps) => {
    const isAutenticado = useAuthStore((state) => state.isAutenticado);
        
    if (isAutenticado) {
      return children
    }
      
    return <Navigate to="/login" />
}

export default UsuarioRoute;