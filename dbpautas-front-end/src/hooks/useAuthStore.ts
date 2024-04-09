import {create} from 'zustand';

type AuthState = {
  isAutenticado: boolean;
  isAdmin: boolean;
  setAutenticado: (isAutenticado: boolean) => void;
  setAdmin: (isAdmin: boolean) => void;
};

const useAuthStore = create<AuthState>((set) => ({
    isAutenticado: false,
    isAdmin: false,
    setAutenticado: (isAutenticado) => set({ isAutenticado: isAutenticado }),
    setAdmin: (isAdmin) => set({ isAdmin: isAdmin })
}));

export default useAuthStore;