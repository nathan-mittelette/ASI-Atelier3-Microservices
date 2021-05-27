package com.usermicroservice.usermicroservice.services;

import com.usermicroservice.usermicroservice.services.iservices.ICrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T> implements ICrudService<T> {
    protected CrudRepository<T, Long> _repository;

    public CrudService(CrudRepository<T, Long> repository) {
        _repository = repository;
    }

    public List<T> findAll() {
        return (List<T>) _repository.findAll();
    }

    public Optional<T> findById(Long id) throws Exception {
        return _repository.findById(id);
    }

    public T insertOrUpdate(T obj) {
        return _repository.save(obj);
    }

    public void insertOrUpdateMultiple(List<T> obj) {
        _repository.saveAll(obj);
    }

    public void delete(T obj) {
        _repository.delete(obj);
    }

    public void deleteMultiple(List<T> obj) {
        _repository.deleteAll(obj);
    }
}
