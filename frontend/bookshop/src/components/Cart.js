import { Box, Button, Paper, Stack, Typography } from "@mui/material"
import { useCallback, useState } from "react";
import DeleteIcon from '@mui/icons-material/Delete';
import { useLocation } from "react-router-dom";

export const Cart = ({setShowCart}) => {
    const cartObject = JSON.parse(localStorage.getItem("cart") || "[]");
    const [optionsOn, setOptionsOn] = useState("accountBackgroundOn");
    const [disablePaper, setDisablePaper] = useState(true);
    const [cartElements, setCartElements] = useState(cartObject);
    const [finalPrice, setFinalPrice] = useState(() => {
        let sum = 0;
        cartObject.forEach(el => {
            sum += Number.parseFloat(el.element.price)*Number.parseFloat(el.element.amount);
        });
        return sum.toFixed(2);
    });
    const location = useLocation();


    const showOptions = useCallback((e) => {
        setDisablePaper(false);
        setOptionsOn("accountBackgroundOff")
        setTimeout(() => {setShowCart(false)}, 200);
    }, [setShowCart])

    const setPrice = (e, id) => {
        const cart = JSON.parse(localStorage.getItem("cart") || "[]");
        let element = cart.find(el => el.id === id);
        if (element) {
            element.element.amount = e.target.value;
        }
        setCartElements(cart);
        setFinalPrice(() => {
            let sum = 0;
            cart.forEach(el => {
                sum += Number.parseFloat(el.element.price)*Number.parseFloat(el.element.amount);
            });
            return sum.toFixed(2);
        });
        localStorage.setItem("cart",JSON.stringify(cart));
    }

    
    const removeBook = (id) => {
        const updatedArray = cartElements.filter(el => {return el.id !== id });
        localStorage.setItem("cart", JSON.stringify(updatedArray))
        setCartElements(updatedArray);
        setFinalPrice(() => {
            let sum = 0;
            updatedArray.forEach(el => {
                sum += Number.parseFloat(el.element.price)*Number.parseFloat(el.element.amount);
            });
            return sum.toFixed(2);
        });
        if (location.pathname.includes("offer")) {
            window.location.reload();
        }
    }
    

    return (  
        <Box className={optionsOn} onClick={showOptions}>
            {disablePaper && 
            <Paper className="cartContainer" onClick={(e) => e.stopPropagation()}>
                <Stack direction={{sm:"column", md:"row"}} sx={{width:"100%", height:"100%", border:"1px solid black"}}>
                    <Stack className="cartList" sx={{width:{sm:"100%", md:"60%"}, height:{sm:"40%", md:"100%"}, padding:"5px", border:"2px solid black"}}>
                        <Typography variant="h4" textAlign="center">Książki:</Typography>
                        {cartElements &&
                            cartElements.map((el) => {
                                return <Paper sx={{backgroundColor:"white", margin:"10px 0"}}>
                                            <Stack direction="row" sx={{display:"flex", justifyContent:"space-beetween"}}>
                                                    <div style={{width:"10%", margin:"auto"}}>
                                                        <img src={`/images/${el.element.img}`} alt={el.element.title} width="100%" height="auto" style={{display:"block"}}/>
                                                    </div>
                                                    <Typography align="center" sx={{margin:"auto", width:"20%", fontSize:{xs:"35px", sm:"10px", md:"20px"}}}>{el.element.title}</Typography>
                                                    <input type="number" min="1" value={el.element.amount} onChange={(e) => setPrice(e, el.id)} style={{ margin:"auto",width:"15%"}}  />
                                                    <Typography textAlign="center" sx={{width:"25%", margin:"auto", fontSize:{xs:"30px", sm:"10px",md:"15px"}}}>{(Number.parseFloat(el.element.price)*Number.parseFloat(el.element.amount)).toFixed(2)}</Typography>
                                                    <Button size="small" onClick={() => removeBook(el.id)}><DeleteIcon/></Button>
                                            </Stack>
                                        </Paper>
                            })
                        }
                    </Stack>
                    <Stack sx={{padding:"5px", width:{sm:"100%", md:"40%"}, height:{sm:"60%", md:"100%"}, border:"2px solid black", overflow:"auto"}}>
                        <Typography variant="h4" textAlign="center">Kwota:</Typography>
                        <Typography textAlign="center" sx={{border:"2px solid #3d0200", fontSize:{xs:"35px", sm:"18px", md:"25px"}, backgroundColor:"white"}}>{finalPrice} zł</Typography>
                        <Button variant="contained" sx={{height:{sm:"40%", md:"10%"}, margin:"5px"}}>Przejdź do zakupu</Button>
                        <Button variant="contained" sx={{height:{sm:"40%", md:"10%"}, margin:"5px"}} onClick={showOptions}>Kontynuuj przeglądanie</Button>
                    </Stack>
                </Stack>
            </Paper>
            }
        </Box>
    )
}