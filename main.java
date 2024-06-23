import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class main {
    private static JTextArea textBox;
    private static JFrame window;

    public static void main(String[] args) {
        // Create the main window
        window = new JFrame("User Interface I");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setLocationRelativeTo(null);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        // Create the menu items
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem displayDateTimeItem = new JMenuItem("Display Date and Time");
        displayDateTimeItem.addActionListener(e -> displayDateTime());
        fileMenu.add(displayDateTimeItem);
        JMenuItem saveTextToLogItem = new JMenuItem("Save Text to Log");
        saveTextToLogItem.addActionListener(e -> saveTextToLog());
        fileMenu.add(saveTextToLogItem);
        JMenuItem changeBackgroundColorItem = new JMenuItem("Change Background Color");
        changeBackgroundColorItem.addActionListener(e -> changeBackgroundColor());
        fileMenu.add(changeBackgroundColorItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> window.dispose());
        fileMenu.add(exitItem);

        // Text box to display output
        textBox = new JTextArea(10, 50);
        textBox.setText("This is the initial text in the text area.");
        textBox.setEditable(true);
        window.getContentPane().add(new JScrollPane(textBox), BorderLayout.CENTER);

        // Display the window
        window.setVisible(true);
    }

    private static void displayDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeString = currentDateTime.format(formatter);
        textBox.setText(dateTimeString);
    }

    private static void saveTextToLog() {
        String logText = textBox.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(logText);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void changeBackgroundColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color randomColor = new Color(red, green, blue);
        window.getContentPane().setBackground(randomColor);
        window.repaint();
    }
}
