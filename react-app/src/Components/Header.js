import React, { useState } from "react";
import logo from "../assets/img/logotmc.png";
import { TbLogout } from "react-icons/tb";
import { Link, useNavigate } from "react-router-dom";
import "./header.css";


export default function Header() {
  //utilisation d'un useState pour gérer l'état du menu burger

  const [showLinks, setShowLinks] = useState(false);

  const navigate = useNavigate();

  const handleShowLinks = () => {
    setShowLinks(!showLinks);
  };

  const handleLogOut = () => {
    sessionStorage.removeItem("username")
    navigate("/")
  }

  return (
    <header className={`navbar ${showLinks ? "show-nav" : "hide-nav"}`}>
      {" "}
      {/* ternaire pour ajouter afficher ou cacher la navbar en fonction de l'état de showLinks */}
      <div className="logo-and-text-container">
        <img className="logoHeader" src={logo} alt="logo"></img>
        <ul className="navbar-links">
          <li className="navbar-item">
            <Link id="link_Styles" to="/">
              {" "}
              Planning{" "}
            </Link>
          </li>
          <li className="navbar-item">
            <Link id="link_Styles" to="/profil">
              Profil
            </Link>
          </li>
        </ul>
      </div>
      <Link to="/" className="logout-container" onClick={handleLogOut}>
        <TbLogout className="logout" />
      </Link>
      <button className="menu-burger" onClick={handleShowLinks}>
        <span className="burger-bar"></span>
      </button>
    </header>
  );
}
