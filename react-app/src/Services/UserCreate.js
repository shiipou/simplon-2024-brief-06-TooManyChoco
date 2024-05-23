export function UserCreate(pseudo, prenom, email, password) {
  // Créez une requête avec l'URL cible
  const url = "http://localhost:8080/users";
  const myRequest = new Request(url);

  // Options pour la requête (par exemple, le corps de la requête)
  const myInit = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      username: pseudo,
      firstname: prenom,
      email: email,
      password: password,
    }),
  };

  // Effectuez la requête
  fetch(myRequest, myInit)
    .then((response) => {
      // Vérifiez si la réponse est OK (statut 200-299)
      console.log(response.status);
      if(!response) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
    })
    .catch((error) => {
      console.log(error.response)
    });
}
