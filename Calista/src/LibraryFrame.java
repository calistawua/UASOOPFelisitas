import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryFrame extends JFrame {
    private ArrayList<Buku> bukuList;
    private DefaultTableModel tableModel;
    private JTable bukuTable;

    public LibraryFrame() {
        // Pengaturan JFrame
        setTitle("Library Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        bukuList = new ArrayList<>();

        // Kolom untuk JTable
        String[] columns = {"Judul", "Penulis", "Penerbit", "Tahun Terbit"};
        tableModel = new DefaultTableModel(columns, 0);
        bukuTable = new JTable(tableModel);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton editButton = new JButton("Edit Book");
        JButton deleteButton = new JButton("Delete Book");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BukuForm bukuForm = new BukuForm(LibraryFrame.this, null);
                bukuForm.setVisible(true);
            }
        });

      editButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = bukuTable.getSelectedRow();
        if (selectedRow != -1) {
            Buku selectedBuku = bukuList.get(selectedRow);
            BukuForm bukuForm = new BukuForm(LibraryFrame.this, selectedBuku);
            bukuForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(LibraryFrame.this, "Select a book to edit");
        }
    }
});

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bukuTable.getSelectedRow();
                if (selectedRow != -1) {
                    bukuList.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(LibraryFrame.this, "Select a book to delete");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                dispose();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        // Menambahkan JTable ke JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(bukuTable);

        // Menambahkan komponen ke JFrame
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addBuku(Buku buku) {
        bukuList.add(buku);
        Object[] row = {buku.getJudul(), buku.getPenulis(), buku.getPenerbit(), buku.getTahunTerbit()};
        tableModel.addRow(row);
    }

    public void updateBuku(Buku buku, int rowIndex) {
        bukuList.set(rowIndex, buku);
        tableModel.setValueAt(buku.getJudul(), rowIndex, 0);
        tableModel.setValueAt(buku.getPenulis(), rowIndex, 1);
        tableModel.setValueAt(buku.getPenerbit(), rowIndex, 2);
        tableModel.setValueAt(buku.getTahunTerbit(), rowIndex, 3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryFrame().setVisible(true);
            }
        });
    }
}
