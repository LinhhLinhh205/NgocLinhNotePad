/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoTable;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author PC
 */
public class JTabledemo extends JFrame {

    private JLabel lbTen, lbGia;
    private JTable tblTaiKhoan;
    private JButton btnThem, btnXoa;
    private JTextField txtTen, txtGia;
    private DefaultTableModel tablemodel;

    public JTabledemo() {
        setTitle("DemoJTable");
        setSize(800, 500);
        createGUI();
        processEvent();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {
        JPanel p = new JPanel();
        Object[][] data = {
            {"Nguyễn Ngọc Linh", "45000"},
            {"Hồ Ly Siết", "47000"},
            {"Quách La Háng", "10000"},
            {"Mã La Toảng", "50000"},
            {"Nhã Màng La", "34000"},
            {"Đào Thế Mã", "66000"},};
        String[] columnNames = {"Tên tài khoản", "Số tiền"};
        tablemodel = new DefaultTableModel(data, columnNames);
        tblTaiKhoan = new JTable(tablemodel);
        JScrollPane scroll = new JScrollPane(tblTaiKhoan);

        p.add(new JLabel("Tên tài khoản: "));
        p.add(txtTen = new JTextField(15));
        p.add(new JLabel("Số tiền: "));
        p.add(txtGia = new JTextField(10));

        p.add(btnThem = new JButton("Thêm"));
        p.add(btnXoa = new JButton("Xoá"));
        btnThem.setIcon(new ImageIcon(this.getClass().getResource("/plus.png")));
        btnXoa.setIcon(new ImageIcon(this.getClass().getResource("/trash.png")));
        add(p);
        add(p, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JTabledemo frm = new JTabledemo();
        frm.setVisible(true);
    }

    private void processEvent() {
        btnThem.addActionListener((e) -> {
            String error = "";
            String tentaikhoan = txtTen.getText();
            String giasanpham = txtGia.getText();

            if (tentaikhoan.length() == 0) {
                error = "Bạn chưa nhập mã sản phẩm";
            } else {
                for (char c : tentaikhoan.toCharArray()) {
                    if (Character.isDigit(c)) {
                        error += "\n Tên tài khoản không chứa số";
                        break;
                    }
                }
            }
            if (giasanpham.length() == 0) {
                error += "\n Bạn chưa nhập giá sản phẩm";
            }
            if (!error.isEmpty()) {
                JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Double giasp = Double.parseDouble(giasanpham);
                tablemodel.addRow(new Object[]{tentaikhoan, giasanpham});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnXoa.addActionListener((e) -> {
            int selectedIndex = tblTaiKhoan.getSelectedRow();
            if (selectedIndex > 0) {
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không") == JOptionPane.OK_OPTION) {
                    tablemodel.removeRow(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa có dòng chọn cần xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
