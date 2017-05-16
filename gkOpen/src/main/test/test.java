import java.util.Random;

/**
 * Created by lx on 2016/12/15.
 */
public class test {
    public static void main(String[] args) {
        while (true) {
            int i=0;
            i++;
            int _random = new Random().nextInt(1000000);
            int random = makerandom(_random);
                System.out.println(random);
              if (i == 100){
                  break;
              }
        }
}
        public static int makerandom(int random) {
            if (random < 100000) {
                 return  makerandom(new Random().nextInt(1000000));
            }
            return random;
        }
    }

