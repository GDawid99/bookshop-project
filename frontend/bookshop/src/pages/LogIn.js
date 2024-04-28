import { AppBar, ThemeProvider, Container, CssBaseline, Toolbar, Typography, Paper, Stack, TextField, Button} from '@mui/material';
import { themeOptions } from './Home';
import { styled } from '@mui/material/styles';


const TextFieldStyle = styled(TextField)(({ theme }) => ({
    borderRadius: theme.shape.borderRadius,
    backgroundColor: '#fdf4c5',
    border:"2px ridge black",
    width: {xs: "48%", sm: "30%"},
    margin:"8px"
  })
);



export const LogIn = () => {
    return (
      <ThemeProvider theme={themeOptions}>
      <CssBaseline/>
      <Container>
        <AppBar position="static">
        <Toolbar sx={{display:"flex", justifyContent:"center"}}>
            <Typography variant="h3">Logowanie</Typography>
        </Toolbar>
        </AppBar>

        <Paper sx={{margin:"5px 0", display:"flex", justifyContent:"center"}}>
            <Stack direction="column" sx={{width: { xs: "100%", sm: "70%"}}}>
                <TextFieldStyle placeholder="Adres e-mail" sx={{width:"auto"}}/>
                <TextFieldStyle placeholder='Hasło' type="password" sx={{width:"auto"}}/>
            
                <Button variant="text">
                    Zaloguj się
                </Button>
            </Stack>
        </Paper>
          
      </Container>
      </ThemeProvider>
    );
  }