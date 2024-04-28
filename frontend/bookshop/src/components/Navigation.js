import { Button } from '@mui/material';


const pages = ['Fantastyka','Sci-Fi','Kryminały','Romanse','Sensacyjne','Horrory','Przygodowe','Historyczne','Popularnonaukowe','Literatura piękna'];

export const Navigation = () => {
    return (    <>
        {
            pages.map((p) => (
                <Button key={p} sx={{ fontSize:"9px", color: 'white', display: 'block' }}>
                    {p}
                </Button>
            ))
        }
        </>
    );
}