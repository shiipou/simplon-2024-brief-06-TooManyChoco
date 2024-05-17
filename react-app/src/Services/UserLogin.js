// avec les users "en dur"

const users = [{id:1, pseudo:'test', password: 'Test', email: 'test@email.com'},{id:2, pseudo:'user', password: 'Test', email: 'test2@example.com'}]

export function userLogin(email, password) {
    return users.find((user)=>user.email.toLocaleLowerCase() === email.toLocaleLowerCase() && user.password === password)
}

// avec l'API
// import { baseApi } from "./_apiConfig"

// export async function getUserById(id) {
//     return await fetch(new URL(`/users/${id}`, baseApi))
//         .then(response => response.json())
// }

// export async function userLogin(email, password) {
//     return await fetch(new URL('/login', baseApi), {
//         method: 'get',
//         headers: {
//             'content-type': 'application/json',
//             'accept': 'application/json'
//         },
//         body: JSON.stringify({
//             email,
//             password
//         })
//     })
//         .then(response => response.json())
// }

// export async function getUserByToken(token) {
//     return await fetch(new URL(`/user`, baseApi), {
//         headers: {
//             authorization: token
//         }
//     })
//         .then(response => response.json())
// }