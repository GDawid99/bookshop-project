import { createContext, useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";


const AuthContext = createContext();

export const AuthProvider = ({children}) => {

    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("user") || "");
    const navigate = useNavigate();
    
    const login = async(data) => {
        try {
            const response = await fetch('http://localhost:8080/api/user/auth/login', {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body: JSON.stringify(data)
            });
            const res = await response.json();
            if (res) {
                setUser(res.user);
                setToken(res.token);
                localStorage.setItem("user", res.token);
                navigate("/");
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
        localStorage.removeItem("user");
        navigate("/");
        return;
    }

    return <AuthContext.Provider value={{token, user, login, logout}}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
    return useContext(AuthContext);
}