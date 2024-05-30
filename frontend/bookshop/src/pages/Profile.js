import { Button, CssBaseline, Paper, Stack, ThemeProvider, Typography } from "@mui/material";
import { useAuth } from "../components/AuthProvider";
import { themeOptions } from "./Home";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";


export const Profile = () => {
    const auth = useAuth();
    const navigate = useNavigate();
    const [notification, setNotification] = useState([]);
    const [removeId, setRemoveId] = useState();
    const [isRemoved, setIsRemoved] = useState(false);
    const id = 1;

    useEffect(() => {
        fetch(`http://localhost:8080/api/notification/${id}`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setNotification(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, []);

    useEffect(() => {
        if (isRemoved) {
        fetch(`http://localhost:8080/api/notification/remove/${removeId}`, {method:"DELETE"})
        .then((data) => {
            console.log(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
        setIsRemoved(false);
    }
    }, [isRemoved, removeId]);

    const remove = (index, id) => {
        console.log(index);
        if (index === 0 && notification.length === 1) setNotification([]);
        else setNotification(notification.splice(index-1,1))
        setRemoveId(id);
        setIsRemoved(true);
    }


    return(
        <ThemeProvider theme={themeOptions}>
        <CssBaseline/>
        <Stack>
            <Paper sx={{margin:"20px", padding:"20px"}}>    
                <Stack direction={{xs:"column",sm:"row"}} sx={{display:"flex", justifyContent:"space-between"}}>
                    <div sx={{height:"100%"}}>
                        <Typography variant="h3">Witaj!</Typography>
                    </div>
                    <Stack direction={{xs:"column",sm:"row"}} sx={{height:"100%" }}>
                        <Button variant="contained" onClick={() => navigate("/")} sx={{height:"100%", margin:"10px"}}>Wróć na stronę główną</Button>
                        <Button variant="contained" onClick={auth.logout} sx={{height:"100%", margin:"10px"}}>Wyloguj się</Button>
                    </Stack>
                </Stack>
            </Paper>
            <Paper sx={{margin:"20px", padding:"20px", backgroundColor:"white"}}>
                <Typography variant="h3">Powiadomienia</Typography>
                {
                    notification.map((el,index) => (
                        <Paper sx={{padding:"10px"}}>
                            <Stack direction="row" display="flex" justifyContent="space-between">
                                <div>
                                    <Typography variant="h4">{el.title}</Typography>
                                    <Typography variant="h6">{el.body}</Typography>
                                </div>
                                <Button onClick={() => remove(index,el.id)}>X</Button>
                            </Stack>
                        </Paper>
                    ))
                }
            </Paper>
        </Stack>
        </ThemeProvider>
    );
}