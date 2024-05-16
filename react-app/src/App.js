import { useState } from "react";
import Calendar from "react-calendar";
import "./assets/styles/App.css";
import "./assets/styles/reserve.css";
import "react-calendar/dist/Calendar.css";

function App() {
  const [date, setDate] = useState(new Date());
  const [events, setEvents] = useState([
    new Date("2024-05-09").toDateString(),
    new Date("2024-05-10").toDateString(),
    new Date("2024-05-15").toDateString(),
    new Date("2024-05-24").toDateString()
  ]);

  return (
    <div className="app">
      <div className="calendar-container myCustomCalendar">
        <Calendar onChange={setDate} value={date}
          tileClassName={({ date }) =>
            events.includes(date.toDateString())
              ? "reserved"
              : ""
          } />
        {console.log(Calendar)}
      </div>
    </div>
  );
}

export default App;
