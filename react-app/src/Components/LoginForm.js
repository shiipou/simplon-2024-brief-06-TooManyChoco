import { useCallback } from "react";
import "../styles/login.css";

const LoginForm = () => {
  const handleSubmit = useCallback(async (event) => {
    event.preventDefault();
  });

  return (
    <div className="main-container">
      <div className="form-container">
          <h3>Connexion</h3>
          <form>
            <div>
              <label>Adresse</label>
              <input
                type="email"
                name="email"
                className="form-control"
                placeholder="Entrez votre adresse email"
              />
            </div>
            <div>
              <label>Mot de passe</label>
              <input
                type="password"
                name="password"
                className="form-control"
                placeholder="Entrez votre mot de passe"
                aria-describedby="passwordHelpBlock"
              />
            </div>
            <div className="login_button">
                <button type="submit">Se connecter</button>
            </div>
            <div className="mot_passe_oublie">
                <a>Mot de passe oublié ?</a>
            </div>
          </form>
      </div>
    </div>
  );
};

export default LoginForm;
