import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from '../pages/Home';
import { LogIn } from '../pages/LogIn';
import { Search } from '../pages/Search';
import { BookPage } from '../pages/BookPage';
import { AuthProvider } from './AuthProvider';



const App = () => {
  let param;
  return (
    
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/signup" element={<SignUp/>}/>
          <Route path="/login" element={<LogIn/>}/>
          <Route path={"/search"} element={<Search phrase={param}/>}/>
          <Route path="offer" element={<BookPage/>}/>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
