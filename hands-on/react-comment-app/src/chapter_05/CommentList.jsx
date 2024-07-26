import React from "react";
import Comment from "./Comment"

const comments = [
    {
        name: "Dwywdo",
        comment: "Hi, this is Euiyub Jung."
    },
    {
        name: "21stopa",
        comment: "Learning React is fun!"
    },
    {
        name: "ButterBeer",
        comment: "I'd like to learn React, too!"
    },

]

function CommentList(props) {
    return (
        <div>
            {comments.map((comment) => {
                return <Comment name={comment.name} comment={comment.comment} />
            })}
        </div>
    );
}

export default CommentList;
