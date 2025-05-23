export const sharedThresholds = {
    http_req_duration: ['p(95)<10'],
    http_req_failed: ['rate<0.01'],
};

export const petThresholds = {
    'http_req_duration{type:pet}': ['p(95)<500'],
};

export const storeThresholds = {
    'http_req_duration{type:store}': ['p(95)<800'],
};

export const userThresholds = {
    'http_req_duration{type:user}': ['p(95)<1000'],
};