// NewAccount.js
import React from 'react';
import './NewAccount.css';

const NewAccount = () => {
   return (
   <div className="main-container">
     <div className="form-container">
       <h2>Créer un compte</h2>
       <form className="form-mini-container">
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
