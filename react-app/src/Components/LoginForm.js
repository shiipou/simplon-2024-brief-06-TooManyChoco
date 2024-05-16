import { useCallback } from "react";

const LoginForm = () => {
  const handleSubmit = useCallback(async (event) => {
    event.preventDefault();
  });

  return (
    <div>
      <h3>Connexion</h3>
      <form onSubmit={handleSubmit}>
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
