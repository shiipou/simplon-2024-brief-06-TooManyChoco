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

  return (
    <div className="calendar-container myCustomCalendar">
      {/* utilisation de la méthode Calendar dispo dans React */}
      <Calendar
        onChange={setDate}
        value={date}
        tileClassName={({ date }) =>
          events.includes(date.toDateString()) ? "reserved" : ""
        }
      />
    </div>
  );
}

export default CalendarContainer;
