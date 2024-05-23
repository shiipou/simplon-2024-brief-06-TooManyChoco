// avec les users "en dur"

// const users = [{ id: 1, pseudo: 'test', password: 'Test', email: 'test@email.com' }, { id: 2, pseudo: 'user', password: 'Test', email: 'test2@example.com' }]

// export async function userLogin(email, password) {
//     return users.find((user) => user.email.toLocaleLowerCase() === email.toLocaleLowerCase() && user.password === password)
//         ? users.find((user) => user.email.toLocaleLowerCase() === email.toLocaleLowerCase() && user.password === password)
//         : alert("error.message");
// }

// avec l'API
import { baseApi } from "./_apiConfig"

// export async function userLogin(email, password) {
//     let userInfo;
//     await fetch(new URL('http://localhost:8080/login'), {
//     // return await fetch(new URL('http://localhost:8080/login'), {
//         method: 'POST',
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
//         .then(data => userInfo = data)
//         .catch(error => alert(error.message))
//         return userInfo

// }

export async function userLogin(email, password) {
    return await fetch(new URL('/login', baseApi), {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            'accept': 'application/json'
        },
        body: JSON.stringify({
            email,
            password
        })

    })
        .then(response => response.json())
        // .then(data => userInfo = data)
        // .catch(error => alert(error.message))
        // return userInfo

}

// export async function getUserByToken(token) {
//     return await fetch(new URL(`/user`, baseApi), {
//         headers: {
//             authorization: token
//         }
//     })
//         .then(response => response.json())
// }