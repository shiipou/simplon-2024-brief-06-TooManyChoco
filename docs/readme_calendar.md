# Lancement du projet #
cd react-app

npm i

npm start

# Rendre dynamique la réservation #
1. aller dans le fichier App.js
2. dans le fonction app()
3. utiliser :

        ```
        const [events, setEvents] = useState([
            new Date("2024-05-09").toDateString(),
            new Date("2024-05-10").toDateString(),
            new Date("2024-05-15").toDateString(),
            new Date("2024-05-24").toDateString()
        ]);
        ```
