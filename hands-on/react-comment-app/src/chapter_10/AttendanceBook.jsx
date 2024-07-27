import React from 'react';

const students = [
    {
        id: 1,
        name: "Euiyub",
    },
    {
        id: 2,
        name: "Woori",
    },
    {
        id: 3,
        name: "dwywdo",
    },
    {
        id: 4,
        name: "h_our",
    },
]

function AttendanceBook(props){
    return (
        <ul>
            {students.map((student) => {
                return <li key={student.id}>{student.name}</li>
            })}
        </ul>
    );
}

export default AttendanceBook
