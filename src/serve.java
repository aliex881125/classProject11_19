import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class serve extends JFrame implements KeyListener {
    private Container cp;
    private serverNet se;

    private JLabel ip = new JLabel("IP");
    private JLabel loction = new JLabel();
    private JLabel port = new JLabel("Port");
    private JTextField jtf = new JTextField();
    private JButton set = new JButton("Setting");
    private JButton start = new JButton("Start");
    private JButton exit = new JButton("Exit");
    private JPanel jpn = new JPanel(new GridLayout(1,8,2,2));

    private JTextArea jta = new JTextArea();
    private JScrollPane jsp = new JScrollPane(jta);



    private JTextField jta1 = new JTextField();
    private JButton send = new JButton("Send");
    private JPanel jpn2 = new JPanel(new GridLayout(1,2,2,2));

    private ImageIcon icon = new ImageIcon("591ba5232e227.png");
    private JLabel jlbImg = new JLabel();
    private int x=50;
    private int y=50;

    private ImageIcon icon1 = new ImageIcon("ghost_PNG12.png");
    private JLabel jlbImg1 = new JLabel();

    private JPanel jpn1 = new JPanel();

    public serve(){
        init();
    }
    public void init(){
        cp=this.getContentPane();
        this.setLayout(new BorderLayout(3,3));
        this.setBounds(100,100,600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jpn1, BorderLayout.CENTER);
        cp.add(jsp,BorderLayout.EAST);
        cp.add(jpn2, BorderLayout.SOUTH);

        jpn.add(ip);
        ip.setHorizontalAlignment(SwingConstants.CENTER);
        jpn.setFont(new Font("標楷體",Font.PLAIN,15));
        jpn.add(loction);
        jpn.add(port);
        jpn.add(jtf);
        jpn.add(set);
        jpn.add(start);
        jpn.add(exit);

        jta.setBackground(new Color(200,150,130));
        jta.setFont(new Font("標楷體", Font.PLAIN,30));
        jta.setEditable(false);
        jsp.setPreferredSize(new Dimension(200,600));


        jpn2.add(jta1);
        jpn2.add(send);

        Image img1 = icon.getImage();
        Image img2 = img1.getScaledInstance(80,120,Image.SCALE_SMOOTH);
        icon.setImage(img2);
        jlbImg.setIcon(icon);
        jpn1.add(jlbImg);
        jlbImg.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());

        Image img3 = icon1.getImage();
        Image img4 = img3.getScaledInstance(80,120,Image.SCALE_SMOOTH);
        icon1.setImage(img4);
        jlbImg1.setIcon(icon1);
        jpn1.add(jlbImg1);
        jlbImg1.setBounds(90,30,icon1.getIconWidth(),icon1.getIconHeight());

        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverNet sn = new serverNet(serve.this);
                loction.setText(sn.getHostIP());
                jtf.setText(Integer.toString(sn.getPort()));


            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverNet sn = new serverNet(serve.this);
                sn.start();


            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(se !=null&&jta1.getText().equals("")){
                    se.sendMsg(jta1.getText());
                    jta1.setText("");
                }
                   serve.this.appendMessage(jta1.getText());
                   jta1.setText("");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void appendMessage(String msg){
        jta.append(msg+"\n------\n");
    }
    public void keyPressed(KeyEvent ke){
        System.out.println(ke.getKeyCode());
        switch (ke.getKeyCode()){
            case 37:
                x=x-10;
                jlbImg.setLocation(x,y);
                break;
            case 38:
                y=y-10;
                jlbImg.setLocation(x,y);
                break;
            case 39:
                x=x+10;
                jlbImg.setLocation(x,y);
                break;
            case 40:
                y=y+10;
                jlbImg.setLocation(x,y);
        }
    }
    public void keyReleased(KeyEvent ke){
        System.out.println(ke.getKeyCode());
    }
    public void keyTyped(KeyEvent ke){
        System.out.println(ke.getKeyCode());
    }
}
