package com.dnstechpack.installer;

import com.dnstechpack.installer.gui.InstallerFrame;

import javax.swing.*;


public class Installer {

    public static void main(String... args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new InstallerFrame();
            }
        });
    }
}