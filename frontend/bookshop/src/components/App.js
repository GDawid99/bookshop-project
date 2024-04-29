import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from '../pages/Home';
import { LogIn } from '../pages/LogIn';
import { Search } from '../pages/Search';



const App = () => {


  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/signup" element={<SignUp/>}/>
        <Route path="/login" element={<LogIn/>}/>
        <Route path={"/search"} element={<Search/>}/>
      </Routes>
      </BrowserRouter>
  );
}

export default App;
