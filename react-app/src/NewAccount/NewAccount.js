// NewAccount.js
import React, { useCallback } from 'react';
import './NewAccount.css';
import { useNavigate } from 'react-router-dom';

const NewAccount = () => {

  const navigate = useNavigate();

  const handleSubmitRegister = useCallback(
    async (e) => {
      e.preventDefault();
  
      //récupérer les données du formulaire
      const { pseudo, prenom, email, password } = Object.fromEntries(
        new FormData(e.target)
      );
  
      //que ça les envoie dans l'api pour les stocker dans notre BDD
      if(pseudo && prenom && email && password) {
        const newUser = await createUser(pseudo, prenom, email, password);
        
        //ça signale que la création a bien été prise en compte
        
        //que ça redirige sur le formulaire de login
        if (newUser) {
          navigate("/login");
        }
      }
    }
  
  )

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
