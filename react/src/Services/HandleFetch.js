import { useEffect, useState } from "react";
import { FaUser } from "react-icons/fa";
import { GiCroissant } from "react-icons/gi";
import "./HandleFetch.css";

export default function HandleFetch() {
  const [userInfo, setUserInfo] = useState([]);

  const WeekDays = ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"]
  
  useEffect(() => {
    async function fetchApi() {
      try {
        // change the endpoint
        const response = await fetch("https://dummyjson.com/users/1");
        const data = await response.json();
        setUserInfo(data);
        console.log(userInfo.title);
      } catch (error) {
        console.log(error);
      }
    }
    fetchApi();
  }, []);

  return (
    <>
      <div className="container">
        <div className="content">
          {/* this is a props coming from the parent component Calender */}
          <h1>{WeekDays[new Date(userInfo.birthDate).getDay()]}</h1>
          <p>{userInfo.birthDate}</p>
          <div className="info">
            <div className="userContainer">
              <FaUser className="userIcon" />
              {/* dynamic render of user name */}
              <h2 className="userName">{userInfo.firstName}</h2>
            </div>
            <div className="foodContainer">
              <GiCroissant className="foodIcon" />
              {/* dynamic render of food chooise */}
              <h2 className="foodName">{userInfo.lastName}</h2>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
