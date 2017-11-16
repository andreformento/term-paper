package simulations.computerdatabase // 1

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

// https://gatling.io/docs/current/quickstart/
// https://gatling.io/docs/2.3/http/http_request/
// https://hub.docker.com/r/denvazh/gatling/

class BasicSimulation extends Simulation { // 3

  //declare a scenario with a simple get request performed 5 times
//  val scn = scenario("BasicSimulation")
//      .exec(http("myRequest")
//      .post("http://localhost:8080/events/uuid456/tickets")
////      .body(StringBody("""{ "idUser": "uuid123" }"""))
////      .asJSON
//      .repeat(5)


//  val scn = scenario("myScenario")
//    .exec(
//        http("request1").
//        post("http://localhost:8080/events/uuid456/tickets").
//        body(StringBody("""{ "idUser": "uuid123"}"""))
//    ).repeat(5)


//  private val request: HttpRequestBuilder = http("myRequest").
//    post("http://localhost:8080/events/uuid456/tickets").
//    body(StringBody("""{ "idUser": "uuid123"}""")).
//    asJSON
//
//  val scn = scenario("myScenario").exec(request).repeat(5)
//
//  //run the scenario with 10 concurrent users
//  setUp(scn.users(10))

  val httpConf = http // 4
    .baseURL("http://computer-database.gatling.io") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation") // 7
    .exec(http("request_1")  // 8
    .get("/")) // 9
    .pause(5) // 10

  setUp( // 11
    scn.inject(atOnceUsers(1)) // 12
  ).protocols(httpConf) // 13

}
