import { check, group } from 'k6';
import { randomItem } from './libs/helpers.js';
import { sharedThresholds } from './config/thresholds.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { createNewPet, findPetById, updatePet } from "./scenarios/Pet/PetRequests.js";
import {createOrder, findPurchaseOrderId, getInvetory,} from "./scenarios/Store/StoreRequest.js";
import { createUser, getUser, loginUsers, logoutUser } from "./scenarios/User/UserRequest.js";

export const options = {
    stages: [
        { duration: '1m', target: 10 },
        { duration: '3m', target: 50 },
        { duration: '1m', target: 0 },
    ],
    thresholds: sharedThresholds
};

export function handleSummary(data) {
    return {
        "reports/summary.html": htmlReport(data),
    };
}

export default function() {
    const scenario = randomItem([
        createNewPet,
        updatePet,
        findPetById,
        getInvetory,
        createOrder,
        findPurchaseOrderId,
        createUser,
        loginUsers,
        logoutUser,
        getUser
    ]);


    scenario();
}