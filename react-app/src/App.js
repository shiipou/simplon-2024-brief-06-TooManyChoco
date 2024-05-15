import { useState } from 'react';
import './styles/App.css';
import Calendar from 'react-calendar';

function App() {
  const [date, setDate] = useState(new Date());

  return (
    <div className='app'>
      <div className='calendar-container'>
        <Calendar onChange={setDate} value={date} />
        {console.log(date)}
      </div>
    </div>
  );
}

export default App;
