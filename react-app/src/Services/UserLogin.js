// avec les users "en dur"

const users = [{id:1, pseudo:'test', password: 'Test', email: 'test@email.com'},{id:2, pseudo:'user', password: 'Test', email: 'test2@example.com'}]

export function userLogin(email, password) {
    return users.find((user)=>user.email.toLocaleLowerCase() === email.toLocaleLowerCase() && user.password === password)
}