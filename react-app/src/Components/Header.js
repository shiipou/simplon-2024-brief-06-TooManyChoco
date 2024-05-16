import React, { useState } from 'react';
import logo from '../assets/img/logotmc.png';
import { TbLogout } from "react-icons/tb";
import {Link}  from 'react-router-dom';
import '../Styles/header.css';


export default function Header() {

    const [showLinks, setShowLinks] = useState(false);

    const handleShowLinks = () => {
        setShowLinks(!showLinks);
    }

    return (
        <header className="navbar">
                <div className='logo-and-text-container'>
                    <img src={logo} alt='logo'></img>
                    <ul className="navbar-links">
                        <li className='navbar-item'>
                            <Link id="link_Styles" to="/"> Planning </Link> 
                        </li>
                        <li className='navbar-item'>
                            <Link id="link_Styles" to="/profil">Profil</Link>
                        </li>
                    </ul>
                </div>
            <Link to="/logout" className='logout-container'><TbLogout className='logout'/></Link>
            <button className='menu-burger' onClick={handleShowLinks}>
                <span className='burger-bar'></span>
            </button>
        </header>
    );
};

