import http from 'k6/http';
import { check, group } from 'k6';
import {pets} from '../../data/pet.js';
import { getRandomInt, randomItem } from "../../libs/helpers.js";
import {getENDPOINT} from "../../config/environment.js";

const petJson = (pet) => {
    return {
        id: pet.id,
        name: pet.name,
        category: {
            id: pet.category.id,
            name: pet.category.name,
        },
        photoUrls: pet.photoUrls,
        tags: pet.tags,
        status: pet.status
    };
};

export function createNewPet() {
    group('Pet Creation', function() {
        const pet = randomItem(pets);
        const res = http.post(`${getENDPOINT()}/pet`, JSON.stringify(petJson(pet)), {
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'pet' }
        });

        check(res, {
            'Status is 200': (r) => r.status === 200,
            'Response has pet name': (r) => r.json('name')
        }) || console.error('Pet creation failed:', res.body);
    });
}

export function updatePet() {
    group('Update data pet', function() {
        const pet = randomItem(pets);
        const res = http.put(`${getENDPOINT()}/pet`, JSON.stringify(petJson(pet)),{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'pet' }
        }, {
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'pet' }
        });

        check(res, {
            'Status is 200': (r) => r.status === 200,
            'Response has pet name': (r) => r.json('name')
        }) || console.error('Pet update failed:', res.body);
    });
}

export function findPetById() {
    group('Find pet by Id', function() {
        const id = getRandomInt(0, 20);
        const res = http.get(`${getENDPOINT()}/pet/${id}`,{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'pet' }
        });

        check(res, {
            'Status is 200': (r) => r.status === 200,
            'Pet exists': (r) => r.json('id')
        });
    });
}