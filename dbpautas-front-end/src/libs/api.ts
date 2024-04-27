import axios, { AxiosError, AxiosResponse, InternalAxiosRequestConfig } from "axios";
import { useEffect } from "react";
import useHandleSessaoExpirada from "../hooks/useHandleSessaoExpirada";
import useHandleExcecao from "../hooks/useHandleExcecao";

const api = axios.create({
  baseURL: "https://dbpautas-spring-back-end.onrender.com"
})

const AxiosInterceptor = ({children}: {children: React.ReactNode}) => {

  const handleSessaoExpirada = useHandleSessaoExpirada();
  const handleExcecao = useHandleExcecao(); 

  useEffect(() => {
    
    const reqConfigInterceptor = (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
      const token = localStorage.getItem('token');
      if (token) {
          config.headers['Authorization'] = `Bearer ${token}`;
      }
      return config;
    };

    const reqErrInterceptor = (error: AxiosError) => {
      return Promise.reject(error);
    };

    const resResInterceptor = (response: AxiosResponse) => {
      return response;
    }

    const resErrInterceptor = (error: AxiosError) => {
      if (error.response) {
        switch (error.response.status) {
          case 403:
            handleSessaoExpirada();
            break;
          case 400:
            handleExcecao("Requisição Inválida", error.response.data as string)
            break;
          case 409:
            handleExcecao("Conflito", error.response.data as string)
            break;
          case 404:
            handleExcecao("Recurso não encontrado", error.response.data as string)
            break;
          case 503:
            handleExcecao("Serviço Indisponível", error.response.data as string)
            break;
          default:
            handleExcecao("Falha na requisição", "Houve uma falha nesta requisição.");
        }
      }
      return Promise.reject(error);
    }

  
    const reqInterceptorID = api.interceptors.request.use(reqConfigInterceptor, reqErrInterceptor);
    const resInterceptorID = api.interceptors.response.use(resResInterceptor, resErrInterceptor);

    return () => {
      api.interceptors.request.eject(reqInterceptorID);
      api.interceptors.response.eject(resInterceptorID);
    }
  }, [handleSessaoExpirada, handleExcecao])

  return children;
}

export default api;
export {AxiosInterceptor};