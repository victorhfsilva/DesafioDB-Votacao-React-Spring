import { createContext, useContext } from "react";

const SessionContext = createContext({
  handleSessionExpired: () => {}
});

export const useSessionContext = () => useContext(SessionContext);