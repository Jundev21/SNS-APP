import React, { useEffect, useState } from "react";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Notification() {
  const [page, setPage] = useState(0);
  const [render, setRender] = useState(false);
  const [alarms, setAlarms] = useState([]);
  const [totalPage, setTotalPage] = useState(0);

  const navigate = useNavigate();

  const changePage = (pageNum) => {
    console.log("change pages");
    console.log(pageNum);
    console.log(page);
    setPage(pageNum);
    handleGetAlarm(pageNum);
  };

  const handleGetAlarm = (pageNum, event) => {
    console.log("handleGetAlarm");
    axios({
      url: "/api/v1/users/notification",
      method: "GET",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        console.log("Success alram");
        console.log("success token");
        console.log(res);

        setAlarms(res.data.responseBody.notificationType);
        // setTotalPage(res.data.result.totalPages);
      })
      .catch((error) => {
        console.log(error);
        // navigate('/authentication/sign-in');
      });
  };

  // useEffect(() => {
  //   handleGetAlarm();
  // }, [alarms]);

  return (
    <NotiParent>
      <div class="bi bi-bell-fill fs-5"></div>
      {alarms.length !== 0 && <NotiChild>{alarms.length}</NotiChild>}
    </NotiParent>
  );
}

export default Notification;

const NotiParent = styled.span`
  display: flex;
  align-items: center;
  position: relative;
  cursor: pointer;
`;

const NotiChild = styled.span`
  position: absolute;
  top: -10px;
  right: -15px;
  background-color: red;
  color: white;
  font-size: 12px;
  padding: 2px 7px;
  border-radius: 80%;
`;
