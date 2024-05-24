// NewAccount.js
import React, { useCallback, useState } from 'react';
import './NewAccount.css';

import { useNavigate } from "react-router-dom";
import { userCreate } from '../Services/userCreate';


function NewAccount() {
  const navigate = useNavigate();
  const [pseudo, setPseudo] = useState('');
  const [prenom, setPrenom] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);



async function handleSubmitRegister(){
  try {
    //récupérer les données du formulaire
    const { pseudo, prenom, email, password } = Object.fromEntries(
      new FormData(e.target)
    );
    await userCreate(pseudo, prenom, email, password);
    setError(null); // Réinitialiser l'erreur en cas de succès
    alert('User created successfully!');
  } catch (error) {
    setError(error.message);
    console.log(error); // Mettre à jour l'erreur en cas d'échec
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
       
      <div>
      {error && <p style={{ color: 'red' }}>{error}</p>} {/* Affiche le message d'erreur */}
      </div>
    </div>
   );
}
 

export default NewAccount;
