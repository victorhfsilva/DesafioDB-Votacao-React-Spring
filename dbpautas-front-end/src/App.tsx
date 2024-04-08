import PageRouter from "./router/PageRouter"
import { ThemeProvider } from "styled-components"
import defaultTheme from "./themes/default"

function App() {

  return (
    <ThemeProvider theme={defaultTheme}>
      <PageRouter />
    </ThemeProvider>
  )
}

export default App
