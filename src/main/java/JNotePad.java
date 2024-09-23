/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
import java.awt.BorderLayout;
import java.awt.Font;
//import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author ADMIN
 */
public class JNotePad extends JFrame {

    private JMenuBar menuBar;
    private JMenu mfile, medit, mformat, mview, mhelp, mzoom;
    private JMenuItem inew, iopen, isave, isaveas, ipage, iprint, iexit,
            iundo, icut, icopy, ipaste, idelete, isearch, ifind, ireplace, igoto, iselect, itime,
            ifont,
            izoomin, izoomout, iretore,
            iviewhelp, isend, iabout;
    private JCheckBoxMenuItem iWrap, iStatus;
    private File currentfile;
    private JTextArea txtArea;
    private UndoManager undomanage;
    private int size = 20;
    private JToolBar toolbar;
    private JButton btnNew, btnOpen, btnSave;

    public JNotePad(String title) {
        setTitle(title);
        createMenu();
        createGui();
        createToolbar();
        processEvent();
        undomanage = new UndoManager();
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }
    

    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.add(mfile = new JMenu("File"));
        menuBar.add(medit = new JMenu("Edit"));
        menuBar.add(mformat = new JMenu("Format"));
        menuBar.add(mview = new JMenu("View"));
        menuBar.add(mhelp = new JMenu("Help"));

        medit.add(iundo = new JMenuItem("Undo"));
        medit.add(icut = new JMenuItem("Cut"));
        medit.add(icopy = new JMenuItem("Copy"));
        medit.add(ipaste = new JMenuItem("Paste"));
        medit.add(idelete = new JMenuItem("Delete"));
        medit.addSeparator();
        medit.add(isearch = new JMenuItem("Search..."));
        medit.add(ifind = new JMenuItem("Find..."));
        medit.add(ireplace = new JMenuItem("Replace"));
        medit.add(igoto = new JMenuItem("Go to..."));
        medit.addSeparator();
        medit.add(iselect = new JMenuItem("Select All"));
        medit.add(itime = new JMenuItem("Time/ Date"));

        mfile.add(inew = new JMenuItem("New"));
        mfile.add(iopen = new JMenuItem("Open..."));
        mfile.add(isave = new JMenuItem("Save"));
        mfile.add(isaveas = new JMenuItem("Save As..."));
        mfile.addSeparator();
        mfile.add(ipage = new JMenuItem("Page Setup..."));
        mfile.add(iprint = new JMenuItem("Print..."));
        mfile.addSeparator();
        mfile.add(iexit = new JMenuItem("Exit"));

        mformat.add(iWrap = new JCheckBoxMenuItem("Word Wrap"));
        mformat.add(ifont = new JMenuItem("Font..."));

        mview.add(mzoom = new JMenu("Zoom"));
        mzoom.add(izoomin = new JMenuItem("Zoom In"));
        mzoom.add(izoomout = new JMenuItem("Zoom Out"));
        mzoom.add(iretore = new JMenuItem("Restore Default Zoom"));
        mview.add(iStatus = new JCheckBoxMenuItem("Status Bar"));

        mhelp.add(iviewhelp = new JMenuItem("View Help"));
        mhelp.add(isend = new JMenuItem("Send FeedBack"));
        mhelp.addSeparator();
        mhelp.add(iabout = new JMenuItem("About NotePad"));

        iundo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        icut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        icopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        ipaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        idelete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
        isearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        ifind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        ireplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        igoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        iselect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itime.setAccelerator(KeyStroke.getKeyStroke("F5"));

        izoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        izoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        iretore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

        inew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        iopen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        isave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        isaveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        iprint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

        setJMenuBar(menuBar);
    }

    private void createGui() {
        txtArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtArea);
        add(scroll);
        txtArea.setLineWrap(true);
        txtArea.setFont(new Font("Arial", Font.PLAIN, 20));
        txtArea.getDocument().addUndoableEditListener(undomanage);
    }

    private void processEvent() {
        iexit.addActionListener((e) -> {
            if (JOptionPane.showConfirmDialog(null, "Do you want exit to Notepad?") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        isave.addActionListener((e) -> {
            saveFile();
        });
        isaveas.addActionListener((e) -> {
            saveasfile();
        });

        iopen.addActionListener((e) -> {
            openfile();
        });
        iWrap.addItemListener((e) -> {
            if (iWrap.isSelected()) {
                txtArea.setLineWrap(true);
            } else {
                txtArea.setLineWrap(false);
            }
        });
        inew.addActionListener((e) -> {
            newfile();
        });
        icopy.addActionListener((e) -> {
            txtArea.copy();
        });
        icut.addActionListener((e) -> {
            txtArea.cut();
        });
        ipaste.addActionListener((e) -> {
            txtArea.paste();
        });
        itime.addActionListener((e) -> {
            timefile();
        });
        idelete.addActionListener((e) -> {
            deletefile();
        });
        iundo.addActionListener((e) -> {
            undofile();
        });
        izoomin.addActionListener((e) -> {
            size += 4;
            txtArea.setFont(new Font("Arial", Font.PLAIN, size));
        });
        izoomout.addActionListener((e) -> {
            size -= 4;
            txtArea.setFont(new Font("Arial", Font.PLAIN, size));
        });
        iretore.addActionListener((e) -> {
            size = 20;
            txtArea.setFont(new Font("Arial", Font.PLAIN, size));
        });
        ifont.addActionListener((e) -> {
            JNoteDialog fontDialog = new JNoteDialog(this, true);
            fontDialog.setVisible(true);
        });
        
    };

    private void saveFile() {
        if (currentfile == null) {
            saveasfile();
        } else {
            try (FileWriter wr = new FileWriter(currentfile)) {
                txtArea.write(wr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi lưu file");
            }
        }
    }

    private void saveasfile() {
        JFileChooser sflie = new JFileChooser();
        if (sflie.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            try {
                currentfile = sflie.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(currentfile);
                try {
                    fos.write(txtArea.getText().getBytes());
                    fos.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi ghi file");
            }
        }
    }

    private void openfile() {
        JFileChooser ofile = new JFileChooser();
        if (ofile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                currentfile = ofile.getSelectedFile();
                FileInputStream fis = new FileInputStream(ofile.getSelectedFile());
                byte[] b = new byte[fis.available()];
                fis.read(b);
                txtArea.setText(new String(b));
                fis.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi đọc file");
            }
        }
    }

    private void newfile() {
        if (txtArea.getText().length() > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want save changes to Untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                saveFile();
            } else if (confirm == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        txtArea.setText("");
        currentfile = null;
    }

    private void timefile() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a M/d/yyyy");
        txtArea.append(formatter.format(date));
    }

    private void deletefile() {
        if (txtArea.getSelectedText() != null) {
            int start = txtArea.getSelectionStart();
            int end = txtArea.getSelectionEnd();
            txtArea.replaceRange("", start, end);
        } else {
            txtArea.setText("");
        }
    }

    private void undofile() {
        if (undomanage.canUndo()) {
            try {
                undomanage.undo();
            } catch (CannotUndoException ex) {
                JOptionPane.showMessageDialog(this, "Không thể hoàn tác.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có gì để hoàn tác.");
        }

    }

    private void createToolbar() {
        toolbar = new JToolBar();
        toolbar.add(btnNew=new JButton("New"));
        toolbar.add(btnOpen=new JButton("Open"));
        toolbar.add(btnSave=new JButton("Save"));
        
        btnNew.setIcon(new ImageIcon(this.getClass().getResource("/plus.png")));
        btnOpen.setIcon(new ImageIcon(this.getClass().getResource("/folder.png")));
        btnSave.setIcon(new ImageIcon(this.getClass().getResource("/savings.png")));
        btnNew.addActionListener((e)->{
            newfile();
        });
        btnOpen.addActionListener((e)->{
            openfile();
        });
        btnSave.addActionListener((e)->{
            saveFile();
        });
        add(toolbar, BorderLayout.NORTH);
        
    }

}
