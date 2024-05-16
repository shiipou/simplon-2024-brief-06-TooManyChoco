import { useState, useRef } from 'react';
import { useNavigate } from "react-router-dom";
import { createEvent } from "../Services/eventApi";



import './formulaire.css'

export default function Formulairetest(props) {

  const navigate = useNavigate();
    
   

    

    

    // etat (state)
    let [choix, setChoix] = useState([]);
    let nouveauChoixInput = useRef();


    let [user, setUser] = useState();
    let [anonyme, setAnonyme] = useState(false);




    //logique (fonctions)

    const addChoix = (event) => {
        event.preventDefault();
        setChoix([...choix, nouveauChoixInput.current.value])
        console.log(choix);
        nouveauChoixInput.current.value = "";
    }

    const visibilite = () => {
      setAnonyme(!anonyme);
    }

    const handleSubmit = async e => {
        e.preventDefault();

        console.log(choix);
        console.log(anonyme);

        const event = await createEvent()
            if(event){
                
                navigate('/')
            }

    }


    //rendu front (return: render)
   return (
    <div className='formBox'>
      <h2 className='formBox_title'>{props.titre} Test</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <div className='formBox_choiceInput'>


            <datalist id="viennoiseries">  
              <option value="croissant">croissant</option>
              <option value="pain au chocolat">pain au chocolat</option>
              <option value="croissant aux amandes">croissant aux amandes</option>
              <option value="pain aux raisins">pain aux raisins</option> 
            </datalist>

            <input autocomplete='off' list='viennoiseries' name='viennoiseries'
                    ref={nouveauChoixInput} className='formBox_input'  placeholder="Quelle viennoiserie ?"></input>

            
                       
            <button className='formBox_choiceButton' onClick={addChoix}>+</button>
          </div>

            <div >
                {
                    choix.map((element, index) => <p className='showChoice' key={index}>{element}  </p>)
                }
            </div>

          <input type="submit" value="Valider" />
          <div>
         
            <p>Anonyme</p>
            <input type="checkbox" onClick={visibilite}></input>





          </div>
        </div>
      </form>
    </div>
  );





};

