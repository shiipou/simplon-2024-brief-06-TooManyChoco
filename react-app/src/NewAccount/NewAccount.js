// NewAccount.js
import React from 'react';
import './NewAccount.css';
import { userCreate } from '../Services/userCreate';
import { useNavigate } from "react-router-dom";



const NewAccount = () => {
  const navigate = useNavigate();


  const handleSubmitRegister = 
    async (e) => {
      e.preventDefault();
  
      //récupérer les données du formulaire
      const { pseudo, prenom, email, password } = Object.fromEntries(
        new FormData(e.target)
      );

      //envoie dans l'api pour stocker les données dans notre BDD
      try{
        if(pseudo && prenom && email && password) {
         
            await userCreate(pseudo, prenom, email, password);
            navigate("/login");
         
        }
      } catch(error) {
        console.error(error.message);
      }
      }

   return (
   <div className="main-container">
     <div className="form-container">
       <h2>Créer un compte</h2>
       <form className="form-mini-container" onSubmit={handleSubmitRegister}>
         <div>
           <label htmlFor="pseudo">Pseudo :</label>
           <input type="text" id="pseudo" name="pseudo" />
         </div>
         <div>
            <label htmlFor="prenom">Prénom :</label>
             <input type="text" id="prenom" name="prenom" />
         </div>
         <div>
           <label htmlFor="email">Email :</label>
           <input type="email" id="email" name="email" />
         </div>
         <div>
           <label htmlFor="password">Mot de passe :</label>
           <input type="password" id="password" name="password" />
         </div>
         <button type="submit">S'enregistrer</button>
       </form>
     </div>
     </div>
   );
 }

export default NewAccount;
