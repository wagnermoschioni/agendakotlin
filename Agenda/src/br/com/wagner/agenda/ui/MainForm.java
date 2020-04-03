package br.com.wagner.agenda.ui;

import br.com.wagner.agenda.bean.ContactBean;
import br.com.wagner.agenda.business.ContactBusiness;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JLabel lblTotal;
    private JTable tblContatos;

    private ContactBusiness mContactBusiness = new ContactBusiness();
    private String nomeContato = "";
    private String foneContato = "";

    public MainForm() {

        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
        carregarContatos();

    }

    public void carregarContatos() {
        List<ContactBean> lista = mContactBusiness.getListaContatos();
        String[] coluna = {"Nome","Telefone"};
        DefaultTableModel modelo = new DefaultTableModel(new Object[0][0], coluna);

        for (ContactBean item : lista) {
        Object[] obj = new Object[2];
        obj[0] = item.getNome();
        obj[1] = item.getFone();

        modelo.addRow(obj);
        }
        tblContatos.clearSelection();
        tblContatos.setModel(modelo);

        lblTotal.setText(String.valueOf(lista.size()));
    }

    /**
     * Método para atribuir funcoes para os elementos/botoes dos Forms
     */
    public void setListeners() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });
        /**
         * Metodo com Table
         */
        tblContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {

                  if (tblContatos.getSelectedRow() != -1) {
                      nomeContato = tblContatos.getValueAt(tblContatos.getSelectedRow(), 0).toString();
                      foneContato = tblContatos.getValueAt(tblContatos.getSelectedRow(), 1).toString();
                  }


            }
        }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    mContactBusiness.delete(nomeContato, foneContato);
                    carregarContatos();
                    nomeContato = "";
                    foneContato = "";
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }

            }
        });
    }
}
