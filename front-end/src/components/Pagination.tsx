import styled, { css } from "styled-components";

interface DataType {
  setCurrPageNum: React.Dispatch<React.SetStateAction<number>>;
  totalPageNum: any;
  currPageNum: number;
}

function Pagination({ setCurrPageNum, totalPageNum, currPageNum }: DataType) {
  const handlePageNum = (pageNum: number) => {
    setCurrPageNum(pageNum);
  };

  const handleBeforePage = () => {
    if (currPageNum > 0) {
      setCurrPageNum((pre) => (pre -= 1));
    }
  };

  const handleNextPage = () => {
    if (currPageNum < totalPageNum.length - 1) {
      setCurrPageNum((pre) => (pre += 1));
    }
  };

  console.log("====================" + totalPageNum);

  return (
    <PageNationContainer>
      {/* {totalPageNum.length !== 0 && (
        <PaginationContianer>
          <ul>
            <Navi onClick={() => handleBeforePage()}>이전</Navi>
            {totalPageNum.map((el, idx) => {
              return (
                <PageList
                  key={idx}
                  onClick={() => handlePageNum(el)}
                  currPageColor={currPageNum === idx ? true : false}
                >
                  {el + 1}
                </PageList>
              );
            })}
            <Navi onClick={() => handleNextPage()}>다음</Navi>
          </ul>
        </PaginationContianer>
      )} */}
      {totalPageNum !== 0 && (
        <nav aria-label="Page navigation example">
          <ul className="pagination black">
            <li className="page-item">
              <a className="page-link" href="#" aria-label="Previous" onClick={() => handleBeforePage()}>
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            {[...Array(totalPageNum).keys()].map((el, idx) => {
              return (
                <li className="page-item">
                  <a key={idx} className="page-link" onClick={() => handlePageNum(el)}>
                    {el + 1}
                  </a>
                </li>
              );
            })}

            <li className="page-item">
              <a className="page-link" href="#" aria-label="Next" onClick={() => handleNextPage()}>
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      )}
    </PageNationContainer>
  );
}

export default Pagination;

const PaginationContianer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  > ul {
    padding: 0;
  }
`;

const PageList = styled.div<{ currPageColor: boolean }>`
  list-style-type: none;
  display: inline;
  padding: 0 15px;
  border-right: 1px solid black;
  cursor: pointer;
  outline: 0 none;
  &:nth-child(2) {
    border-left: 1px solid black;
  }

  ${(props) =>
    props.currPageColor
      ? css`
          color: rgb(247, 0, 0);
          font-weight: 600;
        `
      : "#000000"};

  &:hover {
    color: rgb(247, 0, 0);
  }
`;

const Navi = styled.span`
  padding: 0 10px;
  cursor: pointer;

  &:hover {
    color: rgb(247, 0, 0);
  }
`;

const PageNationContainer = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: end;
`;

//pagenation 로직
// 데이터를 100개를 받아왔다.
// 받아온 데이어 100개를 15개씩 나눠서 렌더링 하려함

// 100/15  = ceil 사용해서 반올림 7페이지 있음
// 처음부터 6페이지까지 15개씩 렌더링

// 1페이지가 됐을때 index 가 0부터 14까지 렌더링//
// firstIdex 가 0 이여야하고 LastIndex 가 14 여야함.

// 2페이지일때는
// firstIdex 가 15 여야하고 LastIndex 가 29 여야함.

// 3페이지일때
// firstIdex 가 30 여야하고 lastIdex 가 49 여야함.

// 페이지가 변경될때마다 firstIdex 는 firstIdex*=15 여야함.
// lastIdex 는 lastIdex = firstidex+perpage-1;

// 마지막 7페이지에는 5개가 렌더링됨
