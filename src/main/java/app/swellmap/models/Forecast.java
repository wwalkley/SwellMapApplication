package app.swellmap.models;

public class Forecast {
    private String region;
    private String location;
    private String date;
    private String time;
    private int rating;
    private String summary;
    private double seaHeight;
    private double swellHeight;
    private double chopHeight;
    private int period;
    private String swellDirection;
    private String seaDirection;
    private String windDirection;
    private int windSpeed;
    private int gust;

    public Forecast() {
    }

    public Forecast(String region, String location, String date,  String time, int rating, String summary, double seaHeight,
            double swellHeight, double chopHeight, int period, String swellDirection, String seaDirection,
            String windDirection, int windSpeed, int gust) {
        this.region = region;
        this.location = location;
        this.date = date;
        this.time = time;
        this.rating = rating;
        this.summary = summary;
        this.seaHeight = seaHeight;
        this.swellHeight = swellHeight;
        this.chopHeight = chopHeight;
        this.period = period;
        this.swellDirection = swellDirection;
        this.seaDirection = seaDirection;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.gust = gust;
    }

    public String getDate() {
        return this.date;
    }

    public String setDate(String date) {
        return this.date = date;
    }

    public String getRegion() {
        return this.region;
    }

    public String setRegion(String region) {
        return this.region = region;
    }

    public String getLocation() {
        return this.location;
    }

    public String getTime() {
        return this.time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        if (isNumeric(rating)) {
            this.rating = Integer.parseInt(rating);
        } else {
            this.rating = 0;
        }
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getSeaHeight() {
        return this.seaHeight;
    }

    public void setSeaHeight(String seaHeight) {
        if (isNumeric(seaHeight)) {
            this.seaHeight = Double.parseDouble(seaHeight);
        } else {
            this.seaHeight = 0;
        }
    }

    public double getSwellHeight() {
        return this.swellHeight;
    }

    public void setSwellHeight(String swellHeight) {
        if (isNumeric(swellHeight)) {
            this.swellHeight = Double.parseDouble(swellHeight);
        } else {
            this.swellHeight = 0;
        }
    }

    public double getChopHeight() {
        return this.chopHeight;
    }

    public void setChopHeight(String chopHeight) {
        if (isNumeric(chopHeight)) {
            this.chopHeight = Double.parseDouble(chopHeight);
        } else {
            this.chopHeight = 0;
        }
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        if (isNumeric(period)) {
            this.period = Integer.parseInt(period);
        } else {
            this.period = 0;
        }
    }

    public String getSwellDirection() {
        return this.swellDirection;
    }

    public void setSwellDirection(String swellDirection) {
        this.swellDirection = swellDirection;
    }

    public String getSeaDirection() {
        return this.seaDirection;
    }

    public void setSeaDirection(String seaDirection) {
        this.seaDirection = seaDirection;
    }

    public String getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        if (isNumeric(windSpeed)) {
            this.windSpeed = Integer.parseInt(windSpeed);
        } else {
            this.windSpeed = 0;
        }
    }

    public int getGust() {
        return this.gust;
    }

    public void setGust(String gust) {
        if (isNumeric(gust)) {
            this.gust = Integer.parseInt(gust);
        } else {
            this.gust = 0;
        }
    }

    public void setWindInformation(String windInfo) {
        String[] data = windInfo.split(":");
        setWindDirection(data[0]);
        setWindSpeed(data[1]);
    }

    @Override
    public String toString() {
        return "Region: " + this.getRegion() + "\nLoaction: " + this.getLocation() + "\nTime: " + this.getTime()
                + "\nRating: " + this.getRating() + "\nSummary: " + this.getSummary() + "\nSea Height: "
                + this.getSeaHeight() + "\nSwell Height: " + this.getSwellHeight() + "\nChop Height: "
                + this.getChopHeight() + "\nPeriod: " + this.getPeriod() + "\nSwell Direction: "
                + this.getSwellDirection() + "\nSea Direction: " + this.getSeaDirection() + "\nWind Direction: "
                + this.getWindDirection() + "\nWind Speed: " + this.getWindSpeed() + "\nGusts: " + this.getGust();

    }

    private static boolean isNumeric(String str) {
        if (canBeConvertedToDouble(str) || canBeConvertedToInt(str)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean canBeConvertedToDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean canBeConvertedToInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}