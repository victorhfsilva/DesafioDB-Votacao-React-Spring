import { Navigate } from "react-router-dom";
import useAuthStore from "../hooks/useAuthStore";
import { ReactNode } from "react";

interface AdminRouteProps {
    children: ReactNode;
  }
  
const AdminRoute = ({ children }: AdminRouteProps) => {
    const isAuthenticated = useAuthStore((state) => state.isAutenticado);
    const isAdmin = useAuthStore((state) => state.isAdmin);

    if (isAuthenticated && isAdmin) {
      return children
    }
      
    return <Navigate to="/acessoNegado" />
}

export default AdminRoute;