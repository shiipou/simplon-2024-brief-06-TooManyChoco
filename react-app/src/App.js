import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Formulaire from './Components/Formulaire';
import Formulairetest from './Components/Formulairetest';
import PageFormulaires from './Pages/PageFormulaires';


function App() {
  return (
    <BrowserRouter>
            <Routes>
              <Route path="/" element={<h1>OUIIIII</h1>} />
              <Route path="/testFormulaire" element={<Formulaire/>} />
              <Route path="/formulaire" element={<PageFormulaires/>} />
              <Route path="/formulairetest" element={<Formulairetest/>} />
            </Routes>
    </BrowserRouter>
  );
}

export default App;
