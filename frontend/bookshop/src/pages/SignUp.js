import { AppBar, ThemeProvider, Container, CssBaseline, Toolbar, Typography, Paper, Stack, TextField, Button} from '@mui/material';
import { themeOptions } from './Home';
import { styled } from '@mui/material/styles';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const TextFieldStyle = styled(TextField)(({ theme }) => ({
    borderRadius: theme.shape.borderRadius,
    backgroundColor: '#fdf4c5',
    border:"2px ridge black",
    width: {xs: "48%", sm: "30%"},
    margin:"8px"
  })
);



export const SignUp = () => {
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("USER");
    let [password, setPassword] = useState("");
    let [repeatedPassword, setRepeatedPassword] = useState("");
    let [passwordIsCorrect, setPasswordIsCorrect] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const checkIfPasswordIsEqual = () => {
        password === repeatedPassword ? setPasswordIsCorrect(true) : setPasswordIsCorrect(false);
    }


    const signUp = async() => {
        try {
            const response = await axios.post('http://localhost:8080/api/user/register', {
                firstname,
                lastname,
                email,
                password,
                role
            });
            console.log(response.data);
            navigate("/home");
        }
        catch (error) {
            console.error('Rejestracja nieudana. ', error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : error.message);
        }
    }


    return (
      <ThemeProvider theme={themeOptions}>
      <CssBaseline/>
      <Container>
        <AppBar position="static">
        <Toolbar sx={{display:"flex", justifyContent:"center"}}>
            <Typography variant="h3">Rejestracja</Typography>
        </Toolbar>
        </AppBar>

        <Paper sx={{margin:"5px 0", display:"flex", justifyContent:"center"}}>
            <Stack direction="column" sx={{width: { xs: "100%", sm: "70%"}}}>
            <Stack direction="row" sx={{ display:"flex", justifyContent:"space-between"}}>
                <TextFieldStyle placeholder="Imię" value={firstname} onChange={e => setFirstname(e.target.value)} sx={{width: "100%"}}/>
                <TextFieldStyle placeholder="Nazwisko" value={lastname} onChange={e => setLastname(e.target.value)} sx={{width: "100%"}}/>
            </Stack>
                <TextFieldStyle placeholder="Adres e-mail" value={email} onChange={e => setEmail(e.target.value)} sx={{width:"auto"}}/>
                <TextFieldStyle placeholder='Hasło' type="password" value={password} onChange={e => setPassword(e.target.value)} sx={{width:"auto"}} onBlur={checkIfPasswordIsEqual}/>
                <TextFieldStyle placeholder='Powtórz hasło' type="password" value={repeatedPassword} onChange={e => setRepeatedPassword(e.target.value)} sx={{width:"auto"}}  onBlur={checkIfPasswordIsEqual}/>
                { 
                    (!passwordIsCorrect && (password !== "" || repeatedPassword !== "")) && (
                        <Typography sx={{color:"red", display:"flex", justifyContent:"center"}}>Hasła nie pasują do siebie.</Typography>
                    )
                }
                <Button variant="text" disabled={!(passwordIsCorrect && firstname !== "" && lastname !== "" && email !== "" && password !== "" && repeatedPassword !== "")} onClick={signUp}>
                    Utwórz konto
                </Button>
            </Stack>
        </Paper>
          
      </Container>
      </ThemeProvider>
    );
  }