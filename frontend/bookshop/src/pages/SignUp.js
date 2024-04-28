import { AppBar, ThemeProvider, Container, CssBaseline, Toolbar, Typography, Paper, Stack, TextField, Button} from '@mui/material';
import { themeOptions } from '../components/Home';
import { styled } from '@mui/material/styles';
import { useState } from 'react';


const TextFieldStyle = styled(TextField)(({ theme }) => ({
    borderRadius: theme.shape.borderRadius,
    backgroundColor: '#fdf4c5',
    border:"2px ridge black",
    width: {xs: "48%", sm: "30%"},
    margin:"8px"
  })
);



export const SignUp = () => {

    let [password, setPassword] = useState("");
    let [repeatedPassword, setRepeatedPassword] = useState("");
    let [passwordIsCorrect, setPasswordIsCorrect] = useState(false);

    const checkIfPasswordIsEqual = () => {
        password === repeatedPassword ? setPasswordIsCorrect(true) : setPasswordIsCorrect(false);
    }

    const handleChange1 = (e) => {
        setPassword(e.target.value);
    }

    const handleChange2 = (e) => {
        setRepeatedPassword(e.target.value);
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
                <TextFieldStyle placeholder="Imię" sx={{width: "100%"}}/>
                <TextFieldStyle placeholder="Nazwisko" sx={{width: "100%"}}/>
            </Stack>
                <TextFieldStyle placeholder="Adres e-mail" sx={{width:"auto"}}/>
                <TextFieldStyle placeholder='Hasło' type="password" value={password} onChange={handleChange1} sx={{width:"auto"}} onBlur={checkIfPasswordIsEqual}/>
                <TextFieldStyle placeholder='Powtórz hasło' type="password" value={repeatedPassword} onChange={handleChange2} sx={{width:"auto"}}  onBlur={checkIfPasswordIsEqual}/>
                { 
                    (!passwordIsCorrect && (password !== "" || repeatedPassword !== "")) && (
                        <Typography sx={{color:"red", display:"flex", justifyContent:"center"}}>Hasła nie pasują do siebie.</Typography>
                    )
                }
                <Button variant="text">
                    Utwórz konto
                </Button>
            </Stack>
        </Paper>
          
      </Container>
      </ThemeProvider>
    );
  }