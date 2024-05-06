import { Box, Button, Container, CssBaseline, Paper, Rating, Stack, ThemeProvider, Typography, styled } from "@mui/material"
import { themeOptions } from "./Home"
import { MainHeader } from "../components/MainHeader";
import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Filter } from "../components/Filter";



const PaperFilter = styled(Paper)({
    margin:"5px",
    padding:"5px"
});


const PaperFoundBook = styled(Paper)({
    backgroundColor:"white",
    color:"black",
    margin:"5px 30px",
    padding:"5px"
});

export const Search = (props) => {

    
    const [book, setBook] = useState("");
    
    const [params]= useSearchParams();

    const title = params.get('title') || "";
    const id = params.get("id");
    const size = params.get("size");
    const priceFrom = parseFloat(params.get('priceFrom')) || 0;
    const priceTo = parseFloat(params.get('priceTo')) || Number.MAX_VALUE;
    const publisher = params.getAll('publisher');
    const rate = params.get('rate');
    const genre = params.getAll('genre');
    

    const navigate = useNavigate();
   

    useEffect(() => {
        if (title !== "") fetch('http://localhost:8080/api/book/page?title=' + title + '&id=' + id + '&size=' + size)
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
                                
                                <Filter book={book}/>
                            </PaperFilter>
                        </Box>
                        <Stack direction="column" sx={{width:{sm:"75%",xs:"100%"}}}>
                            {book === undefined || book.length === 0 ?
                                <Typography><br/>Nic nie znaleziono.</Typography>
                                : book.filter((el) => {
                                    return (parseFloat(el.price) >= priceFrom && parseFloat(el.price) <= priceTo);
                                }).filter((el) => {
                                    return publisher.length !== 0 ? publisher.includes(el.publisher) : true;
                                }).filter((el) => {
                                    return genre.length !== 0 ? genre.includes(el.genre) : true;
                                }).map((el) => {
                                    return <PaperFoundBook>
                                        <Stack direction={{xs:"column", sm:"row"}} sx={{width:"100%", justifyContent:"space-between"}}>
                                            <Box sx={{width:{xs:"100%" ,sm:"70%"}, maxWidth:{xs:"100%" ,sm:"70%"}}}>
                                                <Typography align="center" sx={{fontSize:{xs:"20px",sm:"30px", md:"35px", lg:"40px", xl:"50px"}}}>{el.title}</Typography>
                                                <Typography align="left" sx={{fontSize:{xs:"9px", sm:"14px",md:"20px", lg:"28px", xl:"40px"}}}>Autor: {el.author.firstname + " " + el.author.lastname}</Typography>
                                                <Typography align="left" sx={{fontSize:{xs:"9px", sm:"14px",md:"20px", lg:"28px", xl:"40px"}}}>Wydawnictwo: {el.publisher}</Typography>
                                                <Typography align="left" display="inline" sx={{fontSize:{xs:"9px", sm:"14px",md:"20px", lg:"28px", xl:"40px"}}}>Cena: </Typography>
                                                <Typography align="left" display="inline" sx={{fontSize:{xs:"9px", sm:"14px",md:"20px", lg:"28px", xl:"40px"}, color:"purple"}}>{el.price} zł</Typography>
                                                <Typography>
                                                    <Rating value={el.rating/2} readOnly precision={0.5} sx={{fontSize:{xs:"15px", md:"27px", xl:"40px"}}}></Rating>
                                                </Typography>
                                            </Box>
                                            <img className="bookImage" src={`/images/${el.imageUrl}`} alt={el.title}></img>
                                        </Stack>
                                    </PaperFoundBook>;
                                })
                            }
                        </Stack>
                    </Stack>
                </Container>
        </ThemeProvider>
    );
}