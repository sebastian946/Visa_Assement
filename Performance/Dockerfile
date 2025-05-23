FROM grafana/k6:latest

WORKDIR /app

COPY . .

RUN wget https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js -O /app/k6-reporter.js

CMD ["sh", "-c", "mkdir -p reports && k6 run --out html=reports/summary.html load_test.js && cat /app/k6-reporter.js >> load_test.js && k6 run load_test.js"]