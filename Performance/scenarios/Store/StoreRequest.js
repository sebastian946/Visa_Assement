import http from 'k6/http';
import {check, group} from 'k6';
import {store} from '../../data/store.js'
import {getRandomInt, randomItem} from "../../libs/helpers.js";
import {getENDPOINT} from "../../config/environment.js";

const storeJson = (json) =>{
    return {
        id: json.id,
        petId: json.petId,
        quantity: json.quantity,
        shipDate: "2025-05-21T04:45:49.667Z",
        status: json.status,
        complete: json.complete,
    }
}

export function getInvetory(){
    group('Returns pet inventories by status', function () {
        const res = http.get(`${getENDPOINT()}/store/inventory`, {
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'store' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200,
            'The response contains status approved': (r) => r.json("approved"),
            'The response contains status delivered': (r) => r.json("delivered"),});
    })
}

export function createOrder(){
    group('Create a order', function () {
        const json = randomItem(store);
    const res = http.post(`${getENDPOINT()}/store/order`, JSON.stringify(storeJson(json)),{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'store' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200,
            'The response has status': (r) => r.json("status")})
    })
}

export function findPurchaseOrderId(){
    group('Find purchase by ID', function () {
        const json = randomItem(store);
        const res = http.get(`${getENDPOINT()}/store/order/${json.id}`,{
            headers: { 'Content-Type': 'application/json' },
            tags: { type: 'store' }
        });
        check(res, {'Status is 200': (r)=> r.status === 200,
        'The response has status': (r) => r.json("status"),})
    })
}