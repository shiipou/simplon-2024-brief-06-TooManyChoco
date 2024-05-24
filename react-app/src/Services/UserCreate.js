export async function userCreate(pseudo, prenom, email, password) {
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
      password: password
    })
  };

  // Effectuez la requête
  try{

    // Effectuez la requête en utilisant await
    const response = await fetch(myRequest, myInit);
    
    // Vérifiez si la réponse est correcte
    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message);
    }

    // Récupérez les données JSON de la réponse
    const data = await response.json();
    console.log(data);
    alert("User created successfully: " + data);
    
    // Retournez les données si nécessaire
    return data;
  } catch (error) {
    console.error('Error:', error.message);
    alert(`Error: ${error.message}`);
    // Vous pouvez choisir de relancer l'erreur si vous voulez gérer cela ailleurs
    throw error;
  }
}
  
  // fetch(myRequest, myInit)
  //   .then((response) => {
  //     if(!response.ok){
  //       return response.json().then((error) => {throw new Error(error.message)});
  //     }
  //     return response.json();
  //   })
  //   .then((data) => {
  //     console.log(data);
  //     alert("User created successfully: ",data);
  //   })
  //   .catch((error) => {
  //     console.error('Error:', error.message);
  //     alert(`Error: ${error.message}`);
  //   });
   

