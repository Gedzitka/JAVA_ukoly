import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;

class NadrzGUI extends JFrame {
    private final JTextField kapacitaField = new JTextField("100", 8);
    private final JTextField stavField = new JTextField("0", 8);
    private final JTextField mnozstviField = new JTextField("10", 8);
    private final JComboBox<TypObsahu> typComboBox = new JComboBox<>(TypObsahu.values());
    private final JLabel stavLabel = new JLabel("Nádrž není vytvořena.");
    private final JTextArea logArea = new JTextArea(8, 32);

    private NadrzOperace nadrz;

    NadrzGUI() {
        super("Nádrž - ovládání");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(vytvorVstupniPanel(), BorderLayout.NORTH);
        add(vytvorTlacitkaPanel(), BorderLayout.CENTER);
        add(vytvorLogPanel(), BorderLayout.SOUTH);

        setMinimumSize(new Dimension(520, 380));
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel vytvorVstupniPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Nastavení nádrže"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.WEST;

        pridatRadek(panel, c, 0, "Kapacita:", kapacitaField);
        pridatRadek(panel, c, 1, "Počáteční stav:", stavField);
        pridatRadek(panel, c, 2, "Typ obsahu:", typComboBox);

        JButton vytvoritButton = new JButton("Vytvořit / obnovit nádrž");
        vytvoritButton.addActionListener(e -> vytvorNadrz());

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(vytvoritButton, c);

        c.gridy = 4;
        panel.add(stavLabel, c);

        return panel;
    }

    private JPanel vytvorTlacitkaPanel() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBorder(BorderFactory.createTitledBorder("Operace"));

        // Horní řádek: vstup množství
        JPanel vstupni = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0;
        vstupni.add(new JLabel("Množství:"), c);
        c.gridx = 1;
        vstupni.add(mnozstviField, c);

        panel.add(vstupni, BorderLayout.NORTH);

        // Střední část: vertikální tlačítka
        JPanel tlacitka = new JPanel();
        tlacitka.setLayout(new BoxLayout(tlacitka, BoxLayout.Y_AXIS));

        JButton naplnitButton = new JButton("Plnit nádrž");
        naplnitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        naplnitButton.setMaximumSize(new Dimension(220, 36));
        naplnitButton.addActionListener(e -> provedOperaci(true));

        JButton odebratButton = new JButton("Odebrat z nádrže");
        odebratButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        odebratButton.setMaximumSize(new Dimension(220, 36));
        odebratButton.addActionListener(e -> provedOperaci(false));

        tlacitka.add(Box.createVerticalStrut(8));
        tlacitka.add(naplnitButton);
        tlacitka.add(Box.createVerticalStrut(8));
        tlacitka.add(odebratButton);

        panel.add(tlacitka, BorderLayout.CENTER);

        return panel;
    }

    private JScrollPane vytvorLogPanel() {
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(480, 160));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Výpis"));
        return scrollPane;
    }

    private void pridatRadek(JPanel panel, GridBagConstraints c, int row, String text, Component component) {
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        panel.add(new JLabel(text), c);

        c.gridx = 1;
        panel.add(component, c);
    }

    private void vytvorNadrz() {
        try {
            int kapacita = Integer.parseInt(kapacitaField.getText().trim());
            int stav = Integer.parseInt(stavField.getText().trim());
            TypObsahu typObsahu = (TypObsahu) typComboBox.getSelectedItem();
            nadrz = new Nadrz(kapacita, typObsahu, stav);
            aktualizujStav();
            zapisDoLogu("Vytvořena nádrž: " + nadrz.getStav());
        } catch (NumberFormatException ex) {
            zobrazChybu("Kapacita i počáteční stav musí být celá čísla.");
        } catch (IllegalArgumentException ex) {
            zobrazChybu(ex.getMessage());
        }
    }

    private void provedOperaci(boolean plnit) {
        if (nadrz == null) {
            zobrazChybu("Nejprve vytvořte nádrž.");
            return;
        }

        try {
            int mnozstvi = Integer.parseInt(mnozstviField.getText().trim());
            if (plnit) {
                nadrz.plnit(mnozstvi);
                zapisDoLogu("Naplněno o " + mnozstvi + ". Stav: " + nadrz.getStav());
            } else {
                nadrz.odebrat(mnozstvi);
                zapisDoLogu("Odebráno " + mnozstvi + ". Stav: " + nadrz.getStav());
            }
            aktualizujStav();
        } catch (NumberFormatException ex) {
            zobrazChybu("Množství musí být celé číslo.");
        } catch (MyException_PlnaNadrz | MyException_PrazdnaNadrz | IllegalArgumentException ex) {
            zobrazChybu(ex.getMessage());
        }
    }

    private void aktualizujStav() {
        if (nadrz == null) {
            stavLabel.setText("Nádrž není vytvořena.");
            return;
        }

        stavLabel.setText("Aktuální stav: " + nadrz.getStav());
    }

    private void zobrazChybu(String message) {
        JOptionPane.showMessageDialog(this, message, "Chyba", JOptionPane.ERROR_MESSAGE);
        zapisDoLogu("CHYBA: " + message);
    }

    private void zapisDoLogu(String message) {
        logArea.append(message + System.lineSeparator());
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    static void spust() {
        SwingUtilities.invokeLater(() -> new NadrzGUI().setVisible(true));
    }
}