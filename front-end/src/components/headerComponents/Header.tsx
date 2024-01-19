import styled from "styled-components";
import SearchBar from "./SearchBar";
import UserInfo from "./UserInfo";
import HeaderLogo from "./HeaderLogo";
import Login from "../login/Login";
import Register from "../login/Register";
import React, { useEffect, useState } from "react";
import Feed from "../feed/Feed";
import MyFeed from "../feed/MyFeed";
import WriteFeed from "../feed/WriteFeed";
import Notification from "../notification/Notification";
import LogOut from "../login/LogOut";

function Header() {
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("token")) {
      setIsLogin(true);
    }
  }, [localStorage.getItem("token")]);

  return (
    <HeaderContainer>
      <LogoArea>LOGO</LogoArea>
      <HeaderWrapper>
        <Feed />
        <WriteFeed />
        <MyFeed />
        {isLogin ? (
          <LoginInfo>
            <LogOut setIsLogin={setIsLogin} />
            <Notification />
          </LoginInfo>
        ) : (
          <LoginInfo>
            <Login setIsLogin={setIsLogin} />
            <Register />
          </LoginInfo>
        )}
      </HeaderWrapper>
    </HeaderContainer>
  );
}

export default Header;

const HeaderContainer = styled.div`
  padding: 20px 0;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid rgb(238, 238, 238);
  background-color: rgb(255, 255, 255);
  position: sticky;
  z-index: 100;
`;

const HeaderWrapper = styled.div`
  flex-basis: 700px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-weight: bold;
  font-size: 18px;
`;

const LogoArea = styled.div`
  flex-basis: 10%;
  font-size: 30px;
`;

const LoginInfo = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 15px;
`;
