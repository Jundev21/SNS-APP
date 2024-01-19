import styled from "styled-components";
import { useEffect, useState } from "react";
import axios from "axios";
import LoginModal from "./LoginModal";
import { useNavigate } from "react-router-dom";

function Login({ setIsLogin }) {
  const [modalOpen, setModalOpen] = useState(false);
  const navigate = useNavigate();

  // useEffect(() => {
  //     const autoData = async () => {
  //         const liveWord = await axios.get(
  //             `https://api.stg-bunjang.co.kr/api/1/search/suggests_keyword.json?q=${searchWord}&type=product&v=2`
  //         );
  //         setAutoComplete(liveWord.data.keywords);
  //     };
  //     autoData();
  // }, [searchWord]);

  const handleModal = () => {
    setModalOpen((e) => !e);
  };

  return (
    <>
      <LoginContainer>
        <LoginWrapper onClick={handleModal}>로그인</LoginWrapper>
        {modalOpen && <LoginModal handleModal={handleModal} modalOpen={modalOpen} setIsLogin={setIsLogin} />}
      </LoginContainer>
    </>
  );
}

export default Login;

const LoginContainer = styled.div`
  padding: 0 10px;
  color: black;
  &:hover {
    text: bold;
  }
`;

const LoginWrapper = styled.div`
  cursor: pointer;
  &:hover {
    font-weight: bold;
  }
`;
