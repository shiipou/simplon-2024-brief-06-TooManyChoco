
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import PageFormulaires from './Pages/PageFormulaires';
import DetailsCard from "./Components/DetailsCard";


function App() {
  return (
    <BrowserRouter>
            <Routes>
              <Route path="/formulaire" element={<PageFormulaires/>} />
              <Route path="/details" element={<DetailsCard/>} />
            </Routes>
    </BrowserRouter>
  );
}

export default App;
