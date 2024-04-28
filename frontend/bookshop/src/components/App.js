import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from './Home';
import { LogIn } from '../pages/LogIn';


const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/rejestracja" element={<SignUp/>}/>
        <Route path="/logowanie" element={<LogIn/>}/>
      </Routes>
      </BrowserRouter>
  );
}

export default App;
