import React from 'react';
import "./PageFormulaires.css";
import Formulaire from '../Components/Formulaire';

const PageFormulaires = () => {

    let titre1 = "J'apporte";
    let titre2 = "Sondage";

    return (
        <div >
            <h1>PAGE FORMULAIRE</h1>
            <div className='container'>
                <Formulaire titre= {titre1}/>
                <Formulaire titre= {titre2}/>
            </div>
            

        </div>
    );
};

export default PageFormulaires;