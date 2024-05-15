import { useState } from 'react';
import Calendar from 'react-calendar';
import './assets/styles/App.css';
import './assets/styles/reserve.css';
import 'react-calendar/dist/Calendar.css';

function App() {
  const [date, setDate] = useState(new Date());

  return (
    <div className='app'>
      <div className='calendar-container myCustomCalendar'>
        <Calendar onChange={setDate} value={date} />
        {console.log(Calendar)}
      </div>
    </div>
  );
}

export default App;