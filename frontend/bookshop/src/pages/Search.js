import { Box, Button, Container, CssBaseline, Paper, Rating, Stack, ThemeProvider, Typography, styled } from "@mui/material"
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
                                    return <PaperFoundBook>
                                        <Typography variant={props.title.length < 20 ? "h5" : "h6"} sx={{display: 'flex', justifyContent:"center", textAlign:"center"}}>{props.title}</Typography>
                                        <img src={props.imageSrc} alt={props.title} style={{width:"100%"}}></img>
                                        <Typography variant="h6" sx={{fontSize:"9px"}}>Autor: {props.author}</Typography>
                                        <Typography variant="h6" sx={{fontSize:"9px"}}>Wydawnictwo: {props.publisher}</Typography>
                                        <Typography variant="h6" display="inline" sx={{fontSize:"9px"}}>Cena: </Typography>
                                        <Typography variant="h6" display="inline" sx={{fontSize:"9px", color:"purple"}}>{props.price} zł</Typography>
                                        <Typography>
                                            <Rating value={props.rating/2} readOnly precision={0.5} size="small"></Rating>
                                        </Typography>
                                        <Typography>
                                            <Button variant="h5" display="flex" sx={{width: {xs:"20%", sm:"60%", md: "40%"}}}>SPRAWDŹ</Button>
                                        </Typography>
                                    </PaperFoundBook>;
                                })
                            }
                        </Stack>
                    </Stack>
                </Container>
        </ThemeProvider>
    );
}