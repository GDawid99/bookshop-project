import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from './Home';


const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/rejestracja" element={<SignUp/>}/>
      </Routes>
      </BrowserRouter>
  );
}

export default App;
