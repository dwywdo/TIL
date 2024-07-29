import {useState, memo, useCallback, useContext} from "react";
import {useEffect} from "react";

import {ColoredMessage} from "./components/ColoredMessage";
import {CssModules} from "./components/CssModules";
import {StyledJsx} from "./components/StyledJsx";
import {StyledComponents} from "./components/StyledComponents";
import {Emotion} from "./components/Emotion";
import {TailwindCss} from "./components/TailwindCss";

import {Child1} from "./components/Child1";
import {Child4} from "./components/Child4";

import { Card } from "./components/Card";
import {AdminFlagContext} from "./components/providers/AdminFlagProvider";

export const App = memo(() => {
    const [num, setNum] = useState(0);

    const onClickButton = () => {
        // setNum(num + 1);
        setNum((prevNum) => prevNum + 1)
    }

    useEffect(() => {
        alert("num is updated")
    }, [num]);

    /**
     const contentStyle = {
     color: "blue",
     fontSize: "20px"
     };
     */

    /**
     const contentPinkStyle = {
     color: "pink",
     fontSize: "20px"
     }
     */

    /**
     * Re-rendering Issue
     */
    console.log("APP Rendered");
    const [state, setState] = useState(0)
    const clickButton = () => {
        setState((prev) => prev + 1);
    }
    const clickReset = () => {
        setState(0);
    }
    const memoizedClickReset = useCallback(() => {
        setState(0)
    }, [])

    /**
     * Global State
     */

    const { isAdmin, setIsAdmin } = useContext(AdminFlagContext);
    const onClickSwitch = () => setIsAdmin(!isAdmin)

    return (
        <>
            <h1 style={{color: "red"}}>안녕하세요!</h1>
            <ColoredMessage color="blue">잘 지내시죠?</ColoredMessage>
            <ColoredMessage color="pink">잘 지냅니다!</ColoredMessage>
            <button onClick={onClickButton}>버튼</button>
            <p>{num}</p>
            <CssModules></CssModules>
            <StyledJsx></StyledJsx>
            <StyledComponents></StyledComponents>
            <Emotion></Emotion>
            <TailwindCss></TailwindCss>
            <button onClick={clickButton}>Count Up</button>
            <p>{state}</p>
            <Child1 onClickReset={memoizedClickReset}/>
            <Child4/>
            {/* Admin Flag true / false to differentiate printed string*/}
            {isAdmin ? <span>Admin</span> : <span>NO Admin</span>}
            <button onClick={onClickSwitch}>Switch</button>
            <Card isAdmin={isAdmin} />
        </>
    );
});
