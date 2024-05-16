import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import PageFormulaires from './Pages/PageFormulaires';


function App() {
  return (
    <BrowserRouter>
            <Routes>
              <Route path="/formulaire" element={<PageFormulaires/>} />
            </Routes>
    </BrowserRouter>
  );
}

export default App;
