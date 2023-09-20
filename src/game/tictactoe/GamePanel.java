/**
 * @author Mohit
 */

package game.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel implements ActionListener
{
    private JFrame frame;
    private JButton [] btn;
    private int count, total_series, count_series, final_wins_pl1, final_wins_pl2;
    private String str_x_o;
    private Color clr_x_o;
    private boolean win =  false;
    private static String getting_per1, getting_per2, setting_per_title, setting_per_winner;

    GamePanel() {}
    GamePanel(String getting_per1, String getting_per2, int total_series)
    {
        this.getting_per1 = getting_per1;
        this.getting_per2 = getting_per2;
        this.total_series = total_series;
    }
    public void game_panel()
    {
        frame = new JFrame(getting_per1+" (X) turns.");
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(3,3));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        btn = new JButton[9];

        for (int i = 0; i<9; i++)
        {
            btn[i] = new JButton();
            btn[i].addActionListener(this);
            frame.add(btn[i]);
         }
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        set_X_O(e);
        winningPossibility();
        whoWins();
    }
    private void set_X_O(ActionEvent e)
    {
        if (count%2==0)
        {
            str_x_o = "X";
            setting_per_title = getting_per2+" (O turns)";
            setting_per_winner = getting_per1+" (X) is winner";
            clr_x_o = Color.RED;
        }
        else
        {
            str_x_o = "O";
            setting_per_title = getting_per1+" (X turns)";
            setting_per_winner = getting_per2+" (O) is winner";
            clr_x_o = Color.YELLOW;
        }
        count++;

        Object obj = e.getSource();
        for (int i = 0; i < 9; i++)
        {
            if (obj==btn[i])
            {
                frame.setTitle(setting_per_title);
                btn[i].setFont(new Font("Arial",1,40));
                btn[i].setText(str_x_o);
                btn[i].setBackground(clr_x_o);
                btn[i].setEnabled(false);
            }
        }
    }
    private void winningPossibility()
    {
        //----------Horizontally winnings----------------------
        if ( btn[0].getText()==btn[1].getText() && btn[1].getText()==btn[2].getText() && btn[0].getText()!="" )
        {
            win = true;
        }
        else if ( btn[3].getText()==btn[4].getText() && btn[4].getText()==btn[5].getText() && btn[3].getText()!="" )
        {
            win = true;
        }
        else if ( btn[6].getText()==btn[7].getText() && btn[7].getText()==btn[8].getText() && btn[6].getText()!="" )
        {
            win = true;
        }
        //----------Vertically winnings----------------------
        else if ( btn[0].getText()==btn[3].getText() && btn[3].getText()==btn[6].getText() && btn[0].getText()!="" )
        {
            win = true;
        }
        else if ( btn[1].getText()==btn[4].getText() && btn[4].getText()==btn[7].getText() && btn[1].getText()!="" )
        {
            win = true;
        }
        else if ( btn[2].getText()==btn[5].getText() && btn[5].getText()==btn[8].getText() && btn[2].getText()!="" )
        {
            win = true;
        }
        //----------Diagonally winnings----------------------
        else if ( btn[0].getText()==btn[4].getText() && btn[4].getText()==btn[8].getText() && btn[0].getText()!="" )
        {
            win = true;
        }
        else if ( btn[2].getText()==btn[4].getText() && btn[4].getText()==btn[6].getText() && btn[2].getText()!="" )
        {
            win = true;
        }
        //----------Match Draw----------------------.
        else
        {
            win = false;
        }
    }
    private void whoWins()
    {
        if (win==true)
        {
            if (setting_per_winner.equalsIgnoreCase(getting_per1+" (X) is winner")) {final_wins_pl1++;}
            else {final_wins_pl2++;}

            count_series++;

            frame.setTitle(setting_per_winner);
            JOptionPane.showMessageDialog(frame,"(series :- "+count_series+"/"+total_series+") "+setting_per_winner);
            frame.setVisible(false);
            frame.setTitle(null);
            restartGame();
        }
        else if (win == false && count>=9)
        {
            int mine = 0;
            for (int i = 1; i < 9; i++)
            {
                if (btn[i].getText().equalsIgnoreCase(""))
                {
                    mine++;
                }
            }

            if (win == false && count == 10 && setting_per_title.equalsIgnoreCase(getting_per1 + " (X turns)") && mine == 0)
            {
                count_series++;
                frame.setTitle("Match is Draw.");
                JOptionPane.showMessageDialog(frame, "(series :- "+count_series+"/"+total_series+") "+"Match is Draw.");
                frame.setVisible(false);
                restartGame();
            }
            else if (win == false && count == 9 && mine == 0)
            {
                count_series++;
                frame.setTitle("Match is Draw.");
                JOptionPane.showMessageDialog(frame, "(series :- "+count_series+"/"+total_series+") "+"Match is Draw.");
                frame.setVisible(false);
                restartGame();
            }
        }
    }
    private void restartGame()
    {

        if (total_series<=count_series)
        {
            if (final_wins_pl1>final_wins_pl2) {JOptionPane.showMessageDialog(frame,getting_per1+" is Winner.");}
            else if (final_wins_pl2>final_wins_pl1) {JOptionPane.showMessageDialog(frame,getting_per2+" is Winner.");}
            else if (final_wins_pl1==final_wins_pl2) {JOptionPane.showMessageDialog(frame,"...Match is DRAW...");}

            int n = JOptionPane.showConfirmDialog(frame,"Do you want to play again?","TicTacToe",JOptionPane.YES_NO_OPTION);
            if (n==0)
            {
                new UserPanel().UserPanel();
            }
            else if (n==1)
            {
                System.exit(0);
            }
            else
            {
                System.exit(0);
            }
        }
        else
        {
                if (setting_per_title.equalsIgnoreCase(getting_per2+" (O turns)"))
                {
                    count = 0;
                    frame.setTitle(getting_per1+" (X turns)");
                }
                else
                {
                    count=1;
                    frame.setTitle(getting_per2+" (O turns)");
                }
                str_x_o = "";
                win = false;
                for (int i = 0; i < 9; i++)
                {
                    btn[i].setText("");
                    btn[i].setBackground(null);
                }
                setEnabling(true);
        }
    }
    private void setEnabling(boolean b)
    {
        for (int i = 0; i < 9; i++)
        {
            btn[i].setEnabled(b);
        }
        frame.setVisible(true);
    }
}