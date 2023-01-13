package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Division;

public interface DivisionService {
    public List<Division> getAll();
    public Division getById(Integer id);
    public boolean save(Division division);
    public boolean delete(Integer id);
}
