package br.jus.treto.cemu.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class AutorizadorUsuario {

    private String matricula;
    private String nome;
    private String login;
    private String email;
    private AutorizadorUnidade lotacao;
    private List<String> perfis;
    private List<AutorizadorUnidade> unidadesAdicionais;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AutorizadorUnidade getLotacao() {
        return lotacao;
    }

    public void setLotacao(AutorizadorUnidade lotacao) {
        this.lotacao = lotacao;
    }

    public List<String> getPerfis() {
        if (this.perfis == null) {
            this.perfis = new ArrayList<>(0);
        }
        return this.perfis;
    }

    public void setPerfis(List<String> perfis) {
        this.perfis = perfis;
    }

    public List<AutorizadorUnidade> getUnidadesAdicionais() {
        if (this.unidadesAdicionais == null) {
            this.unidadesAdicionais = new ArrayList<>(0);
        }
        return this.unidadesAdicionais;
    }

    public void setUnidadesAdicionais(List<AutorizadorUnidade> unidadesAdicionais) {
        this.unidadesAdicionais = unidadesAdicionais;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}

