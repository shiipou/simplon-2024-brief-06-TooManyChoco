export async function createEvent(choix, anonyme, username, event_date) {
    // Créez une requête avec l'URL cible
    const url = "http://localhost:8080/events";
    const myRequest = new Request(url);
  
    // Options pour la requête (par exemple, le corps de la requête)
    const myInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        event_date: event_date,
        creator: username,
        pastries: choix,
        isAnonyme: anonyme,
      }),
    };
  
    // Effectuez la requête
    fetch(myRequest, myInit)
      .then((response) => {
        // Vérifiez si la réponse est OK (statut 200-299)
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
      })
      .catch((error) => {
        console.error("Erreur lors de la requête :", error.message);
      });
  }
  