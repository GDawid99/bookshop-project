import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUp } from '../pages/SignUp';
import Home from '../pages/Home';
import { LogIn } from '../pages/LogIn';
import { Search } from '../pages/Search';
import { BookPage } from '../pages/BookPage';
import { AuthProvider} from './AuthProvider';
import { PrivateRoute } from './PrivateRoute';
import { Profile } from '../pages/Profile';
import { Payment } from '../pages/Payment';
import { Article } from './Article';
import { ArticleCreator } from '../pages/ArticleCreator';
import { Panel } from '../pages/Panel';
import { BookManager } from '../pages/BookManager';



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
          <Route path="/offer" element={<BookPage/>}/>
          <Route path="/article" element={<Article/>}/>
          <Route element={<PrivateRoute role="ROLE_USER"/>}>
            <Route path="/profile" element={<Profile/>}/>
            <Route path="/payment" element={<Payment/>}/>
          </Route>
          <Route element={<PrivateRoute role="ROLE_MODERATOR"/>}>
            <Route path="/article/creator" element={<ArticleCreator/>}/>
            <Route path="/panel" element={<Panel/>}/>
            <Route path="/book" element={<BookManager/>}/>
          </Route>
          <Route element={<PrivateRoute role="ROLE_ADMIN"/>}>
            
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
