import App from "./App"
import { render } from '@testing-library/react'

describe('App', () => {
    it('should render the App component', () => {
        render(<App />)
        expect(1).toBe(1)
    })
})