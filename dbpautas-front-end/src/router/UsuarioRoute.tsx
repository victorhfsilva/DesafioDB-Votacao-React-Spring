import { Navigate } from "react-router-dom";
import useAuthStore from "../hooks/useAuthStore";
import { ReactNode } from "react";

interface UsuarioRouteProps {
    children: ReactNode;
  }
  
const UsuarioRoute = ({ children }: UsuarioRouteProps) => {
    const isAuthenticated = useAuthStore((state) => state.isAutenticado);
        
    if (isAuthenticated ) {
      return children
    }
      
    return <Navigate to="/login" />
}

export default UsuarioRoute;