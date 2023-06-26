# Rewards Program
## Description
This is a simple rewards program that calculates the rewards points earned by a customer over a time range ( 3-month period by default).
## API Endpoints
* Calculate rewards within a time range
    ```bash
    GET /api/v1/rewards/{customerId}?startDate={startDate}&endDate={endDate}
    ```
    #### Example Response
    ```bash
    {"customerId":1,"monthlyRewardsList":[{"customerId":1,"yearMonth":"2023-6","rewards":30},{"customerId":1,"yearMonth":"2023-5","rewards":432}],"totalRewards":462}
* Calculate rewards without time range (default a 3-month range)
    ```bash
    GET /api/v1/rewards/{customerId}
    ```
    #### Example Response
    ```bash
    {"customerId":1,"monthlyRewardsList":[{"customerId":1,"yearMonth":"2023-6","rewards":30},{"customerId":1,"yearMonth":"2023-5","rewards":432}],"totalRewards":462}