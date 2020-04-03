package br.com.wagner.agenda.business

import br.com.wagner.agenda.bean.ContactBean
import br.com.wagner.agenda.repository.ContactRepository

class ContactBusiness {

    fun validaSave(nome: String, fone: String) {
        if (nome == "") {
            throw Exception("Campo <Nome> não pode ser vazio")
        } else if (fone == "") {
            throw Exception("Campo <Telefone> não pode ser vazio")
        }
    }

    fun save(nome: String, fone: String) {
        validaSave(nome, fone);
        val contact = ContactBean(nome, fone);
        ContactRepository.save(contact);
    }


    fun validaDelete(nome: String, fone: String) {
        if (ContactRepository.getListaContatos().isEmpty()) {
            throw Exception("Não há itens para remover")
        } else if (nome == "" || fone == "") {
            throw Exception("Selecione um item")
        }

    }

    fun delete(nome: String, fone: String) {
        validaDelete(nome, fone)
        val contact = ContactBean(nome, fone)
        ContactRepository.delete(contact)
    }

    fun getListaContatos():List<ContactBean> {
        return ContactRepository.getListaContatos()
    }
}