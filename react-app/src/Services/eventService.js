export default async function getEventDetails(id) {
  const response = await fetch("http://127.0.0.1:8080/event/" + id);
  const data = await response.json();
  return data;
  // fetch("http://127.0.0.1:8080/event/" + id)
  //   .then((res) => res.json())
  //   .then((data) => console.log(data))
  //   .catch((err) => console.log(err));
}
