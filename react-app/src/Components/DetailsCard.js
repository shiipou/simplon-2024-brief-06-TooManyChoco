import React, { useEffect, useState, useContext } from "react";
import { FaUser } from "react-icons/fa";
import { GiCroissant } from "react-icons/gi";
import "./DetailsCard.css";
import { DateContext } from "../Providers/DateContext";
import getEventDetails from "../Services/eventService";

function DetailsCard() {
    const { date, setDate } = useContext(DateContext);
    const [eventInfo, setEventInfo] = useState({});
    const [path, setPath] = useState(document.location.pathname);

    useEffect(() => {
        const path = document.location.pathname;

        function getDateFromPath(path) {
            const segments = path.split('/');
            return segments[segments.length - 1];
        }

        const dateParam = getDateFromPath(path);

        setPath(path);
        setDate(dateParam);
    }, [setDate]);

    useEffect(() => {
        if (date) {
            getEventDetails(date).then((data) => {
                setEventInfo(data);
            }).catch(error => {
                console.error("Failed to fetch event details:", error);
            });
        }
    }, [date]); // Dependency array to ensure useEffect runs when date changes

    const WeekDays = [
        "Lundi",
        "Mardi",
        "Mercredi",
        "Jeudi",
        "Vendredi",
        "Samedi",
        "Dimanche"
    ];

    return (
        <div className="container">
            <div className="content">
                <h1>{WeekDays[new Date(eventInfo.event_date).getDay() - 1]}</h1>
                <p>{new Date(eventInfo.event_date).toLocaleDateString("FR")}</p>
                <div className="info">
                    <div className="userContainer">
                        <FaUser className="userIcon" />
                        <h2 className="userName">{eventInfo?.creator?.firstname}</h2>
                    </div>
                    <div className="foodContainer">
                        <GiCroissant className="foodIcon" />
                        <ul>
                            {eventInfo?.pastryList?.map((pastry, index) => (
                                <li key={index}>
                                    <h2 className="foodName">{pastry}</h2>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default DetailsCard;
