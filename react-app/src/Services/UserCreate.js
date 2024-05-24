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
  
    try{
      const response = await fetch(myRequest, myInit);
      if(!response.ok){
        throw new Error(`HTTP error! Status: ${response.status}`)
      } 

      alert('User created successfully!');
      
    } catch (error) {
      // console.error("Erreur de récupération des données :", error.message);
      alert('User creation aborted!');
      throw error;
    }
    
}
   

