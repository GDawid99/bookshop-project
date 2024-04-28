import { Typography, InputBase, Button, Box, IconButton } from '@mui/material';
import { styled } from '@mui/material/styles';
import LoginIcon from '@mui/icons-material/Login';
import SearchIcon from '@mui/icons-material/Search';
import { Link } from 'react-router-dom';


const Search = styled(Box)(({ theme }) => ({
    borderRadius: theme.shape.borderRadius,
    backgroundColor: '#dcb597',
    padding:"0 10px",
    margin:"0 10px",
    width: "70%",
    display:"flex",
    justifyContent:"space-between"
  })
);

export const Header = () => {
    return (
        <>
        <Typography variant='h6' sx={{textAlign:"center", fontSize: {xs: "10px", sm:"16px"}}}>Księgarnia</Typography>    
        <Search>
            <InputBase placeholder="Szukaj..." sx={{width:"100%"}}>
            </InputBase>
            <IconButton sx={{display:{xs:"none", sm:"block"}}}>
                <SearchIcon sx={{fontSize: {sm:"15px"}, color:"grey"}}></SearchIcon>
            </IconButton>
        </Search>
        <Link to="/login" style={{textDecoration:"none"}}>
            <Button sx={{ fontSize: "7px", color: 'white'}}>
                <Typography sx={{fontSize: "10px", display:{xs:"none", sm:"block"}}}>Zaloguj się</Typography>
                <LoginIcon sx={{fontSize: "16px", display:{xs:"block", sm:"none"}}}></LoginIcon>
            </Button>
        </Link>
        <Link to="/signup" style={{textDecoration:"none"}}>
            <Button sx={{ fontSize: { xs:"7px", sm:"10px"}, color: 'white', display: 'block' }}>
                Zarejestruj się
            </Button>
        </Link>
        </>     
    );
}