import { Box, Button, Paper, Stack } from "@mui/material"
import { useAuth } from "./AuthProvider"
import { useCallback, useState } from "react";
import { useNavigate } from "react-router-dom";

export const AccountInfo = ({setShowAccountOptions}) => {
    const auth = useAuth();
    const navigate = useNavigate();
    const [optionsOn, setOptionsOn] = useState("accountBackgroundOn");
    const [disablePaper, setDisablePaper] = useState(true);

    const showOptions = useCallback((e) => {
        setDisablePaper(false);
        setOptionsOn("accountBackgroundOff")
        setTimeout(() => {setShowAccountOptions(false)}, 200);
    }, [setShowAccountOptions])


    const handleSubmitEvent = (e) => {
        e.preventDefault();
        auth.logout();
        window.location.reload();
        return;
    };

    return(
        <Box className={optionsOn} onClick={showOptions}>
            {disablePaper && <Paper className="accountContainer" onClick={(e) => e.stopPropagation()}>
                <Stack direction="column">
                    <Button onClick={() => navigate("/profile")}>Profil</Button>
                    <Button>Moje zakupy</Button>
                    <Button>Rekomendacje</Button>
                    <Button>Ustawienia konta</Button>
                    <Button onClick={handleSubmitEvent}>Wyloguj się</Button>
                </Stack>
            </Paper>}
        </Box>
    )
}