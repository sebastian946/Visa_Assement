import http from 'k6/http';

export function randomItem(array) {
    return array[Math.floor(Math.random() * array.length)];
}

export function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

export function logResponse(response) {
    console.log(`Status: ${response.status}, Body: ${response.body}`);
}