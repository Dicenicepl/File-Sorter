import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Gui {
    void gui() {
        JFrame frame = new JFrame("Sorting files");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton alphabetButton = new JRadioButton("Alphabet");
        JRadioButton extensionButton = new JRadioButton("Extension");
        JRadioButton dateButton = new JRadioButton("Date of Creation");
        ButtonGroup group = new ButtonGroup();
        group.add(alphabetButton);
        group.add(extensionButton);
        group.add(dateButton);
        radioPanel.add(alphabetButton);
        radioPanel.add(extensionButton);
        radioPanel.add(dateButton);
        panel.add(radioPanel);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField pathInput = new JTextField(20);
        JButton chooseDirectoryButton = new JButton("Choose Directory");
        jPanel.add(new JLabel("Choose Directory:"));
        jPanel.add(chooseDirectoryButton);
        jPanel.add(jPanel);

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField timeInput = new JTextField(15);
        timePanel.add(new JLabel("Select Time:"));
        timePanel.add(timeInput);
        jPanel.add(timePanel);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitPanel.add(submitButton);
        jPanel.add(submitPanel);

        chooseDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String directoryPath = selectedFile.getAbsolutePath();
                    pathInput.setText(directoryPath);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String time = timeInput.getText();
                String path = pathInput.getText();
                byte choose = -1;
                if (extensionButton.isSelected()) {
                    choose = 0;
                } else if (alphabetButton.isSelected()) {
                    choose = 1;
                } else if (dateButton.isSelected()) {
                    choose = 2;
                }
                long period;
                try {
                    period = Long.parseLong(time);
                }catch (NumberFormatException message){
                    System.out.println(message.getMessage());
                    period = 10_000L; //When user input wrong data then we want to catch that and change to active task
                }
                Schedule schedule = new Schedule();
                schedule.activeTimer(choose, path, period);
            }
        });

        frame.add(jPanel);
        frame.setVisible(true);
    }
}

