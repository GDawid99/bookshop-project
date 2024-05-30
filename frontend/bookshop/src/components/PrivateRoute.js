import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "./AuthProvider";
import { Profile } from "../pages/Profile";

export const PrivateRoute = () => {
    const user = useAuth();
    if (!user.token) return <Navigate to="/login" />;
    return <Outlet />;
  };