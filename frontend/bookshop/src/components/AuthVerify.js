import { jwtDecode } from "jwt-decode";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";

export const AuthVerify = (props) => {
    let location = useLocation();

    useEffect(() => {
        const user = localStorage.getItem("user");
        if (user) {
            const decodedJwt = jwtDecode(user);
            if (decodedJwt.exp * 1000 < Date.now()) {
                props.logout();
            }
        }
    }, [location, props]);
    return;
}