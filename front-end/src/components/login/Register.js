import styled from "styled-components";
import {useEffect, useState} from "react";
import axios from "axios";
import LoginModal from "./LoginModal";
import RegisterModal from "./RegisterModal";


function Register() {

    const [modalOpen, setModalOpen] = useState(false);


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
        setModalOpen((e => !e));
    };

    return (
        <>
            <RegisterContainer>
                <RegisterWrapper onClick={handleModal}>
                    회원가입
                </RegisterWrapper>
                {
                    modalOpen && <RegisterModal handleModal={handleModal} modalOpen={modalOpen}/>
                }
            </RegisterContainer>
        </>
    )


}

export default Register;

const RegisterContainer = styled.div`
    padding: 0 10px;
    border-left: 1px solid black;
    color: black;
    &:hover {
        text: bold;
    }
`;

const RegisterWrapper = styled.div`
    cursor: pointer;
    &:hover {
        font-weight: bold;
    }
`


