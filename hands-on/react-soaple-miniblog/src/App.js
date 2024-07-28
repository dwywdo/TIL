import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import styled from "styled-components";
import MainPage from "./components/page/MainPage";
import PostUploadPage from "./components/page/PostUploadPage";
import PostViewPage from "./components/page/PostViewPage";

const MainTitleText = styled.p`
  font-size: 24px;
  font-weight: bold;
  text-align: center;
`;

function App() {
  return (
      <BrowserRouter>
        <MainTitleText>Dwywdo's Mini Blog</MainTitleText>
        <Routes>
          <Route index element={<MainPage />} />
          <Route path="post-upload" element={<PostUploadPage />} />
          <Route path="post/:postId" element={<PostViewPage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
