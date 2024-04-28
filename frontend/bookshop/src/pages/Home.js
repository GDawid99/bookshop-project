import  { Article } from '../components/Article'
import { createTheme, ThemeProvider, Container, CssBaseline, Stack, Paper, Typography } from '@mui/material';
import { RecommendedPaper } from '../components/RecommendedPaper';
import { RecommendedBook } from '../components/RecommendedBook';
import { useEffect, useState } from 'react';
import { MainHeader } from '../components/MainHeader';




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


const article1 = {
  title: "Co nowego?",
  contents: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in."
}

const article2 = {
  title: "Wyprzedaż",
  contents: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in."
}

const article3 = {
  title: "Nowa oferta",
  contents: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in."
}

const article4 = {
  title: "Okazja",
  contents: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in."
}




const Home = () => {

    const [books, setBooks] = useState([]);

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

    //console.log(books.imageUrl);

  return (
    <ThemeProvider theme={themeOptions}>
      
    <CssBaseline/>
    <Container>
      <MainHeader/>
      
      <Stack direction={{xs:"column", sm:"row"}}>
        <Stack direction="column" sx={{width: {xs:"100%", sm:"80%"}}}>
          <Paper sx={{margin:"15px", padding:"10px"}}>
            <Article title={article1.title} contents={article1.contents} />
          </Paper>
          <Paper sx={{margin:"15px", padding:"10px"}}>
            <Article title={article2.title} contents={article2.contents} />
          </Paper>
          <Paper sx={{margin:"15px", padding:"10px"}}>
            <Article title={article3.title} contents={article2.contents} />
          </Paper>
          <Paper sx={{margin:"15px", padding:"10px"}}>
            <Article title={article4.title} contents={article2.contents} />
          </Paper>
          <Paper sx={{margin:"15px", padding:"10px"}}>
            <Article title={article2.title} contents={article2.contents} />
          </Paper>
        </Stack>
        <Stack direction="column" sx={{width: {xs:"100%", sm:"20%"}}}>
          <Typography variant="h6">Polecane dziś:</Typography>
                <RecommendedPaper>
                    {books[0] &&
                        <RecommendedBook imageSrc={`/images/${books[0].imageUrl}`} title={books[0].title} author={books[0].author.firstname + " " + books[0].author.lastname} publisher={books[0].publisher} price={books[0].price} rating={books[0].rating}/>
                    }
                </RecommendedPaper>
                <RecommendedPaper>
                    {books[1] &&
                    <RecommendedBook imageSrc={`/images/${books[1].imageUrl}`} title={books[1].title} author={books[1].author.firstname + " " + books[1].author.lastname} publisher={books[1].publisher} price={books[1].price} rating={books[1].rating}/>
                }
                </RecommendedPaper>
                <RecommendedPaper>
                {books[2] &&
                    <RecommendedBook imageSrc={`/images/${books[2].imageUrl}`} title={books[2].title} author={books[2].author.firstname + " " + books[2].author.lastname} publisher={books[2].publisher} price={books[2].price} rating={books[2].rating}/>
                }
                </RecommendedPaper>
      
        </Stack>
      </Stack>


    </Container>
    </ThemeProvider>
  );
}

export default Home;
