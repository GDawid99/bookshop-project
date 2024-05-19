import { Navigation } from './Navigation';
import { Header } from './Header';
import { AppBar , styled, Toolbar } from '@mui/material';

const StyledToolBar = styled(Toolbar)({
    display:"flex",
    justifyContent:"space-between"
  });

export const MainHeader = () => {
    return(
        <AppBar position="sticky">
            <StyledToolBar position="relative">        
                <Header/>
            </StyledToolBar>
            <StyledToolBar variant="dense" sx={{display: {xs: "none", md: "flex"}}}>        
                <Navigation/>
            </StyledToolBar>
        </AppBar>
    );
}