/**
 * @author Mohit
 */

package game.tictactoe;

import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserPanel implements ActionListener
{
    private JFrame frame;
    private JTextField person_1, person_2, input_series;
    private JButton start, yt;
    public void UserPanel()
    {
        frame = new JFrame("User Panel");
        frame.setSize(490,290);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel person_l1 = new JLabel("Enter 1st Name (X) :");
        person_l1.setBounds(10,22,150,30);
        person_l1.setFont(new Font("Arial",1,15));
        frame.add(person_l1);
        person_1 = new JTextField();
        person_1.setBounds(170,20,300,35);
        person_1.setFont(new Font("Arial",2,15));
        frame.add(person_1);

        JLabel person_l2 = new JLabel("Enter 2nd Name (O) :");
        person_l2.setBounds(10,72,150,30);
        person_l2.setFont(new Font("Arial",1,15));
        frame.add(person_l2);
        person_2 = new JTextField();
        person_2.setBounds(170,70,300,35);
        person_2.setFont(new Font("Arial",2,15));
        frame.add(person_2);

        JLabel input_seriesL = new JLabel("Total Series :");
        input_seriesL.setBounds(10,120,150,30);
        input_seriesL.setFont(new Font("Arial",1,15));
        frame.add(input_seriesL);

        JLabel warning_get_series = new JLabel("");
        warning_get_series.setBounds(170,147,200,30);
        warning_get_series.setFont(new Font("Arial",1,10));
        warning_get_series.setForeground(Color.RED);
        frame.add(warning_get_series);
        input_series = new JTextField();
        input_series.setBounds(170,120,300,35);
        input_series.setFont(new Font("Arial",2,15));
        input_series.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent getting_key)
            {
                if ((getting_key.getKeyChar()>='0' && getting_key.getKeyChar()<='9') || (getting_key.getKeyChar()==KeyEvent.VK_BACK_SPACE) || (getting_key.getKeyChar()==KeyEvent.VK_DELETE) )
                {
                    input_series.setEditable(true);
                    warning_get_series.setText("");
                }
                else
                {
                    input_series.setEditable(false);
                    warning_get_series.setText("* Enter only numeric digits(0-9)");
                }
            }
        });
        frame.add(input_series);

        start = new JButton("START");
        start.setFont(new Font("Arial",1,20));
        start.setBounds(170,200,120,45);
        start.addActionListener(this);
        frame.add(start);

        yt = new JButton("subscribe my channel");
        yt.setForeground(new Color(22, 30, 24));
        yt.setBackground(new Color(255, 237, 202));
        yt.setFont(new Font("Arial",2,11));
        yt.setBounds(331,232,150,25);
        yt.addActionListener(this);
        frame.add(yt);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==start)
        {
            int getIntSeries;
            try {getIntSeries = Integer.parseInt(input_series.getText());}
            catch (Exception ee) {getIntSeries = 1;}

            GamePanel game = new GamePanel(person_1.getText(), person_2.getText(), getIntSeries);
            frame.setVisible(false);
            game.game_panel();
        }
        else if(e.getSource()==yt)
        {
            try {Desktop.getDesktop().browse(new URL("https://youtube.com/@CodeKaro2006").toURI());}
            catch (Exception eee) {}
        }
    }
}