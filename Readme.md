# Swellmap App [http://www.swellmap.com](http://www.swellmap.com)

[Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#code)

A simple data miner used to persist daily forecast data and ratings to a database table. This data is intended to be used to draw reports on boating weather conditions 
over a period.

## Development Environment Installation

The following steps explain how to configure your development environment for Swellmap App.

### Required Components

Ensure the following components are installed before setting up your environment.

  1. [Git](https://git-scm.com/)
  2. [SQLite](https://www.sqlite.org/index.html)
  3. [Java SE Development Kit 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

### Development Tools

Your preferred Java IDE and SQLite editor is required to work on this project

### Java Libraries

The following libraries/tool-kits were used to write the application

  1. [Jsoup](https://jsoup.org/)
  2. [json-simple] (https://code.google.com/archive/p/json-simple/)
  3. [SQLite JDBC] (https://bitbucket.org/xerial/sqlite-jdbc/src/default/)
  
### Development Environment Setup

The following points detail the steps to setup the development environment.

  1. Clone the swellmap-app repository to your local drive using the following command
    * *git clone https://<username>@bitbucket.org/wwalkley/swellmapapp.git swellmapapp*
  2. Open command prompt at the folder location and run the following command to build:
    * *mvn clean package*
  3. Execute the following command to run the unit tests:
    * *mvn test* (Note, the config.json found under src/test/resources is already configured for tests)

## Configuration (config.json)

A single configuration file is used to store all configuration. The following points describe the configuration properties and their purpose.

  1. *name* - The name of the application. Used for testing purposes. 
  2. *locationsPath* - The list of regions and locations to mine data from.
  3. *db.path* - The path to the sqlite database file.
  4. *http.proxy.enabled* - Set to true to use a proxy connection. Set to false to ignore proxy.
  5. *http.proxy.host* - The proxy host name.
  6. *http.proxy.port* - The proxy port.

## Locations (locations.json)

The locations.json file contains a JSON array of regions which each store an array of locations to mine.

```json
{
    "Regions": [
        {
            "Name": "Auckland East Coast",
            "Locations": [
                "west-waimate-channel",
                "waiheke-channel",
                "the-noises",
                "the-needles",
                "te-arai-point"
            ]
        },
        {
            "Name": "Auckland West Coast",
            "Locations": [
                "waikato-shelf-break",
                "waikato-river-mouth"
            ]
        }
    ]
}
```

## Forecasts table structure in database

The forecasts database table is used to store forecast data for each location specified in the locations.json file. Table structure and description
is provided below.

Column Name | Data Type | Description
--- | --- | ---
LOCATION | TEXT | The name of the location that was mined.
DATE | TEXT | The forecast date.
TIME | TEXT | The time related to the forecast row Eg 4h, 10h, 16h or 22h.
RATING | INT | The forecast rating. See [swellmap.com](http://www.swellmap.com/) for rating descriptions.
SUMMARY | TEXT | A summary of the predicted weather for that 6 hourly time slot.
SEA_HEIGHT | DOUBLE | Height of the waves.
SWELL_HEIGHT | DOUBLE | Height of the swell.
CHOP_HEIGHT | DOUBLE | Height of the chop.
PERIOD | INTEGER | The time between set waves. A longer period generally indicates a better quality swell. Look for jumps in the period to indicate new swells.
SWELL_DIRECTION | TEXT | The origin direction of the swell.
SEA_DIRECTION | TEXT | The direction of the sea chop.
WIND_DIRECTION | TEXT | Wind direction is from its origin Eg NE means wind is blowing from the north east.
WIND_SPEED | INT | Wind speed.
GUST | INT | The strongest speed of the wind.


## Packaging, Deployment and Execution

The following points detail the steps to package, deploy and execute the standalone fat jar.

  1. Build and package your project by running the maven command "*mvn clean package -Dmaven.test.skip*"
  2. Copy the following files to a folder of your choice E.g. *c:\swellmapapp*
    * **SwellMapApp-1.0-SNAPSHOT-fat.jar** (Found in the target folder)
    * **config.json** (Found in the src/test/resources folder)
    * **locations.json** (Found in the src/test/resources folder)
    * **database.db** (Found in the src/test/resources folder)
  3. Open the config.json using your favorite text editor and update the properties to match their absolute locations:
  4. Open command prompt at the folder location and run the following command to execute:
    * *java -jar SwellMapApp-1.0-SNAPSHOT-fat.jar --conf config.json*

