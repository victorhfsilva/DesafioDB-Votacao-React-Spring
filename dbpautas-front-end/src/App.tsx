import PageRouter from "./router/PageRouter"
import defaultTheme from "./themes/default"
import { CSSReset, ChakraProvider } from "@chakra-ui/react"

function App() {

  return (
      <ChakraProvider theme={defaultTheme}>
        <CSSReset />
        <PageRouter />
      </ChakraProvider>
  )
}

export default App
