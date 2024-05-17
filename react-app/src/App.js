
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Header from './Components/Header';
import Footer from './Components/Footer';
import PageFormulaires from './Pages/PageFormulaires';
import DetailsCard from "./Components/DetailsCard";


function App() {
  return (
    <BrowserRouter>
          <Header/>
            <Routes>
              <Route path="/formulaire" element={<PageFormulaires/>} />
              <Route path="/details" element={<DetailsCard/>} />
            </Routes>
          <Footer/>
    </BrowserRouter>
  );
}

export default App;
