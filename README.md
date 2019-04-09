# HotelService
Application to fetch hotels data from different APIs

Prerequisites
---
* Java version "1.8.0_171"
* maven 3.3.9

How to start the HotelService application
---
1. Clone the project and navigate to the project directory
1. Run `mvn clean package` to build your application
1. Start application with `java -jar target/HotelService-1.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Try the AvailableHotel ,CrazyHotel and BestHotel APIs
--

```
  http://localhost:8080/hotels/BestHotels?fromDate=2019-04-09&toDate=2019-04-10&city=AMM
  http://localhost:8080/hotels/CrazyHotels?from=2019-04-10T00:00:00.00Z&to=2019-04-10T15:16:58.052Z&city=AMM
  http://localhost:8080/hotels/AvailableHotels?fromDate=2019-04-09&toDate=2019-04-10&city=AMM
```

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
