package com.investment.apiinvestiment.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface CrudController<T, F> {
    /**
     * Rota para buscar todos os registros não deletados.
     *
     * @return Retorna todos os registros do sistema não deletados
     */
    @GetMapping
    @ApiOperation(value = "List")
    public ResponseEntity<List<T>> findAll();

    /**
     * Rota para buscar registros pelo nome.
     *
     * @param startDate filtro data início
     * @param endDate   filtro data fim
     * @param filters   filtro genérico
     * @param pageable  parâmetros que definem a paginação
     * @return Retorna a página de itens buscada
     */
    @GetMapping(value = "/pageable")
    @ResponseBody
    public Page<T> findPageable(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate, String filters, Pageable pageable);

    /**
     * Rota para criar um registro.
     *
     * @param form                 dados do registro
     * @return Retorna o registro criado
     */
    @PostMapping
    @ApiOperation(value = "Create")
    public ResponseEntity<T> create(@RequestBody @Valid F form);

    /**
     * Rota para buscar um registro.
     *
     * @param id registro que será buscado na base de dados
     * @return Retorna o registro pesquisado
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Search by ID")
    public ResponseEntity<T> findById(@PathVariable Long id);

    /**
     * Rota para atualizar um registro.
     *
     * @param id   registro que será atualizado na base de dados
     * @param form dados do registro
     * @return Retorna o registro atualizado
     */
    @PutMapping("/{id}")

    @ApiOperation(value = "Update")

    @Transactional
    public ResponseEntity<T> update(@PathVariable Long id,
                                    @RequestBody @Valid F form);

    /**
     * Rota para deletar um registro.
     *
     * @param id registro que será deletado de forma lógica na base de dados
     * @return Retorna o registro deletado
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete")
    @Transactional
    public ResponseEntity<T> delete(@PathVariable Long id) throws IOException;
}


