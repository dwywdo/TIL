import {ChangeEvent, useState, FC, memo, useCallback} from "react";
import styled from "styled-components"
import { MemoList } from "./MemoList"

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

    const onClickDelete = useCallback((index: number) => {
        const newMemos = [...memos];
        newMemos.splice(index, 1);
        setMemos(newMemos);
    }, [memos]);

    return (
        <div>
            <h1>Simple Memo Application</h1>
            <input type="text" value={text} onChange={onChangeText}/>
            <SButton onClick={onClickAdd}>Add</SButton>
            <MemoList memos={memos} onClickDelete={onClickDelete}/>
        </div>
    )
}

const SButton = styled.button`
    margin-left: 16px;
`;
