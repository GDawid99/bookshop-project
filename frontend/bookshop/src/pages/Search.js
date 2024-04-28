import { Container, CssBaseline, ThemeProvider } from "@mui/material"
import { themeOptions } from "./Home"
import { MainHeader } from "../components/MainHeader";

export const Search = () => {
    return(
        <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                <Container>
                    <MainHeader/>
                </Container>
        </ThemeProvider>
    );
}