import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ShootGame extends JPanel {
    public static final int WIDTH = 400; // 面板寬
    public static final int HEIGHT = 654; // 面板高
    /**
     * 遊戲的當前狀態: START RUNNING PAUSE GAME_OVER
     */
    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;
    private int score = 0; // 得分
    private Timer timer; // 定時器
    private int intervel = 1000 / 100; // 時間間隔(毫秒)
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    private FlyingObject[] flyings = {}; // 敵機陣列
    private Bullet[] bullets = {}; // 子彈陣列
    private Hero hero = new Hero(); // 英雄機

    static { // 靜態程式碼塊，初始化圖片資源
        try {
            background = ImageIO.read(ShootGame.class.getResource("background.png"));
            start = ImageIO.read(ShootGame.class.getResource("start.png"));
            airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
            bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
            bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
            hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
            hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
            pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
            gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 畫
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null); // 畫背景圖
        paintHero(g); // 畫英雄機
        paintBullets(g); // 畫子彈
        paintFlyingObjects(g); // 畫飛行物
        paintScore(g); // 畫分數
//        paintState(g); // 畫遊戲狀態
    }

    /**
     * 畫英雄機
     */
    public void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
    }

    /**
     * 畫子彈
     */
    public void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(), null);
        }
    }

    /**
     * 畫飛行物
     */
    public void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            g.drawImage(f.getImage(), f.getX(), f.getY(), null);
        }
    }

    /**
     * 畫分數
     */
    public void paintScore(Graphics g) {
        int x = 10; // x座標
        int y = 25; // y座標
        Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20); // 字型
        g.setColor(new Color(0xFF0000));
        g.setFont(font); // 設定字型
        g.drawString("SCORE:" + score, x, y); // 畫分數
        y = y + 20; // y座標增20
        g.drawString("LIFE:" + hero.getLife(), x, y); // 畫命
    }

    /**
     * 畫遊戲狀態
     */
    public void paintState(Graphics g) {
        switch (state) {
            case START: // 啟動狀態
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE: // 暫停狀態
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER: // 遊戲終止狀態
                g.drawImage(gameover, 0, 0, null);
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fly");
        ShootGame game = new ShootGame(); // 面板物件
        frame.add(game); // 將面板新增到JFrame中
        frame.setSize(WIDTH, HEIGHT); // 設定大小
        frame.setAlwaysOnTop(true); // 設定其總在最上
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 預設關閉操作
        frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 設定窗體的圖示
        frame.setLocationRelativeTo(null); // 設定窗體初始位置
        frame.setVisible(true); // 儘快呼叫paint
        game.action(); // 啟動執行
    }

    /**
     * 啟動執行程式碼
     */
    public void action() { // 滑鼠監聽事件
        MouseAdapter l = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) { // 滑鼠移動
                if (state == RUNNING) { // 執行狀態下移動英雄機--隨滑鼠位置
                    int x = e.getX();
                    int y = e.getY();
                    hero.moveTo(x, y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) { // 滑鼠進入
                if (state == PAUSE) { // 暫停狀態下執行
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { // 滑鼠退出
                if (state == RUNNING) { // 遊戲未結束，則設定其為暫停
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { // 滑鼠點選
                switch (state) {
                    case START:
                        state = RUNNING; // 啟動狀態下執行
                        break;
                    case GAME_OVER: // 遊戲結束，清理現場
                        flyings = new FlyingObject[0]; // 清空飛行物
                        bullets = new Bullet[0]; // 清空子彈
                        hero = new Hero(); // 重新建立英雄機
                        score = 0; // 清空成績
                        state = START; // 狀態設定為啟動
                        break;
                }
            }
        };
        this.addMouseListener(l); // 處理滑鼠點選操作
        this.addMouseMotionListener(l); // 處理滑鼠滑動操作
        timer = new Timer(); // 主流程控制
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) { // 執行狀態
                    enterAction(); // 飛行物入場
                    stepAction(); // 走一步
                    shootAction(); // 英雄機射擊
                    bangAction(); // 子彈打飛行物
                    outOfBoundsAction(); // 刪除越界飛行物及子彈
                    checkGameOverAction(); // 檢查遊戲結束
                }
                repaint(); // 重繪，呼叫paint()方法
            }
        }, intervel, intervel);
    }

    int flyEnteredIndex = 0; // 飛行物入場計數

    /**
     * 飛行物入場
     */
    public void enterAction() {
        flyEnteredIndex++;
        if (flyEnteredIndex % 40 == 0) { // 400毫秒生成一個飛行物--10*40
            FlyingObject obj = nextOne(); // 隨機生成一個飛行物
            flyings = Arrays.copyOf(flyings, flyings.length + 1);
            flyings[flyings.length - 1] = obj;
        }
    }

    /**
     * 走一步
     */
    public void stepAction() {
        for (int i = 0; i < flyings.length; i++) { // 飛行物走一步
            FlyingObject f = flyings[i];
            f.step();
        }
        for (int i = 0; i < bullets.length; i++) { // 子彈走一步
            Bullet b = bullets[i];
            b.step();
        }
        hero.step(); // 英雄機走一步
    }

    /**
     * 飛行物走一步
     */
    public void flyingStepAction() {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            f.step();
        }
    }

    int shootIndex = 0; // 射擊計數

    /**
     * 射擊
     */
    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) { // 300毫秒發一顆
            Bullet[] bs = hero.shoot(); // 英雄打出子彈
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 擴容
            System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // 追加陣列
        }
    }

    /**
     * 子彈與飛行物碰撞檢測
     */
    public void bangAction() {
        for (int i = 0; i < bullets.length; i++) { // 遍歷所有子彈
            Bullet b = bullets[i];
            bang(b); // 子彈和飛行物之間的碰撞檢查
        }
    }

    /**
     * 刪除越界飛行物及子彈
     */
    public void outOfBoundsAction() {
        int index = 0; // 索引
        FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // 活著的飛行物
    }
    /** 檢查游戏结束 */
    public void checkGameOverAction() {
        if (isGameOver()==true) {
            state = GAME_OVER; // 改變状态
        }
    }

    /** 檢查游戏是否结束 */
    public boolean isGameOver() {

        for (int i = 0; i < flyings.length; i++) {
            int index = -1;
            FlyingObject obj = flyings[i];
            if (hero.hit(obj)) { // 檢查英雄機與飛行物是否碰撞
                hero.subtractLife(); // 減命
                hero.setDoubleFire(0); // 雙倍火力解除
                index = i; // 紀錄碰上的飛行物索引
            }
            if (index != -1) {
                FlyingObject t = flyings[index];
                flyings[index] = flyings[flyings.length - 1];
                flyings[flyings.length - 1] = t; // 碰上的與最後一个飛行物交换

                flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除碰上的飛行物
            }
        }

        return hero.getLife() <= 0;
    }

    /** 子彈和飛行物之間的碰撞檢查 */
    public void bang(Bullet bullet) {
        int index = -1; // 擊中的飛行物索引
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject obj = flyings[i];
            if (obj.shootBy(bullet)) { // 判断是否擊中
                index = i; // 紀錄被擊中的飛行物的索引
                break;
            }
        }
        if (index != -1) { // 有擊中的飛行物
            FlyingObject one = flyings[index]; // 紀錄被擊中的飛行物

            FlyingObject temp = flyings[index]; // 被擊中的飛行物與最後一个飛行物交换
            flyings[index] = flyings[flyings.length - 1];
            flyings[flyings.length - 1] = temp;

            flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除被擊中的飛行物

            // 檢查one的類型(敵人加分，獎勵獲取)
            if (one instanceof Enemy) { // 檢查類型，是敵人，则加分
                Enemy e = (Enemy) one; // 強制類型转换
                score += e.getScore(); // 加分
            } else { // 若為獎勵，設置獎勵
                Award a = (Award) one;
                int type = a.getType(); // 獲取獎勵類型
                switch (type) {
                    case Award.DOUBLE_FIRE:
                        hero.addDoubleFire(); // 設置雙倍火力
                        break;
                    case Award.LIFE:
                        hero.addLife(); // 設置加命
                        break;
                }
            }
        }
    }

    /**
     * 隨機生成飛行物
     *
     * @return 飛行物對象
     */
    public static FlyingObject nextOne() {
        Random random = new Random();
        int type = random.nextInt(20); // [0,20)
        if (type < 1) { //調整出現頻率
            return new Bee();
        } else {
            return new Airplane();
        }
    }

}
