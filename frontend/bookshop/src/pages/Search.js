import { Box, Button, Container, CssBaseline, Paper, Rating, Stack, ThemeProvider, Typography, styled } from "@mui/material"
import { themeOptions } from "./Home"
import { MainHeader } from "../components/MainHeader";
import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";



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

    
    const [book, setBook] = useState("");
    
    const foundBook= useSearchParams();

    const title = foundBook[0].get('title');
    const id = foundBook[0].get("id");
    const size = foundBook[0].get("size");


    const navigate = useNavigate();
   

    useEffect(() => {
        fetch('http://localhost:8080/api/book/page?title=' + title + '&id=' + id + '&size=' + size)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setBook(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, []);

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
                            {book === undefined || book.length === 0 ?
                                <Typography><br/>Nic nie znaleziono.</Typography>
                                : book.map((el) => {
                                    return <PaperFoundBook>
                                        <Typography variant={el.title.length < 20 ? "h5" : "h6"} sx={{display: 'flex', justifyContent:"center", textAlign:"center"}}>{el.title}</Typography>
                                        <img src={el.imageSrc} alt={el.title} style={{width:"100%"}}></img>
                                        <Typography variant="h6" sx={{fontSize:"9px"}}>Autor: {el.author.firstname + " " + el.author.lastname}</Typography>
                                        <Typography variant="h6" sx={{fontSize:"9px"}}>Wydawnictwo: {el.publisher}</Typography>
                                        <Typography variant="h6" display="inline" sx={{fontSize:"9px"}}>Cena: </Typography>
                                        <Typography variant="h6" display="inline" sx={{fontSize:"9px", color:"purple"}}>{el.price} zł</Typography>
                                        <Typography>
                                            <Rating value={el.rating/2} readOnly precision={0.5} size="small"></Rating>
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