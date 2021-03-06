package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class FileChooser extends JFrame {

    protected static FileBrowser browser;

    public static FileChooser INSTANCE;

    public FileChooser(JTextField field, File defaultDir) {

        this.setTitle("Choose Directory");
        this.setSize(new Dimension(500, 400));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);

        browser = new FileBrowser(defaultDir, field);
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.getContentPane().add(browser);

        INSTANCE = this;
    }

    public class FileBrowser extends JFileChooser {

        private JTextField textBox;
        private File defaultDir;

        public FileBrowser(File defaultDir, JTextField textBox) {

            super(defaultDir);
            this.textBox = textBox;
            this.defaultDir = defaultDir;
            setupCustom();
        }

        public void setupCustom() {

            this.addActionListener(new ActionListener() {

                boolean allowed = true;

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(e.getActionCommand().equals("ApproveSelection")) {

                        try {

                            if(defaultDir == InstallerUtils.mcDefault) {

                                File file = new File(FileChooser.browser.getSelectedFile(), "versions/" + InstallerUtils.settings.getMCVersion() + "/" + InstallerUtils.settings.getMCVersion() + ".jar");

                                if(!file.exists()) {

                                    allowed = false;
                                    JOptionPane.showMessageDialog(null, "Make Sure You Have Run The Launcher Once");
                                    return;
                                }
                            }

                            if(allowed) {

                                FileChooser.browser.textBox.setText(FileChooser.browser.getSelectedFile().getCanonicalPath());
                                FileChooser.INSTANCE.setVisible(false);
                            }
                        } catch(IOException e1) {

                            e1.printStackTrace();
                        }
                    } else if(e.getActionCommand().equals("CancelSelection")) {

                        FileChooser.INSTANCE.setVisible(false);
                    }
                }
            });
        }
    }
}
