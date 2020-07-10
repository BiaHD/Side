import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements ActionListener {

    static int mapHeight = 9, mapWidth = 9, bombNum = 10;
    static int frameWidth = 500, frameHeight = 200;
    private JPanel centerButtonPanel;
    private JButton[] buttons;
    public static final int WIDTH = 400; // 面板寬
    public static final int HEIGHT = 654; // 面板高

    //啟動

    public Launcher(){
        super("遊戲小天地~");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        //GUI初始化
        centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout());
        buttons = new JButton[3];

        //Button

        //選踩地雷
        buttons[0] = new JButton("Mining game");
        buttons[0].addActionListener(this);
        buttons[0].setBackground(Color.LIGHT_GRAY);
        buttons[0].setActionCommand("Mines");
        centerButtonPanel.add(buttons[0]);

        //選21點
        buttons[1] = new JButton("BlackJack game");
        buttons[1].addActionListener(this);
        buttons[1].setBackground(Color.LIGHT_GRAY);
        buttons[1].setActionCommand("Blackjack");
        centerButtonPanel.add(buttons[1]);

        //選飛機射擊遊戲
        buttons[2] = new JButton("Airplane game");
        buttons[2].addActionListener(this);
        buttons[2].setBackground(Color.LIGHT_GRAY);
        buttons[2].setActionCommand("Airplane");
        centerButtonPanel.add(buttons[2]);

        add(centerButtonPanel, BorderLayout.CENTER);
    }

    //按下任何一個按紐
    public void actionPerformed(ActionEvent e){
        String command =e.getActionCommand();

        //若讀到的是踩地雷
        if(command.equals("Mines")){
            System.out.println("123");
            Game frame = new Game();
            frame.setVisible(true);
        }
        //若讀到的是21點
        else if(command.equals("Blackjack")){
            Deck mainDeck = new Deck();
            mainDeck.addAllCards();

            System.out.println("Welcome to Java Blackjack!");
            mainDeck.shuffle();

            Deck playerDeck = new Deck();
            Deck dealerDeck = new Deck();

            MainBlackJack a = new MainBlackJack();
            a.nextAction(mainDeck, playerDeck, dealerDeck);
        }
        //若讀到的是飛機射擊遊戲
        else if(command.equals("Airplane")){
            JFrame frame = new JFrame("Fly");
            ShootGame game = new ShootGame(); // 面板物件
            frame.add(game); // 將面板新增到JFrame中
            frame.setSize(WIDTH, HEIGHT); // 設定大小
            frame.setAlwaysOnTop(true); // 設定其總在最上
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 預設關閉
            frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 設定圖示
            frame.setLocationRelativeTo(null); // 設定視窗初始位置
            frame.setVisible(true); // 儘快呼叫paint
            game.action(); // 啟動執行
        }
    }
    public static void main(String[] args)
    {
        Launcher frame = new Launcher();
        frame.setVisible(true);
    }
}
