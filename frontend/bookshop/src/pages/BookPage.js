import { Box, Button, Container, CssBaseline, Paper, Rating, Stack, ThemeProvider, Typography } from "@mui/material"
import { MainHeader } from "../components/MainHeader"
import { themeOptions } from "./Home";
import { useLocation, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";



export const BookPage = () => {

    const [url] = useSearchParams();
    const [book, setBook] = useState();
    const location = useLocation();

    const title = url.get("title");


    useEffect(() => {
        fetch('http://localhost:8080/api/book/' + title + '/' + location.state)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setBook(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, []);


    if (book === undefined) return (
        <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                <Typography variant="h3" sx={{justifyContent:"center", position:"absolute", top:"50%", left:"50%", transform:"translate(-50%,-50%)"}}>Ładowanie...</Typography>
        </ThemeProvider>
    );
    else return(
            <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                <Container>
                    <MainHeader/>
                    <Stack direction={{xs:"column", sm:"row"}}>
                        <Paper sx={{margin:"15px", width:{xs:"auto",sm:"80%"}}}>
                            <Stack direction={{xs:"column", sm:"row"}}>
                                 <Paper sx={{width:{xs:"auto",sm:"70%"} , justifyContent:"center", padding:"auto", margin:"7px", backgroundColor:"white", border:"5px double #3d0200"}}>
                                     <img src={`/images/${book.imageUrl}`} alt={book.title} width="80%" style={{display:"block", margin:"40px auto"}}/>
                                 </Paper>
                                 <Stack sx={{width:{xs:"auto",sm:"70%"}, padding:"5px"}}>
                                     <Typography variant="h4">Autor:</Typography>
                                     <Typography>BLA BLA</Typography>
                                    <Typography variant="h4">Wydawnictwo:</Typography>
                                    <Typography>{book.publisher}</Typography>
                                    <Typography variant="h4">Gatunek:</Typography>
                                    <Typography>{book.genre}</Typography>
                                    <Typography variant="h4">Data wydania:</Typography>
                                    <Typography>{book.dateOfPublication}</Typography>
                                    <Typography variant="h4">Ocena:</Typography>
                                    <Rating value={book.rating/2} readOnly precision={0.5}></Rating>
                                </Stack>
                            </Stack>
                        </Paper>
                        <Paper sx={{margin:"15px", width:{xs:"auto",sm:"30%"}}}>
                            <Box sx={{padding:"10px"}}>
                                <Typography display="inline" sx={{fontSize:{xs:"20px", sm:"18px",md:"20px",lg:"22px"}, fontWeight:"bold"}}>Cena: </Typography>
                                <Typography display="inline" sx={{fontSize:{xs:"18px", sm:"16px",md:"18px",lg:"20px"},color:"purple"}}>693,69 zł</Typography>
                                {
                                    //JESLI ZALOGOWANY
                                    <Button size="large" variant="contained" sx={{margin:"10px 0", width:"100%"}}>Dodaj do koszyka</Button>
                                }
                                <Typography>Sprawdź także inną książkę autora:</Typography>
                            </Box>
                        </Paper>
                    </Stack>
                </Container>
            </ThemeProvider>
    );
}