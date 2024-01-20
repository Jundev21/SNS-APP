import styled from "styled-components";
import Header from "./components/headerComponents/Header";
import Body from "./components/bodyComponents/Body";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import WriteFeedBody from "./components/bodyComponents/WriteFeedBody";
import MyFeedBody from "./components/bodyComponents/MyFeedBody";
import DetailFeedBody from "./components/bodyComponents/DetailFeedBody";
import MyDetailFeedBody from "./components/bodyComponents/MyDetailFeedBody";
import EditMyWriteFeedBody from "./components/bodyComponents/EditMyWriteFeedBody ";

function App() {
  return (
    <AppContainer>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Body />}></Route>
          <Route path="/my/feed" element={<MyFeedBody />}></Route>
          <Route path="/write/feed" element={<WriteFeedBody />}></Route>
          <Route path="/detail/feed" element={<DetailFeedBody />}></Route>
          <Route path="/detail/my/feed" element={<MyDetailFeedBody />}></Route>
          <Route path="/edit/my/feed" element={<EditMyWriteFeedBody />}></Route>
        </Routes>
      </BrowserRouter>
    </AppContainer>
  );
}

export default App;

const AppContainer = styled.div``;
