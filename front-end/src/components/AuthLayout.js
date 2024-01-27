import React, { useEffect } from "react";
import { useNavigate, useLocation, Outlet } from "react-router-dom";

const AuthLayout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (!localStorage.getItem("token")) {
      navigate("/");
    }
  }, [localStorage.getItem("token")]);

  return (
    <div>
      <Outlet />
    </div>
  );
};

export default AuthLayout;
