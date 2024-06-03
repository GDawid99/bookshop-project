import  { Article } from '../components/Article'
import { createTheme, ThemeProvider, Container, CssBaseline, Stack, Paper, Typography, Box, Button, CircularProgress } from '@mui/material';
import { RecommendedPaper } from '../components/RecommendedPaper';
import { RecommendedBook } from '../components/RecommendedBook';
import { useEffect, useState } from 'react';
import { MainHeader } from '../components/MainHeader';
import { Link } from 'react-router-dom';


export const themeOptions = createTheme({
  palette: {
    type: 'light',
    primary: {
      main: '#3d0200',
    },
    secondary: {
      main: '#2ea61e',
    },
    error: {
      main: '#ff1500',
    },
    warning: {
      main: '#ff9800',
    },
    success: {
      main: '#4ee001',
    },
    background: {
      default: '#fdf4c5',
      paper: '#f9f990',
    },
  },
  typography: {
    fontFamily: 'Lato',
    fontSize: 12,
  },
});


const Home = () => {

    const [books, setBooks] = useState([]);
    const [articles, setArticles] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);

    useEffect(() => {
        fetch('http://localhost:8080/api/book/random')
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setBooks(data);
        })
        .catch((err) => {
            console.log(err.message);
        });
    }, []);

    useEffect(() => {
      fetch('http://localhost:8080/api/article/?page=' + pageNumber + '&size=4')
      .then((response) => response.json())
      .then((data) => {
          console.log(data);
          setArticles(data);
      })
      .catch((err) => {
          console.log(err.message);
      });
  }, [pageNumber]);

  const changePage = (page) => {
    if (page < 0) return;
    else if (page > articles.totalPages-1) return;
    else setPageNumber(page);
  }


    //console.log(books.imageUrl);

  return (
    <ThemeProvider theme={themeOptions}>
      
    <CssBaseline/>
    <Container>
      <MainHeader/>
      <Stack direction={{xs:"column", sm:"row"}}>
        <Stack direction="column" sx={{width: {xs:"100%", sm:"80%"}}}>
          {
            articles.content !== undefined &&
            <Box justifyContent={{xs:"center",sm:"right", margin:"10px 0"}} display="flex" height="30px">
              <Button sx={{height:"100%", margin:"0 10px"}} onClick={() => changePage(pageNumber-1)}>&#60;</Button>
              <Typography variant='h5' sx={{height:"100%"}}>{pageNumber+1}/{articles.totalPages}</Typography>
              <Button sx={{height:"100%", margin:"0 10px"}} onClick={() => changePage(pageNumber+1)}>&#62;</Button>
            </Box>
          }
          {
            articles.content === undefined ? <CircularProgress sx={{margin:"auto"}}/> :
            articles.content.map(el => (
              <Paper sx={{margin:"15px", padding:"10px"}}>
                  <h1>{el.title}</h1>
                  <p style={{textAlign:"justify",padding:"10px"}}>{el.body.slice(0,500)}...</p>
                  <Link to={`/article?id=${el.article_id}`}>
                    <Button variant="text">Czytaj więcej...</Button>
                  </Link>
              </Paper>
            ))
          }
          {
            articles.content !== undefined &&
            <Box justifyContent={{xs:"center",sm:"right", margin:"10px 0"}} display="flex" height="30px">
              <Button sx={{height:"100%", margin:"0 10px"}} onClick={() => changePage(pageNumber-1)}>&#60;</Button>
              <Typography variant='h5' sx={{height:"100%"}}>{pageNumber+1}/{articles.totalPages}</Typography>
              <Button sx={{height:"100%", margin:"0 10px"}} onClick={() => changePage(pageNumber+1)}>&#62;</Button>
            </Box>
          }
        </Stack>
        <Stack direction="column" sx={{width: {xs:"100%", sm:"20%"}}}>
          <Typography variant="h6">Polecane dziś:</Typography>
                <RecommendedPaper>
                    {books[0] &&
                        <RecommendedBook imageSrc={`/images/${books[0].imageUrl}`} title={books[0].title} author={books[0].author} publisher={books[0].publisher} price={books[0].price} rating={books[0].rating}/>
                    }
                </RecommendedPaper>
                <RecommendedPaper>
                    {books[1] &&
                    <RecommendedBook imageSrc={`/images/${books[1].imageUrl}`} title={books[1].title} author={books[1].author} publisher={books[1].publisher} price={books[1].price} rating={books[1].rating}/>
                }
                </RecommendedPaper>
                <RecommendedPaper>
                {books[2] &&
                    <RecommendedBook imageSrc={`/images/${books[2].imageUrl}`} title={books[2].title} author={books[2].author} publisher={books[2].publisher} price={books[2].price} rating={books[2].rating}/>
                }
                </RecommendedPaper>
      
        </Stack>
      </Stack>


    </Container>
    </ThemeProvider>
  );
}

export default Home;
