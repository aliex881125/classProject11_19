import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class serve extends JFrame {
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

    public serve(){
        init();
    }
    public void init(){
        cp=this.getContentPane();
        this.setLayout(new BorderLayout(3,3));
        this.setBounds(100,100,600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jsp, BorderLayout.CENTER);
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


        jpn2.add(jta1);
        jpn2.add(send);

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
}
