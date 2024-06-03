import { Box, Button, Container, CssBaseline, Paper, Rating, Stack, ThemeProvider, Typography } from "@mui/material"
import { MainHeader } from "../components/MainHeader"
import { themeOptions } from "./Home";
import { useLocation, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useAuth } from "../components/AuthProvider";
import { jwtDecode } from "jwt-decode";
import { Comment } from "../components/Article";



export const BookPage = () => {
    const user = useAuth();

    const [url] = useSearchParams();
    const [book, setBook] = useState();
    const [cart, setCart] = useState(JSON.parse(localStorage.getItem("cart") || "[]"));
    const [rating, setRating] = useState([]);
    const [newRating, setNewRating] = useState([]);
    const [users, setUsers] = useState([]);
    const [isAdded, setIsAdded] = useState(false);
    const location = useLocation();

    const title = url.get("title");


    useEffect(() => {
        fetch('http://localhost:8080/api/book/' + title + '/' + location.state)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setBook(data);
            const updatedCart = JSON.parse(localStorage.getItem("cart") || "[]");
            setIsAdded(updatedCart.some(e => e.element.book_id === data.book_id));
            console.log(isAdded);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, []);

    const getRatings = async() => {
        if (!book) return;
        await fetch(`http://localhost:8080/api/rating/all/${book.book_id}`)
        .then((response) => response.json())
        .then((data) => {
            setRating(data);
            console.log(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }

    useEffect(() => {
        getRatings();
    }, [book]);


    useEffect((data) => {
        const arr = rating.map(el => el.userId);
        const tmp = [];
        setUsers([]);
        try {
            fetch(`http://localhost:8080/api/user/get`, {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify({
                    ids:arr
                })
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                arr.map((el, index) => setUsers(prev => [...prev,{
                    name:data[index],
                    id:el
                }]));
            });
            console.log(data);
        }
        catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
        }
    },[rating])

    const sendRating = async() => {
        try {
            await fetch(`http://localhost:8080/api/rating/add/${book.book_id}`, {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify({
                    body: document.getElementById("commentText").value,
                    rating: newRating,
                    userId: jwtDecode(user.token).id
                })
            });
            getRatings();
        }
        catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
        }
    }


    const addToCart = () => {        
        const updatedCart = JSON.parse(localStorage.getItem("cart") || "[]");
        console.log(updatedCart);
        const tmpMaxId = updatedCart.length === 0 ? 1 : Math.max(...updatedCart.map(el => el.id)) + 1;
        updatedCart.push({
            id: tmpMaxId,
            element:{
                book_id: book.book_id,
                title: book.title,
                price: book.price,
                img: book.imageUrl,
                quantity: 1
            }
        });
        setCart(updatedCart)
        setIsAdded(true);
        localStorage.setItem("cart", JSON.stringify(updatedCart))
        
    }


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
                                     <Typography>{book.author.firstname + " " + book.author.lastname}</Typography>
                                    <Typography variant="h4">Wydawnictwo:</Typography>
                                    <Typography>{book.publisher}</Typography>
                                    <Typography variant="h4">Gatunek:</Typography>
                                    <Typography>{book.genre}</Typography>
                                    <Typography variant="h4">Data wydania:</Typography>
                                    <Typography>{book.dateOfPublication}</Typography>
                                    <Typography variant="h4">Ocena:</Typography>
                                    <Rating value={book.rating} readOnly precision={0.5} max={10}></Rating>
                                </Stack>
                            </Stack>
                        </Paper>
                        <Paper sx={{margin:"15px", width:{xs:"auto",sm:"30%"}}}>
                            <Box sx={{padding:"10px"}}>
                                <Typography display="inline" sx={{fontSize:{xs:"20px", sm:"18px",md:"20px",lg:"22px"}, fontWeight:"bold"}}>Cena: </Typography>
                                <Typography display="inline" sx={{fontSize:{xs:"18px", sm:"16px",md:"18px",lg:"20px"},color:"purple"}}>{book.price} zł</Typography>
                                {
                                    user.token &&
                                    <Button size="large" variant="contained" disabled={isAdded} sx={{margin:"10px 0", width:"100%"}} onClick={addToCart}>Dodaj do koszyka</Button>
                                }
                                <Typography>Sprawdź także inną książkę autora:</Typography>
                            </Box>
                        </Paper>
                    </Stack>
                    <Paper sx={{margin:"15px"}}>
                        <Typography variant="h5" sx={{margin:"10px", padding:"10px 0"}}>Oceny czytelników:</Typography>
                            <Stack>
                                {
                                    user.token &&
                                    <Stack>
                                        <Typography variant="h5" sx={{margin:"10px"}}>Oceń:</Typography>
                                        <Rating value={newRating} onClick={e => setNewRating(e.target.value)} max={10} sx={{margin:"10px"}}></Rating>
                                        <textarea id="commentText" maxLength="255" placeholder="Limit znaków: 255" style={{maxWidth:"60%", maxHeight:"60px", backgroundColor:"white"}}></textarea>
                                        <Button variant="contained" sx={{width:"10%", margin:"10px"}} onClick={sendRating}>Wyślij</Button>
                                    </Stack>
                                }   
                                <Typography variant="h5" sx={{margin:"10px"}}>Komentarze:</Typography>
                                {
                                    rating.map(el => {
                                        let name = users.filter(x => x.id === el.userId);
                                        if (name.length > 0)
                                        return ( 
                                            <Stack>
                                                <Rating value={el.rating} readOnly max={10} sx={{margin:"10px"}}></Rating>
                                                <Comment author={name[0].name} body={el.body}/>
                                            </Stack>
                                        );
                                    })
                                }
                            </Stack>
                    </Paper>
                </Container>
            </ThemeProvider>
    );
}