import { useState, useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashAlt, faCheckSquare, faSquare } from "@fortawesome/free-solid-svg-icons";
import { createEvent } from "../Services/eventApi";
import { useNavigate } from "react-router-dom";

import "./formulaire.css";

export default function Formulaire(props) {
  const navigate = useNavigate();

  // tableau provisoire, sera plus tard issu d'un fetch vers l'API
  let viennoiseries = [
    "Croissant",
    "Pain au chocolat",
    "Pain aux raisins",
    "Croissant aux amandes",
    "Chouquettes",
  ];

  // etat (state)
  let [choix, setChoix] = useState([]);
  let nouveauChoixInput = useRef();

  // sera utilisé par la suite
  // let [user, setUser] = useState();

  let [anonyme, setAnonyme] = useState(false);

  //logique (fonctions)

  const addChoix = (event) => {
    event.preventDefault();
    setChoix([...choix, nouveauChoixInput.current.value]);
    // console.log(choix);
    nouveauChoixInput.current.value = "";
  };

  const visibilite = () => {
    setAnonyme(!anonyme);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // console.log(choix);
    // console.log(anonyme);

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
      <h2 className="formBox_title">{props.titre}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <div className="formBox_choiceInput">
            <datalist id="viennoiseries">
              {viennoiseries.map((element, index) => (
                <option value={element} key={index}>
                  {element}
                </option>
              ))}
            </datalist>

            <input
              autoComplete="off"
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
