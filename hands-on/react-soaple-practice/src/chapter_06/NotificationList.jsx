import React from 'react';
import Notification from "./Notification"

const reservedNotifications = [
    {
        id: 1,
        message: "Hi, here's your today's schedule."
    },
    {
        id : 2,
        message: "It will be lunch time soon."
    },
    {
        id : 3,
        message: "There will be meeting soon."
    },
]

var timer;

class NotificationList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            notifications: [],
        }
    }

    componentDidMount() {
        const { notifications } = this.state;
        timer = setInterval(() => {
            if (notifications.length < reservedNotifications.length) {
                const index = notifications.length;
                notifications.push(reservedNotifications[index]);
                this.setState({ notifications: notifications });
            } else {
                this.setState({ notifications: [] });
                clearInterval(timer);
            }
        }, 1000);
    }

    componentWillUnmount() {
        if (timer) {
            clearInterval(timer);
        }
    }

    render() {
        return (
            <div>
                {this.state.notifications.map((notification) => {
                    return <Notification key={notification.id} id={notification.id} message={notification.message}/>
                })}
            </div>
        );
    }
}

export default NotificationList
