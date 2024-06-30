import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BukuForm extends JDialog {
    private JTextField judulField;
    private JTextField penulisField;
    private JTextField penerbitField;
    private JTextField tahunField;
    private Buku buku;
    private LibraryFrame libraryFrame;
    private int rowIndex;

    public BukuForm(LibraryFrame libraryFrame, Buku buku) {
        this.libraryFrame = libraryFrame;
        this.buku = buku;
        this.rowIndex = -1; // default value

        setTitle(buku == null ? "Add Book" : "Edit Book");
        setSize(300, 200);
        setLocationRelativeTo(libraryFrame);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Judul:"));
        judulField = new JTextField(buku == null ? "" : buku.getJudul());
        panel.add(judulField);

        panel.add(new JLabel("Penulis:"));
        penulisField = new JTextField(buku == null ? "" : buku.getPenulis());
        panel.add(penulisField);

        panel.add(new JLabel("Penerbit:"));
        penerbitField = new JTextField(buku == null ? "" : buku.getPenerbit());
        panel.add(penerbitField);

        panel.add(new JLabel("Tahun Terbit:"));
        tahunField = new JTextField(buku == null ? "" : String.valueOf(buku.getTahunTerbit()));
        panel.add(tahunField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = judulField.getText();
                String penulis = penulisField.getText();
                String penerbit = penerbitField.getText();
                int tahunTerbit = Integer.parseInt(tahunField.getText());

                if (buku == null) {
                    Buku newBuku = new Buku(judul, penulis, penerbit, tahunTerbit);
                    libraryFrame.addBuku(newBuku);
                } else {
                    buku.setJudul(judul);
                    buku.setPenulis(penulis);
                    buku.setPenerbit(penerbit);
                    buku.setTahunTerbit(tahunTerbit);
                    libraryFrame.updateBuku(buku, rowIndex);
                }
                dispose();
            }
        });

        panel.add(saveButton);

        add(panel);
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
