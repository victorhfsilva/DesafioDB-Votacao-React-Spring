import { BrowserRouter, Route, Routes } from "react-router-dom"
import Menu from "../components/Menu";
import Home from "../pages/Home";

const PageRouter = () => {
    return (
        <BrowserRouter>
            <Menu/>
            <Routes>
                <Route path="/" element={<Home />} />
            </Routes>
        </BrowserRouter>
    )
}

export default PageRouter;