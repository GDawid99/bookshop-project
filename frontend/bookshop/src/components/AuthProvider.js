import { createContext, useContext, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";


const AuthContext = createContext();

export const AuthProvider = ({children}) => {

    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("site") || "");
    const navigate = useNavigate();
    
    const login = async(data) => {
        try {
            console.log("DANE:");
            console.log(data);
            const response = await fetch('http://localhost:8080/api/user/login', {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify(data)
            });
            const res = await response.json();
            console.log("ODP:");
            console.log(res);
            if (res) {
                setUser(res.user);
                setToken(res.token);
                localStorage.setItem("site", res.token);
                navigate(-1);
                return;
            }
            throw new Error(res.message);
        }
        catch (error) {
            console.error('Logowanie nieudane. ', error.response ? error.response.data : error.message);
        }
    }

    const logout = () => {
        setUser(null);
        setToken("");
        localStorage.removeItem("site");
        return <Link to={window.location.href} />
    }

    return <AuthContext.Provider value={{token, user, login, logout}}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
    return useContext(AuthContext);
}