import { Typography, InputBase, Button, Box, IconButton } from '@mui/material';
import { styled } from '@mui/material/styles';
import LoginIcon from '@mui/icons-material/Login';
import SearchIcon from '@mui/icons-material/Search';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import { useState } from 'react';


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
    
    const textFromUrl = useSearchParams();
    const [titleValue, setTitleValue] = useState(textFromUrl[0].get("title"));

    const navigate = useNavigate();

    //let title = "P";
    let id = 0;
    let size = 2;

    const changeTitle = (e) => {
        setTitleValue(e.target.value);
    }

    const goToPosts = (title) => {
        navigate({
            pathname:'/search',
            search:`title=${title}&id=${id}&size=${size}`
        })
        window.location.reload();
    }

    const searchEvent = (e) => {
        console.log(titleValue.replaceAll(/[&%$+,/:;=?@]/g,''));
        goToPosts(titleValue.replaceAll(/[&%$+,/:;=?@]/g,''));
    }
    
    return (
        <>
        <Typography variant='h6' sx={{textAlign:"center", fontSize: {xs: "10px", sm:"16px"}}}>Księgarnia</Typography>    
        <Search>
            <InputBase value={titleValue} onChange={changeTitle}  onKeyDown={(e) => { if (e.key === 'Enter') searchEvent()}} placeholder="Szukaj..." sx={{width:"100%"}}>
            </InputBase>
            <IconButton onMouseDown={searchEvent} sx={{display:{xs:"none", sm:"block"}}}>
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