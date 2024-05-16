<<<<<<< HEAD
import React from 'react';
import NewAccount from './NewAccount/NewAccount.js';
import './styles/App.css';
=======
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import PageFormulaires from './Pages/PageFormulaires';
>>>>>>> 66c77c85 (12 création de chaque sous formulaires composant réutilisable (#55))


function App() {
  return (
<<<<<<< HEAD
 <div className="App">
      <NewAccount />
    </div>
=======
    <BrowserRouter>
            <Routes>
              <Route path="/formulaire" element={<PageFormulaires/>} />
            </Routes>
    </BrowserRouter>
>>>>>>> 66c77c85 (12 création de chaque sous formulaires composant réutilisable (#55))
  );
}

export default App;
