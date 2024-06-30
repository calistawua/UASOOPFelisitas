import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Pengaturan JFrame
        setTitle("Perpustakaan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat tombol untuk pindah ke JFrame kedua (LibraryFrame)
        JButton manageBooksButton = new JButton("Manage Books");
        manageBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka JFrame kedua
                LibraryFrame libraryFrame = new LibraryFrame();
                libraryFrame.setVisible(true);
                // Menyembunyikan JFrame saat ini
                dispose();
            }
        });

        // Menambahkan tombol ke JFrame
        add(manageBooksButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
