import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class client extends JFrame {
    private Container cp;

    private JLabel ip = new JLabel("IP");
    private JLabel loction = new JLabel();
    private JLabel port = new JLabel("Port");
    private JTextField jtf = new JTextField();
    private JButton set = new JButton("Setting");
    private JButton start = new JButton("Start");
    private JPanel jpn = new JPanel(new GridLayout(1,7,2,2));

    private JTextArea jta = new JTextArea();
    private JScrollPane jsp = new JScrollPane(jta);



    private JTextField jta1 = new JTextField();
    private JButton send = new JButton("Send");
    private JPanel jpn2 = new JPanel(new GridLayout(1,2,2,2));

    public client(){
        init();
    }
    public void init(){
        cp=this.getContentPane();
        this.setLayout(new BorderLayout(3,3));
        this.setBounds(700,100,600,600);
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

        jta.setBackground(new Color(100,150,130));
        jta.setFont(new Font("標楷體", Font.PLAIN,30));
        jta.setEditable(false);


        jpn2.add(jta1);
        jpn2.add(send);

        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientNet cn = new clientNet(client.this);
                loction.setText(cn.getHostIP());
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setJta(String str) {
        jta.setText(str);
    }
}
