import java.awt.*;
 
 
import java.awt.event.ActionEvent;
 
 
import java.awt.event.ActionListener;
 
 
import java.lang.reflect.InvocationTargetException;
 
import javax.swing.*;
 
 
import java.util.concurrent.ExecutorService;
 
import java.util.concurrent.Executors;
 
 
public class SwingThreadWait extends JFrame {
 
ExecutorService ex;
 
 
JTextArea first, second;
 
 
private long start = 0;
 
 
RSSReader a, b;
 
 
public SwingThreadWait() {
 
 
super("RSS Readers");
 
ex = Executors.newFixedThreadPool(2);
 
 
 
first = new JTextArea(20, 50);
 
 
second = new JTextArea(20, 50);
 
try {
 
a = new RSSReader(first);
 
 
b = new RSSReader(second);
 
ex.execute(a);
 
ex.execute(b);
 
} catch (Exception e) {
 
System.out.println(e.toString());
 
first.setText(e.toString());
 
}
 
 
JPanel Center = new JPanel();
 
 
Center.setLayout(new GridLayout(1,3));
 
//JPanel filler=new JPanel();filler.setSize(5,5);
Center.add(first);
//Center.add(filler);
Center.add(second);
 
add(Center, BorderLayout.CENTER);
 
pack();
 
setLocationRelativeTo(null);
 
 
setDefaultCloseOperation(EXIT_ON_CLOSE);
 
}
 
 
public static void main(String args[]) {
 
 
SwingThreadWait edt = new SwingThreadWait();
 
 
edt.setVisible(true);
 
 
}
 
 
}