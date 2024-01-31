package br.com.alura.tabelafipe.services;

import java.util.List;

public interface IConversorDeDados {
    <T> T converterObjeto(String json, Class<T> classe);

    <T> List<T> converterLista(String json, Class<T> classe);
}
