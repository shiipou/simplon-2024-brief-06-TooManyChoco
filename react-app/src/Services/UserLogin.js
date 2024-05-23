import { baseApi } from "./_apiConfig"



export async function userLogin(email, password) {
    return await fetch(new URL('/login', baseApi), {
    // return await fetch(new URL('http://localhost:8080/login'), {
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
        .catch(error => alert(error.message))

}

export async function getUserByToken(token) {
    return await fetch(new URL(`/user`, baseApi), {
        headers: {
            authorization: token
        }
    })
        .then(response => response.json())
}