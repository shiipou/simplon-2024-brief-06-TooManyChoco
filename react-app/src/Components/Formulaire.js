import { useState, useRef, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashAlt, faCheckSquare, faSquare } from "@fortawesome/free-solid-svg-icons";
import { createEvent } from "../Services/eventApi";
import { useNavigate } from "react-router-dom";

import "./formulaire.css";

export default function Formulaire(props) {
  const navigate = useNavigate();

  // Remplacer ce tableau par un fetch à la bdd via l'api
  const [pastries, setPastries] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/pastries')
      .then((res) => res.json())
      .then((data) => {
        setPastries(data);
      })
      .catch((error) => console.error("Error fetching pastries:", error));
  }, []);
  
  // etat (state)
  let [choix, setChoix] = useState([]);
  let nouveauChoixInput = useRef();

  // sera utilisé par la suite
  let [user, setUser] = useState(sessionStorage.getItem("username"));
  let [event_date, setEvent_date] = useState("2024-06-03");

  let [anonyme, setAnonyme] = useState(false);

  //logique (fonctions)

  const addChoix = (event) => {
    event.preventDefault();
    setChoix([...choix, nouveauChoixInput.current.value]);
    nouveauChoixInput.current.value = "";
  };

  const visibilite = () => {
    setAnonyme(!anonyme);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const event = await createEvent(choix, anonyme, user, event_date);
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
              {pastries?.map((element, index) => (
                <option value={element.pastry_name} key={index}>
                  {element.pastry_name}
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
