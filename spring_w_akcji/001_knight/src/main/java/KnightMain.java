import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = (Knight) context.getBean("knight");
        knight.embarkOnQuest();
        context.close();
    }
}
