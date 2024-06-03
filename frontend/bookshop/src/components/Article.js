import { Button, Container, CssBaseline, Paper, Stack, TextField, ThemeProvider, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { Link, useSearchParams } from "react-router-dom";
import { themeOptions } from "../pages/Home";
import { useAuth } from "./AuthProvider";
import { MainHeader } from "./MainHeader";
import { jwtDecode } from "jwt-decode";

export const Article = () => {
    const [article, setArticle] = useState([]);
    const [comment, setComment] = useState([]);
    const [params] = useSearchParams();
    const id = params.get("id");
    const user = useAuth();

    useEffect(() => {
        fetch(`http://localhost:8080/api/article/${id}`)
        .then((response) => response.json())
        .then((data) => {
            setArticle(data);
            console.log(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, [id]);


    const getComments = () => {
        fetch(`http://localhost:8080/api/comment/all/${id}`)
        .then((response) => response.json())
        .then((data) => {
            setComment(data);
            console.log(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }

    useEffect(() => {
        getComments();
    }, [id]);

    const sendComment = async() => {
        try {
            await fetch(`http://localhost:8080/api/comment/create/${id}/${jwtDecode(user.token).id}`, {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify({body: document.getElementById("commentText").value})
            });
            getComments();
        }
        catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
        }
    }
    
console.log(comment);

    return (
        <ThemeProvider theme={themeOptions}>
            <CssBaseline/>
            <Container>
                <MainHeader/>
                <Paper sx={{margin:"20px", padding:"10px"}}>
                    <Typography variant="h3" sx={{justifyContent:"center", display:"flex"}}>{article.title}</Typography>
                    <Typography variant="h6" sx={{justifyContent:"center", display:"flex"}}>{article.user !== undefined && "Autor: " + article.user.firstname + " " + article.user.lastname}</Typography>
                    <Typography variant="h6" sx={{justifyContent:"center", display:"flex"}}>{article.dateOfPublication}</Typography>
                    <Paper sx={{backgroundColor:"white", margin:"10px", padding:"20px"}}>
                        <Typography variant="h5" textAlign="justify" lineHeight="40px">&emsp;&emsp;{article.body}</Typography>
                    </Paper>
                </Paper>
                
                <Paper sx={{margin:"20px", padding:"10px"}}>
                    <Stack>
                        {
                            user.token &&
                            <Stack>
                                <Typography variant="h5" sx={{margin:"10px"}}>Napisz komentarz:</Typography>
                                <textarea id="commentText" maxLength="400" placeholder="Limit znaków: 400" style={{maxWidth:"60%", maxHeight:"100px", backgroundColor:"white"}}></textarea>
                                <Button variant="contained" sx={{width:"10%", margin:"10px"}} onClick={sendComment}>Wyślij</Button>
                            </Stack>
                        }   
                        <Typography variant="h5" sx={{margin:"10px"}}>Komentarze:</Typography>
                        {
                            comment.map(el => {
                                return <Comment author={el.user.firstname} dateOfPublication={el.dateOfPublication} body={el.body}/>
                            })
                        }
                    </Stack>
                </Paper>
                <Link to="/">
                    <Button variant="contained" sx={{position:"sticky", bottom:"5%", border:"1px solid black"}}>Wróć na stronę główną</Button>
                </Link>
                </Container>
        </ThemeProvider>
    );
}

export const Comment = (props) => {

    return(
        <Paper style={{margin:"10px", backgroundColor:"white"}}>
            <div style={{padding:"8px 20px"}}>
                <Typography variant="h6">Autor: {props.author}</Typography>
                <Typography variant="h6">{props.dateOfPublication}</Typography>
            </div>
            <Typography sx={{border:"1px solid black", padding:"10px", margin:"8px 20px"}}>{props.body}</Typography>
        </Paper>
    )
}