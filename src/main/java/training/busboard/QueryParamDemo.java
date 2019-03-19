package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class QueryParamDemo {

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        double lat = 51.488165;
        double lon = 0.108944;
        List<StopPoint> response = client.target("https://api.tfl.gov.uk/StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("radius", 1000)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPointsResult.class)
                .getStopPoints();
        System.out.println(response);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StopPointsResult {
        private List<StopPoint> stopPoints;
        private StopPointsResult() {}

        public List<StopPoint> getStopPoints() {
            return stopPoints;
        }
    }
}
