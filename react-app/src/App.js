import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./Components/Header";
import Footer from "./Components/Footer";
import PageFormulaires from "./Pages/PageFormulaires";
import DetailsCard from "./Components/DetailsCard";
import NewAccount from './NewAccount/NewAccount.js';
import LoginForm from "./Components/LoginForm";
import { UserContext } from "./Providers/UserContext";
import { DateContext } from "./Providers/DateContext";
import { useState } from "react";
import CalendarContainer from "./Components/CalendarContainer.js";

function App() {

  const [userToken, setUserToken] = useState();
  const [date, setDate] = useState();


  return (
    <div className="app">
      <UserContext.Provider
        value={{
          userToken: userToken,
          setUserToken: setUserToken,
        }}
      >
      <DateContext.Provider
        value = {{
          date,
          setDate,
        }}
      >
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<CalendarContainer />} />
          <Route path="/formulaire" element={<PageFormulaires />} />
          <Route path="/details" element={<DetailsCard />} />
          <Route path="/register" element={<NewAccount />} />
          <Route path="/login" element={<LoginForm />} />
        </Routes>
        <Footer />
      </BrowserRouter>
      </DateContext.Provider>
      </UserContext.Provider>
    </div>
  );
}

export default App;
