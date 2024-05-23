import { useState } from "react";
import Calendar from "react-calendar";
import "./CalendarContainer.css";
import "./reserve.css";
import "react-calendar/dist/Calendar.css";


function CalendarContainer() {
  const [date, setDate] = useState(new Date());
  // création en dur d'évènements réservés pour afficher le design
  const [events, setEvents] = useState([
    new Date("2024-05-09").toDateString(),
    new Date("2024-05-10").toDateString(),
    new Date("2024-05-15").toDateString(),
    new Date("2024-05-24").toDateString(),
  ]);

  const handleDayClick = (date) => {
    const selectedDate = date.toISOString().split('T')[0]; // Format YYYY-MM-DD
    window.location.href = `/details/${selectedDate}`;
    console.log(selectedDate)
    
  };


  return (
    <div className="calendar-container myCustomCalendar">
      {/* utilisation de la méthode Calendar dispo dans React */}
      <Calendar
        onChange={setDate}
        onClickDay={handleDayClick}
        value={date}
        tileClassName={({ date }) =>
          events?.includes(date.toDateString()) ? "reserved" : ""
        }
      />
    </div>
  );
}

export default CalendarContainer;
