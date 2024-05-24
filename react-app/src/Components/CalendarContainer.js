import { useEffect, useState } from "react";
import Calendar from "react-calendar";
import "./CalendarContainer.css";
import "./reserve.css";
import "react-calendar/dist/Calendar.css";
import { useNavigate } from "react-router-dom";
import LoginForm from "./LoginForm";

function CalendarContainer() {
  const [date, setDate] = useState(new Date());
  const navigate = useNavigate();
  const [events, setEvents] = useState([]);
 
  
  
  useEffect(() => {
    fetch('http://localhost:8080/events')
      .then((res) => res.json())
      .then((data) => {
        // Convert timestamp to date string for each event
        const eventDates = data.map(event => new Date(event.event_date).toDateString());
        setEvents(eventDates);
      })
      .catch((error) => console.error("Error fetching events:", error));
  }, []);
  
  return (
    (sessionStorage.getItem("username") ? <div className="calendar-container myCustomCalendar">
    {/* utilisation de la méthode Calendar dispo dans React */}
    <Calendar
      onChange={setDate}
      value={date}
      tileClassName={({ date }) =>
        events.includes(date.toDateString()) ? "reserved" : ""
      }
    />
  </div> : <LoginForm />)
  );
}

export default CalendarContainer;
