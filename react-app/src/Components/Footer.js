import React from "react";
import logo from "../assets/img/logotmc.png";
import "./footer.css";

export default function Footer() {
  return (
    <footer>
      <div className="footer-content"></div>
      <div className="footer-div">
        <div className="footer-text"> ©TooManyChoco Campus Dev MAIF 2024</div>
        <img className="logo" src={logo} lat="logo" />
      </div>
    </footer>
  );
}
