import { Container, CssBaseline, ThemeProvider } from "@mui/material"
import { MainHeader } from "../components/MainHeader"
import { themeOptions } from "./Home";
import { useSearchParams } from "react-router-dom";



export const BookPage = () => {

    const [book, setBook] = useSearchParams();

    

    return(
        <>
            <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                <Container>
                    <MainHeader/>
                </Container>
            </ThemeProvider>
        </>
    );
}