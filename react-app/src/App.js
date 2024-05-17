import React from 'react';
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import PageFormulaires from './Pages/PageFormulaires';
import DetailsCard from "./Components/DetailsCard";
import NewAccount from './NewAccount/NewAccount.js';


function App() {
  return (
    <BrowserRouter>
            <Routes>
              <Route path="/formulaire" element={<PageFormulaires/>} />
              <Route path="/details" element={<DetailsCard/>} />
              <Route path="/register" element={<NewAccount />} />
            </Routes>
    </BrowserRouter>
  );
}

export default App;
