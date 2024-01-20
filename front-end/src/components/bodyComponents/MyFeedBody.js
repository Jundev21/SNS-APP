import styled from "styled-components";
import { useState, useEffect } from "react";
import axios from "axios";
import { useAppSelector } from "../../hooks";
import Pagination from "../Pagination";
import RenderContent from "./RenderContent";
import BodyTitle from "./BodyTitle";
import { useNavigate } from "react-router-dom";

function MyFeedBody() {
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

  const handleDetail = (post) => {
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

  const changePage = (pageNum) => {
    console.log("change pages");
    console.log(pageNum);
    console.log(page);
    setPage(pageNum);
    // @ts-ignore
    handleGetPosts(pageNum);
  };

  const handleGetPosts = (pageNum, event) => {
    console.log("handleGetPosts");
    axios({
      url: "/api/v1/board/user?size=10&sort=id&page=" + pageNum,
      method: "GET",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        console.log("success");
        console.log(res);
        setPosts(res.data.responseBody.content);
        setTotalPage(res.data.responseBody.totalPages);
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
        <BodyTitle renderData={posts} title={"나의 게시물"} />
        <RenderContent renderData={posts} currPageNum={currPageNum} title={"나의 게시물"} />
        <Pagination setCurrPageNum={setCurrPageNum} totalPageNum={totalPageNum} currPageNum={currPageNum} />
      </BodyWrapper>
    </BodyContainer>
  );
}

export default MyFeedBody;

const BodyContainer = styled.div`
  width: 100%;
`;

const BodyWrapper = styled.div`
  width: 1200px;
  margin: auto;
  padding: 30px 0;
`;
