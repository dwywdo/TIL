import {ChangeEvent, useState, FC, memo} from "react";
import styled from "styled-components"

export const App: FC = () => {
    const [text, setText] = useState<string>("");
    const [memos, setMemos] = useState<string[]>([]);

    const onChangeText = (e: ChangeEvent<HTMLInputElement>) => setText(e.target.value)

    const onClickAdd = () => {
        const newMemos = [...memos];
        newMemos.push(text);
        setMemos(newMemos);
        setText("");
    }

    const onClickDelete = (index: number) => {
        const newMemos = [...memos]
        newMemos.splice(index, 1);
        setMemos(newMemos)
    }

    return (
        <div>
            <h1>Simple Memo Application</h1>
            <input type="text" value={text} onChange={onChangeText}/>
            <SButton onClick={onClickAdd}>Add</SButton>
            <SContainer>
                <p>Memo List</p>
                <ul>
                    {memos.map((memo, index) => (
                        <li key={memo}>
                            <SMemoWrapper>
                                <p>{memo}</p>
                                <SButton onClick={() => onClickDelete(index)}>Delete</SButton>
                            </SMemoWrapper>
                        </li>
                    ))}
                </ul>
            </SContainer>
        </div>
    )
}

const SButton = styled.button`
    margin-left: 16px;
`;

const SContainer = styled.div`
    border: solid 1px #ccc;
    padding: 16px;
    margin: 8px;
`

const SMemoWrapper = styled.div`
    display: flex;
    align-items: center;
`
