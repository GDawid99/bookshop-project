import { Box, Checkbox, Grid, Stack, TextField, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";


const test = [
        { publisher:"A"},
        { publisher:"B"},
        { publisher:"A"},
        { publisher:"C"},
        { publisher:"D"},
        { publisher:"B"},
        { publisher:"B"},
        { publisher:"C"},
        { publisher:"E"},
        { publisher:"A"}
];

export const Filter = (props) => {
    
    
    const [priceFilter, setPriceFilter] = useState(false);
    const [publisherFilter, setPublisherFilter] = useState([]);
    const [genreFilter, setGenreFilter] = useState([]);
    let [priceFrom, setPriceFrom] = useState("");
    let [priceTo, setPriceTo] = useState();

    const [params, setParams] = useSearchParams();
    const navigate = useNavigate();

    let [currentQueryParams, setCurrentQueryParams] = useState("?title="+ params.get("title") + "&id="+ params.get("id") +"&size="+ params.get("size"));

    //console.log(currentQueryParams);


    const checkInput = (e) => {
        setPriceFrom(e.target.value);
    }

    const checkPublisher = (e) => {
        if (e.target.checked) { 
            setPublisherFilter([...publisherFilter, e.target.value]);
            params.append("publisher",e.target.value);
            setParams(params);
        }
        else { 
            setPublisherFilter(publisherFilter.filter(p => p !== e.target.value));
            if (params.has("publisher")) {
                params.delete("publisher",e.target.value);
                setParams(params);
            }
        }
        console.log(publisherFilter);
    }

    const checkGenre = (e) => {
        if (e.target.checked) { 
            setGenreFilter([...genreFilter, e.target.value]);
            params.append("genre",e.target.value);
            setParams(params);
        }
        else { 
            setGenreFilter(genreFilter.filter(p => p !== e.target.value));
            if (params.has("genre")) {
                params.delete("genre",e.target.value);
                setParams(params);
            }
        }
    }
    
    //console.log(typeof props.book);

    return(
        <>
            <Typography variant="h4">Filtruj:</Typography>
                <Box>
                    <Typography>Wg ceny:</Typography>
                    <Stack direction="row">
                        <TextField variant="standard" InputProps={{disableUnderline: true}} sx={{width:"47%", margin:"5px", backgroundColor:"white", paddingTop:"4px"}} size="small" placeholder="Od" value={priceFrom} onChange={checkInput}></TextField>
                        <Typography align="center" sx={{width:"6%", margin:"auto"}} variant="h6">:</Typography>
                        <TextField variant="standard" InputProps={{disableUnderline: true}} sx={{width:"47%", margin:"5px", backgroundColor:"white", paddingTop:"4px"}} size="small" placeholder="Do" value={priceTo} onChange={(e) => {setPriceTo(e.target.value);}}></TextField>
                        <Checkbox sx={{width:"10%"}} checked={priceFilter} onClick={() => {
                            if(!priceFilter) { 
                                priceFrom = priceFrom.match(/\d+(\.\d+|\,\d+)?/)[0];
                                priceTo = priceTo.match(/\d+(\.\d+|\,\d+)?/)[0];
                                currentQueryParams += "&priceFrom=" + priceFrom + "&priceTo=" + priceTo;
                                navigate({
                                    pathname: `${window.location.pathname}`,
                                    search: `${currentQueryParams}`
                                });
                            }
                            else {
                                if (params.has("priceFrom")) {
                                    params.delete("priceFrom");
                                    setParams(params);
                                }
                                if (params.has("priceTo")) {
                                    params.delete("priceTo");
                                    setParams(params);
                                }
                            }
                        setPriceFilter(!priceFilter);
                        }}></Checkbox>
                    </Stack>
                    <Typography>Wg gatunku:</Typography>
                    <Stack>
                        {
                            Array.from(props.book)
                            .filter((el, index, self) => {
                                return index === self.findIndex(b => b.genre === el.genre)
                            })
                            .map((el) => {
                                return (
                                    <Stack direction="row" sx={{display:"flex"}}>
                                        <Typography sx={{width:"90%", margin:"auto"}}>{el.genre}</Typography>
                                        <Checkbox value={el.genre} checked={genreFilter.includes(el.genre)} onClick={checkGenre} sx={{width:"10%"}}></Checkbox>
                                    </Stack>
                                );
                            })
                        }
                    </Stack>
                    <Typography>Wg oceny ponad:</Typography>
                    <TextField placeholder="Ocena" size="small" sx={{backgroundColor:"white"}}></TextField>
                    <Typography>Wg wydawnictwa:</Typography>
                    <Stack>
                        {
                            Array.from(props.book)
                            .filter((el, index, self) => {
                                return index === self.findIndex(b => b.publisher === el.publisher)
                            })
                            .map((el) => {
                                return (
                                    <Stack direction="row">
                                        <Typography sx={{width:"90%", margin:"auto"}}>{el.publisher}</Typography>
                                        <Checkbox value={el.publisher} checked={publisherFilter.includes(el.publisher)} onClick={checkPublisher} sx={{width:"10%"}}></Checkbox>
                                    </Stack>
                                );
                            })
                        }
                    </Stack>
                </Box>
        </>
    );
}