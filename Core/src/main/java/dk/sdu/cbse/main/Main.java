package dk.sdu.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("main")
public class Main extends Application{
    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String bean : ctx.getBeanDefinitionNames()){
            System.out.print(bean);
        }

        Game game = ctx.getBean(Game.class);
        game.start(window);
        game.render();
    }
}
