import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
 
class ChooseColor extends JFrame implements ActionListener{
  JButton b= new JButton("Change");
  JTextArea t= new JTextArea(6, 30);
  ChooseColor() {
     setTitle("Change Color");
     setDefaultCloseOperation(3);
     setBounds(300, 200,350,200);
     Container c= getContentPane();
     c.setLayout(new FlowLayout());
     c.add(b);
     c.add(new JScrollPane(t));
     b.addActionListener(this);
     setVisible(true);
  }
 
  public void actionPerformed(ActionEvent ac ){
 
  }
 
   public static void main(String args[]) throws Exception{
     new ChooseColor();
  }
}