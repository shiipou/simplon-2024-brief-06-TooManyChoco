import React, { useEffect, useState } from "react";
import handleFetch from "../Services/eventService"; // Service pour gérer les requêtes
import { FaUser } from "react-icons/fa"; // Icône utilisateur
import { GiCroissant } from "react-icons/gi"; // Icône croissant
import "./DetailsCard.css"; // Style pour DetailsCard

function DetailsCard() {
  // Initialisation des informations de l'événement avec des données factices
  const [eventInfo, setEventInfo] = useState({
    // firstName: "Foo Bar",
    // date: "2024-05-16",
    // viennoiseries: [{ name: "miamiam" }, { name: "nomnom" }],
  });
  // Jours de la semaine à afficher
  const WeekDays = [
    "Lundi",
    "Mardi",
    "Mercredi",
    "Jeudi",
    "Vendredi",
    "Samedi",
    "Dimanche"
  ];
  // Effet pour charger les données de l'événement (commenté pour le moment)
  useEffect(() => {
    handleFetch("2024-05-16").then((data) => {
      setEventInfo(data);
    });
  });

  let dateFormatted = new Date(eventInfo.event_date).toLocaleDateString("FR");
  let dateUs = new Date(eventInfo.event_date)
  
  return (
    <>
      <div className="container">
        <div className="content">
          {/* Jour de l'événement */}
          <h1>{WeekDays[dateUs.getDay() - 1]}</h1>
          {/* Date de l'événement */}
          <p>{dateFormatted}</p>
          <div className="info">
            <div className="userContainer">
              <FaUser className="userIcon" />
              {/* Nom de l'utilisateur */}
              <h2 className="userName">{eventInfo?.creator?.firstname}</h2>
            </div>
            <div className="foodContainer">
              <GiCroissant className="foodIcon" />
              {/* Liste des viennoiseries */}
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
    </>
  );
}

export default DetailsCard;