export default async function handleFetch(id) {
  const response = await fetch("https://dummyjson.com/users/" + id);
  const data = await response.json();
  return data;
}
