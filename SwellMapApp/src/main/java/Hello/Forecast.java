package Hello;

public class Forecast {
    private String time;
    private int rating;
    private String summary;
    private double seaHeight;
    private double swellHeight;
    private double chopHeight;
    private int period;
    private String swellDirection;
    private String seaDirection;
    private String windSpeed;
    private double gust;



    public Forecast() {
    }

    public Forecast(String time, int rating, String summary, double seaHeight, double swellHeight, double chopHeight, int period, String swellDirection, String seaDirection, String windSpeed, double gust) {
        this.time = time;
        this.rating = rating;
        this.summary = summary;
        this.seaHeight = seaHeight;
        this.swellHeight = swellHeight;
        this.chopHeight = chopHeight;
        this.period = period;
        this.swellDirection = swellDirection;
        this.seaDirection = seaDirection;
        this.windSpeed = windSpeed;
        this.gust = gust;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public void setSeaHeight(double seaHeight) {
        this.seaHeight = seaHeight;
    }

    public double getSwellHeight() {
        return this.swellHeight;
    }

    public void setSwellHeight(double swellHeight) {
        this.swellHeight = swellHeight;
    }

    public double getChopHeight() {
        return this.chopHeight;
    }

    public void setChopHeight(double chopHeight) {
        this.chopHeight = chopHeight;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getGust() {
        return this.gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public Forecast time(String time) {
        this.time = time;
        return this;
    }

    public Forecast rating(int rating) {
        this.rating = rating;
        return this;
    }

    public Forecast summary(String summary) {
        this.summary = summary;
        return this;
    }

    public Forecast seaHeight(double seaHeight) {
        this.seaHeight = seaHeight;
        return this;
    }

    public Forecast swellHeight(double swellHeight) {
        this.swellHeight = swellHeight;
        return this;
    }

    public Forecast chopHeight(double chopHeight) {
        this.chopHeight = chopHeight;
        return this;
    }

    public Forecast period(int period) {
        this.period = period;
        return this;
    }

    public Forecast swellDirection(String swellDirection) {
        this.swellDirection = swellDirection;
        return this;
    }

    public Forecast seaDirection(String seaDirection) {
        this.seaDirection = seaDirection;
        return this;
    }

    public Forecast windSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public Forecast gust(double gust) {
        this.gust = gust;
        return this;
    }
}