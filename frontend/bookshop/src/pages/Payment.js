import { ThemeProvider } from "@emotion/react";
import { Button, CssBaseline } from "@mui/material";
import { themeOptions } from "./Home";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


export const Payment = () => {
    
    const navigate = useNavigate();

    const payment = async(data) => {
        try {
            await fetch('http://localhost:8080/api/cart/add', {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify(data)
            });
        }
        catch (error) {
            console.error('Wysłanie nieudane. ', error.response ? error.response.data : error.message);
        }
    }

    const sendData = () => {
        const cart = JSON.parse(localStorage.getItem("cart")).map(el => el.element);
        console.log(cart);
        const data = {
            user_id: 1,
            elements: cart
        };
        payment(data);
        console.log(data);
        localStorage.removeItem("cart");
        navigate("/");
    }

    return(
        <ThemeProvider theme={themeOptions}>
        <CssBaseline/>
            <div style={{display:"flex", justifyContent:"center", margin:"300px 0"}}>
                <Button variant="contained" onClick={sendData}>Kup</Button>
            </div>
        </ThemeProvider>
    );
}