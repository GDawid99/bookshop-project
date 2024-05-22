import { Typography, InputBase, Button, Box, IconButton } from '@mui/material';
import { styled } from '@mui/material/styles';
import LoginIcon from '@mui/icons-material/Login';
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import { useState } from 'react';
import { useAuth } from './AuthProvider';
import { AccountInfo } from './AccountInfo';


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

export const Header = (props) => {
    const user = useAuth();
    const textFromUrl = useSearchParams();
    let title = (textFromUrl[0].get("title") === null || textFromUrl[0].get("title") === undefined) ? "" : textFromUrl[0].get("title");
    const [titleValue, setTitleValue] = useState(title);
    

    const navigate = useNavigate();

    //let title = "P";
    let id = 0;
    let size = 2;

    const changeTitle = (e) => {
        setTitleValue(e.target.value);
    }

    const goToPosts = (title) => {
        if (title === "") navigate("/search");
        else navigate({
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
            <Button variant='h6' sx={{textAlign:"center", fontSize:"11px", display:{xs:"none", sm:"block"}}} onClick={()=> {navigate("/");}}>Księgarnia</Button>    
            <Search>
                <InputBase value={titleValue} onChange={changeTitle}  onKeyDown={(e) => { if (e.key === 'Enter') searchEvent()}} placeholder="Szukaj..." sx={{width:"100%"}}>
                </InputBase>
                <IconButton onMouseDown={searchEvent} sx={{display:{xs:"none", sm:"block"}}}>
                    <SearchIcon sx={{fontSize: {sm:"15px"}, color:"grey"}}></SearchIcon>
                </IconButton>
            </Search>
            {!user.token && <Link to="/login" style={{textDecoration:"none"}}>
                <Button sx={{ fontSize: "7px", color: 'white'}}>
                    <Typography sx={{fontSize: "10px", display:{xs:"none", sm:"block"}}}>Zaloguj się</Typography>
                    <LoginIcon sx={{fontSize: "16px", display:{xs:"block", sm:"none"}}}></LoginIcon>
                </Button>
            </Link>}
            {!user.token && <Link to="/signup" style={{textDecoration:"none"}}>
                <Button sx={{ fontSize: { xs:"7px", sm:"10px"}, color: 'white', display: 'block' }}>
                    Zarejestruj się
                </Button>
            </Link>}
            {user.token && <Link to="/login" style={{textDecoration:"none"}}>
                <Button sx={{ fontSize: "7px", color: 'white'}}>
                    <ShoppingCartIcon sx={{fontSize: "20px", display:{xs:"none", sm:"block"}}}></ShoppingCartIcon>
                    <ShoppingCartIcon sx={{fontSize: "16px", display:{xs:"block", sm:"none"}}}></ShoppingCartIcon>
                </Button>
            </Link>}
            {user.token && 
                <Button sx={{ fontSize: { xs:"20px", sm:"16px"}, color: 'white', display: 'block' }} onClick={() => props.setShowAccountOptions(!props.showAccountOptions)}>
                    <AccountBoxIcon sx={{fontSize: "20px", display:"block"}}></AccountBoxIcon>
                </Button>
            }
        </>     
    );
}