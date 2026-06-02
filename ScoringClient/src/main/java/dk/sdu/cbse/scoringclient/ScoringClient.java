package dk.sdu.cbse.scoringclient;

import dk.sdu.cbse.scoringclient.spi.IScoringSPI;
import org.springframework.web.client.RestTemplate;

public class ScoringClient implements IScoringSPI {
    private static final String SERVER_URL = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Integer addScore(int score) {
        try{
            return restTemplate.postForObject(SERVER_URL + "/score/add?toAdd=" + score, null, Integer.class);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Integer resetScore() {
        try{
            return restTemplate.postForObject(SERVER_URL + "/score/reset", null, Integer.class);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Integer getScore() {
        try{
            return restTemplate.getForObject(SERVER_URL + "/score", Integer.class);
        } catch (Exception e){
            return null;
        }
    }
}
