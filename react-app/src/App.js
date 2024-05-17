import { BrowserRouter, Route, Routes } from "react-router-dom";
// import "./App.css";
import CalendarContainer from "./Components/CalendarContainer";
import PageFormulaires from "./Pages/PageFormulaires";
import DetailsCard from "./Components/DetailsCard";
import NewAccount from './NewAccount/NewAccount.js';

function App() {
  return (
    <div className="app">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<CalendarContainer />} />
          <Route path="/formulaire" element={<PageFormulaires />} />
          <Route path="/details" element={<DetailsCard />} />
          <Route path="/register" element={<NewAccount />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
