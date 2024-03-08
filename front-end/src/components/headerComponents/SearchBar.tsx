import styled from "styled-components";
import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { useAppDispatch } from "../../hooks";
import { setSearchWordTK, setOrderCommandTK } from "../../redux/dataSlice";
import RenderAutoCom from "../searchBarComponents/RenderAutoCom";

function SearchBar() {
  const [searchWord, setSearchWord] = useState("");
  const [autoComplete, setAutoComplete] = useState([]);
  const [searchList, setSearchList] = useState(false);
  const textInput = useRef<HTMLInputElement>(null);

  const dispatch = useAppDispatch();

  // useEffect(() => {
  //   const autoData = async () => {
  //     const liveWord = await axios.get(
  //       `https://api.stg-bunjang.co.kr/api/1/search/suggests_keyword.json?q=${searchWord}&type=product&v=2`
  //     );
  //     setAutoComplete(liveWord.data.keywords);
  //   };
  //   autoData();
  // }, [searchWord]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchWord(e.target.value);
  };

  const handleInput = () => {
    setSearchList(true);
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement> | null) => {
    if (e) {
      e.preventDefault();
    }

    if (searchWord !== "") {
      let getSessionData = sessionStorage.getItem("SearchHistory");

      if (!getSessionData) {
        sessionStorage.setItem("SearchHistory", JSON.stringify([searchWord]));
      } else {
        let historyArr: Array<string> = JSON.parse(getSessionData);

        if (historyArr.length > 6) {
          historyArr.pop();
        }
        historyArr.unshift(searchWord);
        let newData = new Set(historyArr);
        let makeArr = [...newData];
        sessionStorage.setItem("SearchHistory", JSON.stringify(makeArr));
      }

      setSearchList(false);
      dispatch(setOrderCommandTK("date"));
      dispatch(setSearchWordTK(searchWord));
    }
  };

  const handleClear = () => {
    if (textInput.current !== null) {
      textInput.current.focus();
    }
    setSearchList(true);
    setSearchWord("");
  };

  return (
    <SearchContainer>
      <SearchWrapper>
        <SearchForm onSubmit={handleSubmit}>
          <FormInput ref={textInput} type="text" onChange={handleChange} placeholder="상품명" value={searchWord} onClick={handleInput} />

          {searchList === true && <RenderAutoCom searchWord={searchWord} autoComplete={autoComplete} setSearchWord={setSearchWord} setSearchList={setSearchList} />}
        </SearchForm>
        {searchWord !== "" && <ClearInput onClick={() => handleClear()}>X</ClearInput>}
        <Img
          src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB
          QAAAAUCAYAAACNiR0NAAAAAXNSR0IArs4c6QAAAeZJREFUOBGVVD1PAkEQ3VlOjY0xIdGOI0BMxMSGytJE+RsWWomx8mfYWNBpZWltg1b2NCZaGBppFAzR1njsOO9gzHEfIJdws/vmvcft7OySiT2DQqUakDtipjoZ4xsyzGy6RNzy2F7mu53nmGRiKprRw7XaQm/wdU6OG2xMTvFoFPKQLTXX86tn1G7/RHM6thjArP/xeWscn8rUWqJLee/klhdW8MM4xCQHDrjQqEkivhfLF++FEvf80luvsLGXIIwB5MABF5o0HoU1M+5RkvK1Xn29+3KfRlQMpmyCOyzfM3Y7XlMbboDUjIiuZpnBFBwsH3WGVv9Io8VuYuLEUMFZUbmqjfJt2BqC5JZyT9HEtLFyVRvlhrscBeYaS4/G+VaQV4DD7+FWPJk1Vy4aPs6R+nILoBTzMJ7MmitXtVGexXFCC8j5OpzWgyoCxzEfQQOt4hot+gjHSZZOhoLraabIEQU3EEMT70HgHl44m3KcNqUm+2SCVt8vX6E1dDdRMyzTcSCXBhRSImc6o9HkW7589Pz3cpAD8CBL3oXKkj1Ze+00xxZh+DNUMHF9SQKdEL2+en7lmNmFRmmm6jVXhGl4SchF0fcrjbnEWeQ008SSs8RZuC5fjIbWW6xm8ebCYdovlg8g+gXwsu0wmCVGbgAAAABJRU5ErkJggg=="
          alt="검색 버튼 아이콘"
          onClick={() => handleSubmit(null)}
        ></Img>
      </SearchWrapper>
    </SearchContainer>
  );
}

export default SearchBar;

const SearchContainer = styled.div`
  position: relative;
  min-width: 280px;
  border: 1px solid rgb(194, 194, 194);
  border-radius: 5px;
`;

const SearchWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 15px;

  > form {
    flex: 1;
  }

  > img {
  }
`;

const SearchForm = styled.form``;

const FormInput = styled.input`
  width: 100%;
  height: 36px;
  border: none;
  outline: none;
  color: black;
`;

const ClearInput = styled.div`
  padding: 0 5px;
  color: rgb(194, 194, 194);
  cursor: pointer;
`;

const Img = styled.img`
  color: black;
  width: 16px;
  height: 16px;
  cursor: pointer;
`;
