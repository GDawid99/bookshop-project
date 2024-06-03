import { Button, Container, CssBaseline, Stack, ThemeProvider, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import { themeOptions } from "./Home";
import { useAuth } from "../components/AuthProvider";
import { jwtDecode } from "jwt-decode";



export const ArticleCreator = () => {
    const moderator = useAuth();
    
    const createArticle = async(data) => {
        try {
            await fetch(`http://localhost:8080/api/article/create/${jwtDecode(moderator.token).id}`, {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify({
                    title: document.getElementById("titleText").value,
                    body: document.getElementById("bodyText").value
                })
            });
        }
        catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
        }
    }

    return(
        <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
                    <Stack sx={{width:"60%", margin:"30px auto"}}>
                        <Typography variant="h4" sx={{display:"flex", justifyContent:"center"}}>Utwórz artykuł:</Typography>
                        <div>
                            <Typography>Tytuł: </Typography>
                            <input id="titleText" style={{width:"100%"}}></input>
                        </div>
                        <div>
                            <Typography>Tekst: </Typography>
                            <textarea id="bodyText"  style={{resize:"vertical", width:"100%", minHeight:"200px"}}></textarea>
                        </div>
                        <Link to="/">
                            <Button variant="contained" onClick={createArticle}  sx={{width:"100%"}}>Publikuj</Button>
                        </Link>
                    </Stack>
        </ThemeProvider>
    );
}