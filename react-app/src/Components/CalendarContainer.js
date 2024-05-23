import { useContext } from "react";
import Calendar from "react-calendar";
import "./CalendarContainer.css";
import "./reserve.css";
import "react-calendar/dist/Calendar.css";
import { DateContext } from "../Providers/DateContext";
import { useNavigate } from "react-router-dom";
import moment from 'moment-timezone';


function CalendarContainer() {
  const { date, setDate } = useContext(DateContext);
  const navigate = useNavigate();
  // création en dur d'évènements réservés pour afficher le design
  // const [events, setEvents] = useState([
  //   // new Date("2024-05-09").toDateString(),
  //   // new Date("2024-05-10").toDateString(),
  //   // new Date("2024-05-15").toDateString(),
  //   // new Date("2024-05-24").toDateString(),
  // ]);

  const handleDayClick = (date) => {
    const selectedDate = moment(date).tz('Europe/Paris').format('YYYY-MM-DD');
    console.log('Date formatée:', selectedDate);
    navigate(`/details/${selectedDate}`);    
  };


  return (
    <div className="calendar-container myCustomCalendar">
      {/* utilisation de la méthode Calendar dispo dans React */}
      <Calendar
        onChange={setDate}
        onClickDay={handleDayClick}
        value={date}
        // tileClassName={ date =>
          // date?.includes(date.toDateString()) ? "reserved" : ""
        // }
      />
    </div>
  );
}

export default CalendarContainer;
