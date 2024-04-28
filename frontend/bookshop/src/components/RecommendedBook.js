import { Button, Rating, Typography } from "@mui/material";


export const RecommendedBook = (props) => {
    
    return(
        <>
            <Typography variant={props.title.length < 20 ? "h4" : "h6"} sx={{display: 'flex', justifyContent:"center", textAlign:"center"}}>{props.title}</Typography>
            <img src={props.imageSrc} alt={props.title} style={{width:"100%"}}></img>
            <Typography variant="h6" sx={{fontSize:"9px"}}>Autor: {props.author}</Typography>
            <Typography variant="h6" sx={{fontSize:"9px"}}>Wydawnictwo: {props.publisher}</Typography>
            <Typography variant="h6" display="inline" sx={{fontSize:"9px"}}>Cena: </Typography>
            <Typography variant="h6" display="inline" sx={{fontSize:"9px", color:"purple"}}>{props.price} zł</Typography>
            <Typography>
                <Rating value={props.rating/2} readOnly precision={0.5} size="small"></Rating>
            </Typography>
            <Typography>
                <Button variant="h5" display="flex">SPRAWDŹ</Button>
            </Typography>
        </>  
    );
}