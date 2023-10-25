package org.demoSHA;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelHashString;
    private JLabel lblInputText;
    private JTextArea textAreaResult;
    private JButton btnHashingText;
    private JTextArea textAreaInput;
    private JPanel panelHashFile;
    private JButton btnOpenFile;
    private JTextField textFieldFileUrl;
    private JButton btnHashingFile;
    private JTextArea textAreaFileHashing;
    private File file;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setTitle("Demo SHA-256");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(630, 234);

        contentPane = new JPanel();
        contentPane.setLayout(new MigLayout("fillx", "[grow, fill]", "[][]"));

        panelHashString = new JPanel(new MigLayout("fillx", "[grow, fill]", "[][]"));
        panelHashString.setBorder(BorderFactory.createTitledBorder("Hashing String"));

        lblInputText = new JLabel("Input Text:");
        textAreaResult = new JTextArea();
        textAreaResult.setLineWrap(true);
        textAreaResult.setEditable(false);

        btnHashingText = new JButton("Generate >>");
        btnHashingText.addActionListener(e -> {
            String input = textAreaInput.getText();
            String result = SHAHashing.getSHAHash(input);
            textAreaResult.setText(result);
        });

        textAreaInput = new JTextArea();
        textAreaInput.setLineWrap(true);

        panelHashString.add(lblInputText, "alignx left");
        panelHashString.add(textAreaInput, "alignx left, growx, pushx, span 2");
        panelHashString.add(btnHashingText, "alignx center, grow");
        panelHashString.add(new JLabel("SHA-256 Hash:"), "alignx left");
        panelHashString.add(textAreaResult, "alignx left, growx, pushx");


        panelHashFile = new JPanel(new MigLayout("fillx", "[grow, fill]", "[][]"));
        panelHashFile.setBorder(BorderFactory.createTitledBorder("Hashing File"));

        btnOpenFile = new JButton("Open File");
        btnOpenFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int status = fileChooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                textFieldFileUrl.setText(file.getAbsolutePath());
            }
        });

        textFieldFileUrl = new JTextField();
        textFieldFileUrl.setEditable(false);

        btnHashingFile = new JButton("Check SHA");
        btnHashingFile.addActionListener(e -> {
            if (file == null) {
                JOptionPane.showMessageDialog(null, "Please select a file first.", "No File Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String result = null;
            try {
                result = SHAHashing.getSHAHash(file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            textAreaFileHashing.setText(result);
        });

        textAreaFileHashing = new JTextArea();
        textAreaFileHashing.setLineWrap(true);
        textAreaFileHashing.setEditable(false);

        panelHashFile.add(btnOpenFile);
        panelHashFile.add(textFieldFileUrl, "growx, pushx");
        panelHashFile.add(btnHashingFile, "wrap");
        panelHashFile.add(new JLabel("SHA-256 Hash:"), "alignx left");
        panelHashFile.add(textAreaFileHashing, "growx, pushx");


        contentPane.add(panelHashString, "growx, wrap");
        contentPane.add(panelHashFile, "growx");

        add(contentPane);

    }
}
