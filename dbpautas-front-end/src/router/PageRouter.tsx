import { BrowserRouter, Route, Routes } from "react-router-dom"
import Header from "../components/Header";
import Home from "../pages/Home";
import NotFound from "../pages/NotFound";
import Login from "../pages/Login";
import AbrirPauta from "../pages/AbrirPauta";
import CadastrarPauta from "../pages/CadastrarPauta";
import PautasAbertas from "../pages/PautasAbertas";
import PautasFinalizadas from "../pages/PautasFinalizadas";
import RegistrarUsuario from "../pages/RegistrarUsuario";

const PageRouter = () => {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/abrirPauta" element={<AbrirPauta />} />
                <Route path="/cadastrarPauta" element={<CadastrarPauta />} />
                <Route path="/pautasAbertas" element={<PautasAbertas />} />
                <Route path="/pautasFinalizadas" element={<PautasFinalizadas />} />
                <Route path="/registrarUsuario" element={<RegistrarUsuario />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
        </BrowserRouter>
    )
}

export default PageRouter;