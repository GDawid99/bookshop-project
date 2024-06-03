import { Button, Rating, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";


export const RecommendedBook = (props) => {
    const navigate = useNavigate();

    return(
        <>
            <Typography variant={props.title.length < 20 ? "h5" : "h6"} sx={{display: 'flex', justifyContent:"center", textAlign:"center"}}>{props.title}</Typography>
            <img src={props.imageSrc} alt={props.title} style={{width:"100%"}}></img>
            <Typography variant="h6" sx={{fontSize:"9px"}}>Autor: {props.author.firstname + " " + props.author.lastname}</Typography>
            <Typography variant="h6" sx={{fontSize:"9px"}}>Wydawnictwo: {props.publisher}</Typography>
            <Typography variant="h6" display="inline" sx={{fontSize:"9px"}}>Cena: </Typography>
            <Typography variant="h6" display="inline" sx={{fontSize:"9px", color:"purple"}}>{props.price} zł</Typography>
            <Typography>
                <Rating value={props.rating} readOnly precision={0.5} max={10} size="small" sx={{fontSize:{sm:"8px",md:"14px"}}}></Rating>
            </Typography>
            <Typography>
                <Button variant="h5" display="flex" sx={{width: {xs:"20%", sm:"60%", md: "40%"}}} onClick={() => navigate("/offer?title=" + props.title,{state: props.author.author_id})}>SPRAWDŹ</Button>
            </Typography>
        </>  
    );
}