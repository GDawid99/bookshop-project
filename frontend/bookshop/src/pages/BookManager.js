import { Button, CssBaseline, Paper, Stack, ThemeProvider, Typography } from "@mui/material"
import { themeOptions } from "./Home"
import { Link } from "react-router-dom"
import { useState } from "react"

export const BookManager = () => {
    
    const [id, setId] = useState();
    
    const createArticle = async() => {
        try {
            const authorResponse = await fetch(`http://localhost:8080/api/author/get`, {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                firstname: document.getElementById("firstname").value,
                lastname: document.getElementById("lastname").value,
              }),
            });
        
            const aid = await authorResponse.text();
        
            const bookResponse = await fetch(`http://localhost:8080/api/book/add/${aid}`, {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                title: document.getElementById("title").value,
                publisher: document.getElementById("publisher").value,
                dateOfPublication: document.getElementById("date").value,
                genre: document.getElementById("genre").value,
                price: document.getElementById("price").value,
                quantity: document.getElementById("quantity").value,
                url: document.getElementById("url").value,
              }),
            });
        } catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
          }
    }   


    return (
    <ThemeProvider theme={themeOptions}>
        <CssBaseline/>
            <Paper sx={{margin:"30px 10%"}}>
                <Stack sx={{margin:"30px auto"}}>
                    <Typography variant="h4">Dane:</Typography>
                    <Typography>Tytuł:</Typography><input id="title"></input>
                    <Typography>Imię autora:</Typography><input id="firstname"></input>
                    <Typography>Nazwisko autora:</Typography><input id="lastname"></input>
                    <Typography>Wydawnictwo:</Typography><input id="publisher"></input>
                    <Typography>Data wydania:</Typography><input id="date"></input>
                    <Typography>Gatunek:</Typography><input id="genre"></input>
                    <Typography>Cena:</Typography><input id="price"></input>
                    <Typography>Liczba sztuk:</Typography><input id="quantity"></input>
                    <Typography>Plik obrazu:</Typography><input id="url"></input>
                    <Button variant="contained" sx={{margin:"10px 0"}} onClick={createArticle}>Publikuj</Button>
                </Stack>
            </Paper>
    </ThemeProvider>
    );
}