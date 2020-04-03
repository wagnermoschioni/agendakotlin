package br.com.wagner.agenda.repository

import br.com.wagner.agenda.bean.ContactBean
import javax.swing.JOptionPane

class ContactRepository {
    companion object {
        private val listaContatos = mutableListOf<ContactBean>()

        fun save(contact: ContactBean) {
            listaContatos.add(contact)
        }

        fun delete(contact: ContactBean) {
            //listaContatos.remove(contact)
           var index = 0;
            for (item in listaContatos) {
                if (item.nome == contact.nome && item.fone == contact.fone) {
                    break
                }
                index++
            }
            //var op: Int = JOptionPane.YES_OPTION
            var op = JOptionPane.showConfirmDialog(null,"Deseja excluir o contato '${contact.nome}' ?")
            if (op == JOptionPane.YES_OPTION)
            listaContatos.removeAt(index)
        }

        fun getListaContatos(): List<ContactBean> {
            return listaContatos
        }
    }
}