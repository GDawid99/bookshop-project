import { Button, CssBaseline, Paper, Stack, ThemeProvider, Typography } from "@mui/material"
import { themeOptions } from "./Home"
import { Link } from "react-router-dom"

export const Panel = () => {
    return (
    <ThemeProvider theme={themeOptions}>
        <CssBaseline/>
            <Paper sx={{margin:"30px 10%"}}>
                <Stack sx={{margin:"30px auto"}}>
                    <Typography variant="h4" sx={{display:"flex",justifyContent:"center"}}>Panel</Typography>
                    <Link to="/article/creator">
                        <Button variant="contained" sx={{width:"100%", margin:"30px 0"}}>Dodaj artykuł</Button>
                    </Link>
                    <Link to="/book">
                        <Button variant="contained" sx={{width:"100%", margin:"30px 0"}}>Zarządzaj książkami</Button>
                    </Link>
                    <Link to="/users">
                        <Button variant="contained" sx={{width:"100%", margin:"30px 0"}}>Zarządzaj użytkownikami</Button>
                    </Link>
                </Stack>
            </Paper>
    </ThemeProvider>
    );
}