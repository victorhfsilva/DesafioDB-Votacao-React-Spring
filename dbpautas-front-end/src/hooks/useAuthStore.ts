import {create} from 'zustand';

type AuthState = {
  isAutenticado: boolean;
  isAdmin: boolean;
  setAutenticado: () => void;
  setDesautenticado: () => void;
  setAdmin: () => void;
  setNonAdmin: () => void;
};

const useAuthStore = create<AuthState>((set) => ({
    isAutenticado: false,
    isAdmin: false,
    setAutenticado: () => set({ isAutenticado: true }),
    setDesautenticado: () => set({ isAutenticado: false }),
    setAdmin: () => set({ isAdmin: true }),
    setNonAdmin: () => set({ isAdmin: false })
}));

export default useAuthStore;