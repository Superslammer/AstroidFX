package dk.sdu.cbse.scoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {
    int score = 0;

    @GetMapping("/score")
    public int getScore(){
        return score;
    }

    @PostMapping("/score/add")
    public int add(@RequestParam int toAdd){
        score += toAdd;
        return score;
    }

    @PostMapping("/score/reset")
    public int reset(){
        score = 0;
        return score;
    }
}
