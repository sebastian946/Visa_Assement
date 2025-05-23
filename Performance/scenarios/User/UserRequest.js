import http from 'k6/http';
import {check, group} from 'k6';
import {getRandomInt, randomItem} from "../../libs/helpers.js";
import {getENDPOINT} from "../../config/environment.js";
import {users} from "../../data/user.js";

const json = (body) =>{
    return {
        id: body.id,
        username: body.username,
        firstName: body.firstName,
        lastName: body.lastName,
        email: body.email,
        password: body.password,
        phone: body.phone,
        userStatus: body.userStatus,
    }
}

export function createUser() {
    group("Create a new user", function() {
        const body = randomItem(users);
        const res = http.post(`${getENDPOINT()}/user`, JSON.stringify(json(body)),{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'user' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200}
                    )
    })
}



export function loginUsers(){
    group("Login a user", function() {
        const body = randomItem(users);
        const {username,password} = body;
        const res = http.get(`${getENDPOINT()}/user/login?username=${username}&password=${password}`,{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'user' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200,
        'The reponse is ': (r)=> r})
    })
}
export function logoutUser(){
    group("Logout a user", function() {
        const res = http.get(`${getENDPOINT()}/user/logout`,{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'user' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200,})
    })
}
export function getUser(){
    group("Getting user", function() {
        const body = randomItem(users);
        const res = http.get(`${getENDPOINT()}/user/${body.username}`,{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'user' }
        })
        check(res, {'Status is 200': (r)=> r.status === 200,})
    })
}