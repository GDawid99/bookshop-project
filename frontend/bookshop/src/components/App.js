import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from '../pages/Home';
import { LogIn } from '../pages/LogIn';
import { Search } from '../pages/Search';
import { useState } from 'react';



const App = () => {

  //const [params, setParams] = useState([]);
  

  let param;

  //setParams(["P",0,2]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/signup" element={<SignUp/>}/>
        <Route path="/login" element={<LogIn/>}/>
        <Route path={"/search"} element={<Search phrase={param}/>}/>
      </Routes>
      </BrowserRouter>
  );
}

export default App;
