import { useState } from 'react';
import Calendar from 'react-calendar';
import './assets/styles/App.css';
import 'react-calendar/dist/Calendar.css';

function App() {
  const [date, setDate] = useState(new Date());

  return (
    <div className='app'>
      <div className='calendar-container'>
        <Calendar onChange={setDate} value={date} />
      </div>
    </div>
  );
}

export default App;