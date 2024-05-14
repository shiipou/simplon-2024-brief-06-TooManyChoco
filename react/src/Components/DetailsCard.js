import React from "react";
import "./DetailsCard.css";
import { FaUser } from "react-icons/fa";
import { GiCroissant } from "react-icons/gi";

function DetailsCard() {
  return (
    <div className="container">
      <div className="content">
        <h1>Lundi</h1>
        <p>13.05.2024</p>
        <FaUser />
        <GiCroissant />
      </div>
    </div>
  );
}

export default DetailsCard;
