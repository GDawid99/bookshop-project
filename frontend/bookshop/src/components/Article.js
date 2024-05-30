import { Button } from "@mui/material";

export const Article = (props) => {
    return (
     <>
        <h1>{props.title}</h1>
        <p style={{textAlign:"justify",padding:"10px"}}>{props.contents.slice(0,500)}...</p>
        <Button variant="text">Czytaj więcej...</Button>
     </>
    );
}