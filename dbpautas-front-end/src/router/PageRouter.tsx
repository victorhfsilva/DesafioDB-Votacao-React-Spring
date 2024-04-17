import { BrowserRouter, Route, Routes } from "react-router-dom"
import Header from "../components/Header";
import NotFound from "../pages/NotFound";
import Login from "../pages/Login";
import AbrirPauta from "../pages/AbrirPauta";
import CadastrarPauta from "../pages/CadastrarPauta";
import PautasAbertas from "../pages/PautasAbertas";
import PautasFinalizadas from "../pages/PautasFinalizadas";
import RegistrarUsuario from "../pages/RegistrarUsuario";
import UsuarioRoute from "./UsuarioRoute";
import AdminRoute from "./AdminRoute";
import { AxiosInterceptor } from "../libs/api";

const PageRouter = () => {
    return (
        <BrowserRouter>
            <AxiosInterceptor>
            <Header/>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="*" element={<NotFound />} />

                <Route path="/" element={
                    <UsuarioRoute>
                        <PautasAbertas />
                    </UsuarioRoute>
                } />
                <Route path="/pautasFinalizadas" element={
                    <UsuarioRoute>
                        <PautasFinalizadas />
                    </UsuarioRoute>
                } />
                <Route path="/abrirPauta" element={
                    <AdminRoute>
                        <AbrirPauta />
                    </AdminRoute>
                } />
                <Route path="/cadastrarPauta" element={
                    <AdminRoute>
                        <CadastrarPauta />
                    </AdminRoute>
                } />
                <Route path="/registrarUsuario" element={
                    <AdminRoute>
                        <RegistrarUsuario />
                    </AdminRoute>
                } />


            </Routes>
            </AxiosInterceptor>
        </BrowserRouter>
    )
}

export default PageRouter;