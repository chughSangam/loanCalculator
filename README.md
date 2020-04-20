# loanCalculator

This application consists of only one POST endpoint as mentioned below:

 __POST: /loan/schedules/fetch__

Expeceted body for the fetch point will look like below:
> {
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2020-01-01T00:00:01Z"
}
