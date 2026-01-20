package student.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagerFrame extends JFrame implements ActionListener {

    JTextField idField, nameField, cgpaField, searchField;
    JTextArea displayArea;
    JButton addBtn, updateBtn, deleteBtn, saveBtn, loadBtn, searchBtn, clearBtn;

    Student[] students = new Student[100];

    public StudentManagerFrame() {
        setTitle("Student Management");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        FileIO.load(students);
        refreshDisplay();
        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel p = new JPanel(new GridLayout(2, 4, 10, 10));
        p.setBorder(BorderFactory.createTitledBorder("Student Information"));

        idField = new JTextField();
        nameField = new JTextField();
        cgpaField = new JTextField();
        searchField = new JTextField();

        p.add(new JLabel("ID:"));
        p.add(idField);
        p.add(new JLabel("Name:"));
        p.add(nameField);
        p.add(new JLabel("CGPA:"));
        p.add(cgpaField);
        p.add(new JLabel("Search by ID:"));
        p.add(searchField);

        return p;
    }

    private JPanel createCenterPanel() {
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        return p;
    }

    private JPanel createBottomPanel() {
        JPanel p = new JPanel(new FlowLayout());
        addBtn = createBtn("Add", p);
        updateBtn = createBtn("Update", p);
        deleteBtn = createBtn("Delete", p);
        searchBtn = createBtn("Search", p);
        saveBtn = createBtn("Save", p);
        loadBtn = createBtn("Load", p);
        clearBtn = createBtn("Clear", p);
        return p;
    }

    private JButton createBtn(String text, JPanel p) {
        JButton b = new JButton(text);
        b.addActionListener(this);
        p.add(b);
        return b;
    }

    private void refreshDisplay() {
        displayArea.setText("");
        for (Student s : students) {
            if (s != null) displayArea.append(s.toString() + "\n");
        }
    }

    private int findIndex(String id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    private int emptyIndex() {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) return i;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addBtn) {
                int index = emptyIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Database Full");
                } else if (findIndex(idField.getText()) == -1) {
                    students[index] = new Student(
                            idField.getText(),
                            nameField.getText(),
                            Double.parseDouble(cgpaField.getText())
                    );
                } else {
                    JOptionPane.showMessageDialog(this, "ID already exists");
                }
            }

            else if (e.getSource() == updateBtn) {
                int i = findIndex(idField.getText());
                if (i != -1) {
                    students[i].setName(nameField.getText());
                    students[i].setCgpa(Double.parseDouble(cgpaField.getText()));
                }
            }

            else if (e.getSource() == deleteBtn) {
                int i = findIndex(idField.getText());
                if (i != -1) students[i] = null;
            }

            else if (e.getSource() == searchBtn) {
                int i = findIndex(searchField.getText());
                if (i != -1) {
                    idField.setText(students[i].getId());
                    nameField.setText(students[i].getName());
                    cgpaField.setText(String.valueOf(students[i].getCgpa()));
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found");
                }
            }

            else if (e.getSource() == saveBtn) {
                FileIO.save(students);
                JOptionPane.showMessageDialog(this, "Saved successfully");
            }
            
            else if (e.getSource() == loadBtn) {
                FileIO.load(students);
            }

            else if (e.getSource() == clearBtn) {
                idField.setText("");
                nameField.setText("");
                cgpaField.setText("");
                searchField.setText("");
            }

            refreshDisplay();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: Check your input format");
        }
    }
}