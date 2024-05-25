import { Navigation } from './Navigation';
import { Header } from './Header';
import { AppBar , styled, Toolbar } from '@mui/material';
import { useState } from 'react';
import { AccountInfo } from './AccountInfo';
import { Cart } from './Cart';

const StyledToolBar = styled(Toolbar)({
    display:"flex",
    justifyContent:"space-between"
  });

export const MainHeader = () => {
    
    const [showAccountOptions, setShowAccountOptions] = useState(false);
    const [showCart, setShowCart] = useState(false);

    return(
        <AppBar position="sticky">
            <StyledToolBar position="relative">        
                <Header showAccountOptions={showAccountOptions} setShowAccountOptions={setShowAccountOptions} showCart={showCart} setShowCart={setShowCart}/>
            </StyledToolBar>
            <StyledToolBar variant="dense" sx={{display: {xs: "none", md: "flex"}}}>        
                <Navigation/>
            </StyledToolBar>
            {showAccountOptions && <AccountInfo setShowAccountOptions={setShowAccountOptions} />}
            {showCart && <Cart setShowCart={setShowCart}/>}
        </AppBar>
        
    );
}