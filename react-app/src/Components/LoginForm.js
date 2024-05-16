import React from "react";

const LoginForm = () => {
  return (
    <div>
      <h3>Connexion</h3>
      <form>
        <label>Adresse</label>
        <input
          type="email"
          name="email"
          className="form-control"
          placeholder="Entrez votre adresse email"
        />
        <label>Mot de passe</label>
        <input
          type="password"
          name="password"
          className="form-control"
          placeholder="Entrez votre adresse email"
          aria-describedby="passwordHelpBlock"
        />
        <div>
          <button type="submit">Se connecter</button>
        </div>
        <div>
          <a>Mot de passe oublié ?</a>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
