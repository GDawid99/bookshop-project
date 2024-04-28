import { Box, Container, CssBaseline, Paper, Stack, ThemeProvider, Typography, styled } from "@mui/material"
import { themeOptions } from "./Home"
import { MainHeader } from "../components/MainHeader";



const PaperFilter = styled(Paper)({
    margin:"5px",
    padding:"5px"
});


const PaperFoundBook = styled(Paper)({
    backgroundColor:"white",
    color:"black",
    margin:"5px",
    padding:"5px"
});

export const Search = (props) => {
    return(
        <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                <Container>
                    <MainHeader/>
                    <Stack direction="row" sx={{width:"100%"}}>
                        <Box sx={{width:"25%", display:{xs:"none", sm:"block"}}}>
                            <PaperFilter>
                                dsa
                            </PaperFilter>
                        </Box>
                        <Stack direction="column" sx={{width:{sm:"75%",xs:"100%"}}}>
                            {props.listOfFoundBooks === undefined || props.listOfFoundBooks.length === 0 ?
                                <Typography><br/>Nic nie znaleziono.</Typography>
                                : props.listOfFoundBooks.map((el) => {
                                    return <PaperFoundBook>{el}</PaperFoundBook>;
                                })
                            }
                        </Stack>
                    </Stack>
                </Container>
        </ThemeProvider>
    );
}