import { useState, useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faTrashAlt,
  faCheckSquare,
  faSquare,
} from "@fortawesome/free-solid-svg-icons";
import { createEvent } from "../Services/eventApi";
import { useNavigate } from "react-router-dom";

import "./formulaire.css";

export default function Formulaire(props) {
  const navigate = useNavigate();


  
  let viennoiseries = [
    "Croissant",
    "Pain au chocolat",
    "Pain aux raisins",
    "Croissant aux amandes",
  ];






  // etat (state)
  let [choix, setChoix] = useState([]);
  let nouveauChoixInput = useRef();

  let [user, setUser] = useState();
  let [anonyme, setAnonyme] = useState(false);

  //logique (fonctions)

  const addChoix = (event) => {
    event.preventDefault();
    setChoix([...choix, nouveauChoixInput.current.value]);
    console.log(choix);
    nouveauChoixInput.current.value = "";
  };

  const visibilite = () => {
    setAnonyme(!anonyme);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log(choix);
    console.log(anonyme);

    const event = await createEvent(choix, anonyme);
    if (event) {
      navigate("/");
    }
  };

  const deleteChoice = (indexToDelete) => {
    setChoix(choix.filter((_, index) => index !== indexToDelete));
  };

  //rendu front (return: render)
  return (
    <div className="formBox">
      <h2 className="formBox_title">{props.titre} Test</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <div className="formBox_choiceInput">
            {/* <input ref={nouveauChoixInput} className='formBox_input' type="text" placeholder="Quelle viennoiserie ?"></input> */}

            <datalist id="viennoiseries">
              <option value="croissant">croissant</option>
              <option value="pain au chocolat">pain au chocolat</option>
              <option value="croissant aux amandes">
                croissant aux amandes
              </option>
              <option value="pain aux raisins">pain aux raisins</option>
            </datalist>

            <input
              autocomplete="off"
              list="viennoiseries"
              name="viennoiseries"
              ref={nouveauChoixInput}
              className="formBox_input"
              placeholder="Quelle viennoiserie ?"
            ></input>

            <button className="formBox_choiceButton" onClick={addChoix}>
              +
            </button>
          </div>

          <div className="showResult">
            {choix.map((element, index) => (
              <span key={index} className="showChoice">
                <p>{element} </p>
                <FontAwesomeIcon
                  icon={faTrashAlt}
                  style={{ color: "#c62222" }}
                  onClick={() => deleteChoice(index)}
                />
              </span>
            ))}
          </div>

          <input type="submit" value="Valider" />

          <div className="anonyme">
            <p>Anonyme</p>
            <FontAwesomeIcon
              icon={anonyme ? faCheckSquare : faSquare}
              onClick={visibilite}
              style={{ cursor: "pointer", fontSize: "24px", color: "#C62222" }}
            />
          </div>
        </div>
      </form>
    </div>
  );
}
