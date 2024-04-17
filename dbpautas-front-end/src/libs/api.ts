import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import { useEffect } from "react";
import useHandleSessaoExpirada from "../hooks/useHandleSessaoExpirada";

const api = axios.create({
  baseURL: "http://localhost:8080"
})

const AxiosInterceptor = ({children}: {children: React.ReactNode}) => {

  const handleSessaoExpirada = useHandleSessaoExpirada();

  useEffect(() => {
    
    const reqConfigInterceptor = (config: AxiosRequestConfig) => {
      config.headers = config.headers || {};
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
      if (axios.isAxiosError(error) && error.response && error.response.status === 403) {
        handleSessaoExpirada();
      }
      return Promise.reject(error);
    }

  
    const reqInterceptorID = api.interceptors.request.use(reqConfigInterceptor, reqErrInterceptor);
    const resInterceptorID = api.interceptors.response.use(resResInterceptor, resErrInterceptor);

    return () => {
      api.interceptors.request.eject(reqInterceptorID);
      api.interceptors.response.eject(resInterceptorID);
    }
  }, [useHandleSessaoExpirada])

  return children;
}

export default api;
export {AxiosInterceptor};