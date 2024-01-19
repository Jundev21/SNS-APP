import styled from "styled-components";
import { useState, useEffect } from "react";
import axios from "axios";
import { useAppSelector } from "../../hooks";
import Pagination from "../Pagination";
import RenderContent from "./RenderContent";
import BodyTitle from "./BodyTitle";
import { useNavigate } from "react-router-dom";

function Body() {
  const { searchWord, orderCommand } = useAppSelector((state) => state.searchState);
  const [renderData, setRendering] = useState([]);
  const [currPageNum, setCurrPageNum] = useState(0);
  const [totalPageNum, setTotalPageNum] = useState([]);
  //===========================================

  const [page, setPage] = useState(0);
  const [render, setRender] = useState(false);
  const [posts, setPosts] = useState([]);
  const [totalPage, setTotalPage] = useState(0);

  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [open, setOpen] = useState(false);
  const [dialogTitle, setDialogTitle] = useState("");
  const [dialogMessage, setDialogMessage] = useState("");
  const navigate = useNavigate();

  const handleDetail = (post: any) => {
    console.log("handleDetail");
    console.log(post);
    navigate("/post-detail", { state: post });
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const changePage = (currPageNum: any) => {
    console.log("change pages");
    console.log(currPageNum);
    console.log(page);
    setPage(currPageNum);
    // @ts-ignore
    handleGetPosts(currPageNum);
  };

  const handleGetPosts = (pageNum: any, event: any) => {
    console.log("handleGetPosts");
    axios({
      url: "/api/v1/board?size=10&sort=id&page=" + currPageNum,
      method: "GET",
      // headers: {
      //   Authorization: "Bearer " + localStorage.getItem("token"),
      // },
    })
      .then((res) => {
        console.log("success");
        console.log(res);
        setPosts(res.data.responseBody.content);
        setTotalPageNum(res.data.responseBody.totalPages);
      })
      .catch((error) => {
        console.log(error);
        // navigate("/authentication/sign-in");
      });
  };

  useEffect(() => {
    // @ts-ignore
    handleGetPosts();
  }, []);

  return (
    <BodyContainer>
      <BodyWrapper>
        <BodyTitle renderData={posts} title={"전체 게시물"} />
        <RenderContent renderData={posts} currPageNum={currPageNum} title={"전체 게시물"} />
        <Pagination setCurrPageNum={setCurrPageNum} totalPageNum={totalPageNum} currPageNum={currPageNum} />
      </BodyWrapper>
    </BodyContainer>
  );
}

export default Body;

const BodyContainer = styled.div`
  width: 100%;
  height: 60vh;
`;

const BodyWrapper = styled.div`
  height: 100%;
  width: 1200px;
  margin: auto;
  padding: 30px 0;
`;
