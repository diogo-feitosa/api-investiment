package com.investment.apiinvestiment.services;

import com.investment.apiinvestiment.forms.FilterForm;
import com.investment.apiinvestiment.models.EntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface CrudService<T extends EntityModel, F> {
    /**
     * Método para buscar todos os registros não deletados.
     *
     * @return Retorna todos os registros do sistema não deletados
     */
    public List<T> findAll();

    /**
     * Método para buscar registros pelo nome.
     *
     * @param startDate filtro data inicio
     * @param endDate filtro data fim
     * @param filters os parâmetros a serem usados para filtrar os registros
     * @param pageable os parâmetros que definem a paginação
     * @return Retorna a página de itens buscada
     */
    public Page<T> findPageable(LocalDateTime startDate, LocalDateTime endDate, List<FilterForm> filters, Pageable pageable);

    /**
     * Método para buscar um registro.
     *
     * @param id id do registro que será buscado na base de dados
     * @return Retorna o registro pesquisado
     */
    public T findById(Long id);

    /**
     * Método para criar um registro.
     *
     * @param form o form com os dados do registro
     * @return Retorna o registro criado
     */
    public T create(F form);

    /**
     * Método para atualizar um registro.
     *
     * @param id   id do registro que será atualizado na base de dados
     * @param form o form com os dados do registro
     * @return Retorna o registro atualizado
     */
    public T update(Long id, F form);

    /**
     * Método para deletar um registro.
     *
     * @param id id do registro que será deletado de forma lógica na base de dados
     * @return Retorna o registro deletado
     */
    public T delete(Long id) throws IOException;
}
