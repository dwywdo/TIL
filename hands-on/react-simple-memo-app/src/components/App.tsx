import {ChangeEvent, useState, FC, memo, useCallback} from "react";
import styled from "styled-components"
import { MemoList } from "./MemoList"
import { useMemoList } from "../hooks/useMemoList";

export const App: FC = () => {
    const { memos, addTodo, deleteTodo } = useMemoList();
    const [text, setText] = useState<string>("");

    const onChangeText = (e: ChangeEvent<HTMLInputElement>) => setText(e.target.value)

    const onClickAdd = () => {
        addTodo(text);
        setText("");
    }

    const onClickDelete = useCallback((index: number) => {
        deleteTodo(index);
    }, [deleteTodo]);

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
