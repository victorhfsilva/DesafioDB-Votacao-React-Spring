import App from "./App"
import { render, screen } from '@testing-library/react'
import '@testing-library/jest-dom'

describe('App', () => {
    it('should render the App component', () => {
        render(<App />)
        const titulo = screen.getByText('App');
        expect(titulo).toBeInTheDocument();
    })
})