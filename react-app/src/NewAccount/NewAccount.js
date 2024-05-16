// NewAccount.js
import React from 'react';

const NewAccount = () => {
  return (
    <div style={{ border: '2px solid red', padding: '20px', borderRadius: '10px', width: '300px' }}>
      <h2>Créer un compte</h2>
      <form>
        <div>
          <label htmlFor="pseudo">Pseudo :</label>
          <input type="text" id="pseudo" name="pseudo" />
        </div>
        <div>
          <label htmlFor="email">Email :</label>
          <input type="email" id="email" name="email" />
        </div>
        <div>
          <label htmlFor="password">Mot de passe :</label>
          <input type="password" id="password" name="password" />
        </div>
        <button type="submit" style={{ backgroundColor: 'red', color: 'white' }}>Création de compte</button>
      </form>
      <button style={{ backgroundColor: 'red', color: 'white' }}>S'inscrire</button>
    </div>
  );
}

export default NewAccount;
