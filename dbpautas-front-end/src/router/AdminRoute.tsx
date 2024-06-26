import { Navigate } from "react-router-dom";
import useAuthStore from "../hooks/useAuthStore";
import { ReactNode } from "react";

interface AdminRouteProps {
    children: ReactNode;
  }
  
const AdminRoute = ({ children }: AdminRouteProps) => {
    const isAutenticado = useAuthStore((state) => state.isAutenticado);
    const isAdmin = useAuthStore((state) => state.isAdmin);

    if (isAutenticado && isAdmin) {
      return children
    }
      
    return <Navigate to="/login" />
}

export default AdminRoute;