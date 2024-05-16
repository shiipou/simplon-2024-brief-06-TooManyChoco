import { Route, Routes } from 'react-router-dom';
import './App.css';
import Header from './Components/Header';
import Footer from './Components/Footer';

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/" element={(<main><p>Content</p></main>)}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
