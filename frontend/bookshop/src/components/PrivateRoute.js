import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "./AuthProvider";
import { useEffect, useState } from "react";



export const PrivateRoute = (props) => {
    const user = useAuth();
    const [roleData, setRoleData] = useState([]);
    const [loading, setLoading] = useState(true);
    
    useEffect(() => {
      const fetchData = async() => {
          try {
              const response = await fetch('http://localhost:8080/api/user/auth/validate', {
                  method: 'GET',
                  headers: {
                      'Authorization': `Bearer ${user.token}`
                  }
              });
              
              if (!response.ok) {
                setLoading(false);
                  throw new Error('Network response was not ok');
                  
              }
              
              const data = await response.json();
              setRoleData(data.roles);
              setLoading(false);
              console.log(data);
              
          } catch (error) {
              console.error('There has been a problem with your fetch operation:', error);
              setLoading(false);
          }
      };

      fetchData();
  }, [user.token]);

    console.log(roleData[0]);
    if (loading) {
      return <div>Loading...</div>;
    } else {
      if (!user.token) return <Navigate to="/login" />;
        switch(props.role) {
          case "ROLE_USER": {
            if (roleData[0] === "ROLE_USER" || roleData[0] === "ROLE_MODERATOR" || roleData[0] === "ROLE_ADMIN") return <Outlet />;
            else return <Navigate to="/login" />;
          }
          case "ROLE_MODERATOR": {
            if (roleData[0] === "ROLE_MODERATOR" || roleData[0] === "ROLE_ADMIN") return <Outlet />;
            else return <Navigate to="/login" />;
          }
          case "ROLE_ADMIN": {
            if (roleData[0] === "ROLE_ADMIN") return <Outlet />;
            else return <Navigate to="/login" />;
          }
          default: return <Navigate to="/login" />;
        }
    }
  };