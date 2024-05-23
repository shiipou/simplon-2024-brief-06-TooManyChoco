import { useCallback, useContext } from "react";
import "./loginForm.css";
import { UserContext } from "../Providers/UserContext";
import { useNavigate } from "react-router-dom";
import { userLogin } from "../Services/UserLogin";

const LoginForm = () => {
  const { setUserToken } = useContext(UserContext);
  const navigate = useNavigate();

  const handleSubmit = useCallback(
    async (event) => {
      event.preventDefault();

      const { email, password } = Object.fromEntries(
        new FormData(event.target)
      );

      if (email && password) {
        const user = await userLogin(email, password);
        console.log("connexion réussie");

        if (user) {
          setUserToken(user.token);
          navigate("/");
        }
      }
    },
    [navigate, setUserToken]
  );

  return (
    <div className="main-container">
      <div className="form-container">
        <h3>Connexion</h3>
        <form onSubmit={handleSubmit}>
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
