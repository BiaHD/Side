import java.util.Random;

/**
 * 蜜蜂
 */
public class Bee extends FlyingObject implements Award,Enemy {
    private int xSpeed = 1; //x座標移動速度
    private int ySpeed = 2; //y座標移動速度
    private int awardType; //獎勵型別

    /**
     * 初始化資料
     */
    public Bee() {
        this.image = ShootGame.bee;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt(ShootGame.WIDTH - width);
        awardType = rand.nextInt(2); //初始化時給獎勵
        }
        /** 獲得獎勵型別 */
        public int getType () {
            return awardType;
        }
        /** 越界處理 */
        @Override
        public boolean outOfBounds(){
            return y > ShootGame.HEIGHT;
        }
        /** 移動，可斜著飛 */
        @Override
        public void step(){
            x += xSpeed;
            y += ySpeed;
            if (x > ShootGame.WIDTH - width) {
                xSpeed = -1;
            }
            if (x < 0) {
                xSpeed = 1;
            }
        }
        @Override
        public int getScore() {
            return 10;
        }
    }
