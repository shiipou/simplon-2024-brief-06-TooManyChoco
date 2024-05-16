import React, { useEffect, useState } from "react";
import handleFetch from "../Services/HandleFetch";
import { FaUser } from "react-icons/fa";
import { GiCroissant } from "react-icons/gi";
import "./DetailsCard.css";

function DetailsCard() {
  //vider le useState lors de l'implantation de l'Api
  const [eventInfo, setEventInfo] = useState({
    firstName: "Foo Bar",
    date: "2024-05-16",
    viennoiseries: [{ name: "miamiam" }, { name: "nomnom" }],
  });
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
  // décommenter et mettre en fonction le useEffect
  useEffect(() => {
    // handleFetch(1).then((data) => {
    //   setUserInfo(data);
    // });
  }, []);
  return (
    <>
      <div className="container">
        <div className="content">
          {/* this is a props coming from the parent component Calender */}
          <h1>{WeekDays[new Date(eventInfo.date).getDay()]}</h1>
          <p>{eventInfo.date}</p>
          <div className="info">
            <div className="userContainer">
              <FaUser className="userIcon" />
              {/* dynamic render of user name */}
              <h2 className="userName">{eventInfo.firstName}</h2>
            </div>
            <div className="foodContainer">
              <GiCroissant className="foodIcon" />
              {/* dynamic render of food chooise */}
              <ul>
                {eventInfo?.viennoiseries.map((viennoiserie, index) => (
                  <li key={index}>
                    <h2 className="foodName">{viennoiserie.name}</h2>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default DetailsCard;
