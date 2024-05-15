import React, { useEffect, useState } from "react";
import handleFetch from "../Services/HandleFetch";
import { FaUser } from "react-icons/fa";
import { GiCroissant } from "react-icons/gi";
import "./DetailsCard.css";

function DetailsCard() {
  const [userInfo, setUserInfo] = useState([]);
  // Array corespondant aux dates du calendrier à modifier en fonction des props reçues
  const WeekDays = [
    "Dimanche",
    "Lundi",
    "Mardi",
    "Mercredi",
    "Jeudi",
    "Vendredi",
    "Samedi",
  ];
  useEffect(() => {
    handleFetch(1).then((data) => {
      setUserInfo(data);
    });
  }, []);
  return (
    <>
      <div className="container">
        <div className="content">
          {/* this is a props coming from the parent component Calender */}
          <h1>{WeekDays[new Date(userInfo.birthDate).getDay()]}</h1>
          <p>{userInfo.birthDate}</p>
          <div className="info">
            <div className="userContainer">
              <FaUser className="userIcon" />
              {/* dynamic render of user name */}
              <h2 className="userName">{userInfo.firstName}</h2>
            </div>
            <div className="foodContainer">
              <GiCroissant className="foodIcon" />
              {/* dynamic render of food chooise */}
              <h2 className="foodName">{userInfo.lastName}</h2>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default DetailsCard;
