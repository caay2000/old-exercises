# METROPOLIS Robot

## How to compile the project
You need maven to compile the project.

From the root directory, simply run ```mvn clean package``` and it will compile the project and create a jar to be executed.

## How to run the program
The jar file is located inside target directory. You can run the program by 

```java -jar target\metropolis.jar```

This will run the program console and explain the basic commands

## Commands
```
$metropolis> start [polyline]   - start the robot routine along the provided polyline");
$metropolis> stop               - stop the robot in it's current position");
$metropolis> restart            - restart the robot after an stop");
$metropolis> report             - make the robot print an instant report in it's current location");
$metropolis> example            - starts the robot with the example polyline from London West to London City");
$metropolis> exit               - stops the robot and exits the program");
```

## Statement considerations

I really enjoyed this exercise, and and take the opportunity to have fun and try new things I wanted to try. 

As explained in the technical considerations section, I used an EventBus architecture, and almost all the robot collaborators are decoupled from the main robot logic. This allowed me to introduce new functionality really quick with small modifications on the robot logic.

I also applied a Simulation Factor logic to simulate the time in this project. As the default value for the periodic report is 15 min, I didn't want to wait 15 min to check if that's working or not, so my simulation logic tries to reduce that time with a simulation factor. The default value is 1000/60. This means that each second simulates 60 seconds of real time. So, 1second = 1minute.

### Bonus included

- Bonus 1 -  fully included, when the robot in in 100m range for some stations, it publishes an instant report. The points included are:
    - Buckingham Palace [51.50155, -0.14200] - moved from [51.501299, -0.141935] to be in 100m range
    - Temple Station [51.510852, -0.114165]
    - Trafalgar Square [51.50807, -0.1280]

- Bonus 2 - partially included. Start / stop, report and also restart were fully implemented, working, and they also generate an special entry on the robot logs. The re-route was not included because it generates other problems I didn't want face (re-routing to new far away route will instantly move the robot there hundreds of kilometers?) 

- Bonus 3 (extra) - route was enhanced with some reports (distance, time and average speed) when the robot reaches one of the end points. It also turns around, and starts again the same route backwards.

## Domain considerations

Following the statement in README.statement.md file, I started researching a little bit about polylines and GMaps APIS. It had it's complications because it was my first time working with Gmaps API. After one POC, I understood that I only need a Decode function for the Polyline, and everything should be ran offline, without GMaps Platform.

After understanding this, everything was easier. Anyway, I also had to investigate how to translate GPS coordinates to meters. I found two different formulas, Haversine and Vincenty. Seems that the Haversine is less accurate, but for this exercise I don't think we should be really precise. So that's the formula I'm using in the whole project.

For the pollution meter, I used a log(x) function to simulate more GOOD levels than MODERATE, USG or UNHELATHY.

## Technical considerations

This project is created using maven. I start prototyping something and after some iterations I had lots of logic spreaded everywhere and not tested. So after some trials with polylines, simulations and the robot itself I started thinking about design.

Long time ago I was reading about EventBus architecture, and I wanted to try my simple and for sure not accurate interpretation of that. I created an EventBus that can publish events and allows subscriptions to that events. I this was, anyone can send an event, and the subscribers will receive it and process it. In this implementation, everything is synchronous, as it's only a memory eventBus, but this could be easily replaced with messages across components if needed.

```
      +---------+            +---------+            +-----------+          +---------+
      |  Route  |            |  Data   |            |   Data    |          | Station |
      | Storage |            | Storage |            | Collector |          |  Range  |
      +^-------++            +^-------++            +^---------++          +^-------++
       ^       |              ^       |              ^         |            ^       |
       |       |              |       |              |         |            |       |
   StoreRoute  |         StoreData    |        CollectData     |      StationRange  |
  PublishRoute |        PublishData   |       CollectInstant   |            |       |
       |       |              |       |              |         |            |       |
       |       |              |       |              |   OutputReport       |       |
       |  OutputReport        |  OutputReport        |    StoreData         |  StatusReport
       |       |              |       |              |         |            |       |
       |       v              |       v              |         v            |       v
+------+-------v--------------+-------v--------------+---------v------------+-------v---------+
|                        Event Bus                                                            |
+------------------^--------------------------------------------------+-----------------------+
                   ^                                                  |
                   |                                                  |
             PublishRoute                                             |
              StoreRoute                                        OutputReport
              PublishData                                       StatusReport
              CollectData                                             |
             CollectInstant                                           |
              StationRange                                            |
              RobotStatus                                             |
                   |                                                  v
               +---+---+                                         +----v-----+
               | Robot |                                         | Reporter |
               +-------+                                         +----------+

```

About testing, as explained above, I started this prototyping and playing with it, so there's a lots of parts where the test were included after the code. Some of the latests systems where created using TDD. I was also using mutation testing (pitest) for my junits.

So, there are lots of tests I'm not proud of, also some parts of the code not tested (ConsoleSystem and related parts of the RobotApplication). I spend to much time with that exercise, and I didn't want to spend more time re-designing it from scratch. So I had to adapt and use some ArgumentCaptors or tests finishing in exceptions to test some parts of the code. 

### Event Model

Those are the events the applications works with
```
- COLLECT_DATA - Collects Pollution Data in the current point, call storage to save it
    - Publisher: Robot
    - Subscribers: DataCollector  
- COLLECT_INSTANT_DATA - Collects Pollution Data in the current point, calls reporter to print it
    - Publisher: Robot, StationRange
    - Subscribers: DataCollector
- STORE_COLLECT_DATA - Stores Pollution Data for aggregated reports
    - Publisher: DataCollector
    - Subscribers: DataStorage   
- STORE_ROUTE_DATA - Stores Route Data for aggregated reports
    - Publisher: Robot
    - Subscribers: RouteStorage   
- PUBLISH_DATA_REPORT - Aggregates all stored Pollution Data and calls reporter to print it
    - Publisher: Robot
    - Subscribers: DataStorage   
- PUBLISH_ROUTE_REPORT - Aggregates all stored Route Data and calls reporter to print it
    - Publisher: Robot
    - Subscribers: RouteStorage   
- STATION_IN_RANGE - Checks if some stations are in range and calls Data Collector if needed
    - Publisher: Robot
    - Subscribers: StationRange   
- OUTPUT_REPORT - Prints the report received
    - Publisher: DataCollector, DataStorage, RouteStorage
    - Subscribers: Reporter  
- ROBOT_STATUS - calls the reporter with the status changes of the robot
    - Publisher: Robot
    - Subscribers: Reporter  
```

### Reports

Example reports :
``` 
Robot Status                - {"timestamp":1566942599,"location":{"lat":51.50487,"lng":-0.21533},"status":"START"}
Periodic Data Report        - {"timestamp":1566943500,"location":{"lat":51.50239,"lng":-0.18861},"level":"GOOD","source":"robot"}
Station Data Report         - {"timestamp":1566944657,"location":{"lat":51.502342,"lng":-0.142423},"level":"GOOD","source":"Buckingham Palace"}
Route Report                - {"timestamp":1566946466,"location":{"lat":51.51161,"lng":-0.08644},"distanceTravelled":"11.126,74","timeElapsed":"3867","averageSpeed":"2,88","source":"route"}
On-demand Report            - {"timestamp":1566947015,"location":{"lat":51.511055,"lng":-0.102545},"level":"GOOD","source":"on_demand"}
```

## Future

There are plenty of improvements for this project that could be done. 

Technical: improve the design and the test, use Spring Boot instead of plain Java, use a REST api to control the robot and retrieve reports ...

Features: create a GUI showing the route and the current robot position, make robot able to find a valid route between two points, be able to modify robot configuration from System Console ...
