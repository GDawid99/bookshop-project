import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';


const pages = ['Fantastyka','Sci-Fi','Kryminały','Romanse','Sensacyjne','Horrory','Przygodowe','Historyczne','Popularnonaukowe','Literatura piękna'];

export const Navigation = () => {
    const navigate = useNavigate();

    return (    <>
        {
            pages.map((p) => (
                <Button key={p} sx={{ fontSize:"9px", color: 'white', display: 'block' }} onClick={() => {navigate({
                    pathname:'/search',
                    search:`genre=${p}`
                })}}>
                    {p}
                </Button>
            ))
        }
        </>
    );
}