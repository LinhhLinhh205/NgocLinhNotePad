
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class JNoteDialog extends JDialog {

    private JList listFontName, listStyle, listSize;
    private JLabel lbPreview;
    private JButton btOk, btCancel;
    private JLabel lbfont, lbstyle, lbsize;
    private JNotePad parent;
    private int[] arrStyle = {Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD};
    private Font font;

    public JNoteDialog(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("Font");
        parent = (JNotePad) owner;
        createGUI();
        setFont();
        processEvent();
        setSize(570, 450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
    }

    private void createGUI() {
        JPanel p = new JPanel();
        p.setLayout(null);

        String[] fontnames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        listFontName = new JList(fontnames);
        JScrollPane scrollFontName = new JScrollPane(listFontName);
        p.add(lbfont = new JLabel("Font:"));
        p.add(lbstyle = new JLabel("Font style:"));
        p.add(lbsize = new JLabel("Size:"));
        listFontName.setSelectedIndex(0);

        p.add(scrollFontName);
        scrollFontName.setBounds(5, 40, 200, 200);
        lbfont.setBounds(5, 15, 50, 30);

        String[] styles = {"Plain", "Italic", "Bold", "Italic Bold"};
        listStyle = new JList(styles);
        JScrollPane scrollStyle = new JScrollPane(listStyle);
        listStyle.setSelectedIndex(1);

        p.add(scrollStyle);
        scrollStyle.setBounds(225, 40, 200, 200);
        lbstyle.setBounds(225, 15, 100, 30);

        String[] size = {"8", "9", "10", "11", "12", "14", "16", "22", "24", "32", "48", "72"};
        listSize = new JList(size);
        JScrollPane scrollSize = new JScrollPane(listSize);
        listSize.setSelectedIndex(9);

        p.add(scrollSize);
        scrollSize.setBounds(445, 40, 100, 200);
        lbsize.setBounds(445, 5, 50, 50);
        
        JPanel sample=new JPanel();
        sample.setLayout(new GridLayout(1,2));
        sample.setBounds(250, 270, 300, 80);
        sample.setBorder(BorderFactory.createTitledBorder("Sample"));
        
        sample.add(lbPreview = new JLabel("AaBbYyZz"));
        lbPreview.setBounds(350, 280, 200, 70);

        p.add(btOk = new JButton("OK"));
        p.add(btCancel = new JButton("Cancel"));
        btOk.setBounds(330, 370, 100, 30);
        btCancel.setBounds(440, 370, 100, 30);
        p.add(sample);
        add(p);

    }

    private void setFont() {
        String name = (String) listFontName.getSelectedValue();
        int style = arrStyle[listStyle.getSelectedIndex()];
        int size = Integer.parseInt(listSize.getSelectedValue().toString());
        font = new Font(name, style, size);
        lbPreview.setFont(font);
    }

    private void processEvent() {
        listFontName.addListSelectionListener((e) -> {
            setFont();
        });

        listStyle.addListSelectionListener((e) -> {
            setFont();
        });
        listSize.addListSelectionListener(((e) -> {
            setFont();
        }));

        btOk.addActionListener((e) -> {

            parent.getTxtArea().setFont(font);

            this.dispose();
        });
        btCancel.addActionListener((e)->{
            this.dispose();
        });
    }

}
