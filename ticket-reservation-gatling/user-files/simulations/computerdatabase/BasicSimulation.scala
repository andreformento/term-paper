package simulations.computerdatabase // 1

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

// https://gatling.io/docs/current/quickstart/
// https://gatling.io/docs/2.3/http/http_request/
// https://gatling.io/docs/2.3/general/simulation_setup
// https://hub.docker.com/r/denvazh/gatling/

class BasicSimulation extends Simulation { // 3

  val hostname = System.getProperty("hostname_test")
  val httpConf = http // 4
    .baseURL(hostname) // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation") // 7
    .exec(http("request_1")  // 8
         .post("/events/uuid456/tickets")
         .body(StringBody("""{ "idUser": "uuid123", "count": 2}"""))
         .asJSON
      )
    .pause(0) // 10

    before {
      printf("\n############ HOSTNAME: ")
      printf(hostname)
      printf("\n\n\n\n")
    }

  setUp(
    scn.inject(
    nothingFor(4 /*seconds*/), // 1
    atOnceUsers(10), // 2
    rampUsers(10) over(5 /*seconds*/), // 3
    constantUsersPerSec(20) during(1 /*seconds*/), // 4
    constantUsersPerSec(20) during(1 /*seconds*/) randomized, // 5
    splitUsers(1000) into(rampUsers(10) over(1 /*seconds*/)) separatedBy(1 /*seconds*/), // 8
    splitUsers(1000) into(rampUsers(10) over(1 /*seconds*/)) separatedBy atOnceUsers(30), // 9
    heavisideUsers(1000) over(20 /*seconds*/) // 10
      )
  ).protocols(httpConf) // 13

}
