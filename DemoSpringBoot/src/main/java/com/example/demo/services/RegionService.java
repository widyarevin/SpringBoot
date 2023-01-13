package com.example.demo.services;
import java.util.List;
import com.example.demo.models.Region;

public interface RegionService {
    public List<Region> getAll();
    public Region getById(Integer id);
    public boolean save(Region region);
    public boolean delete(Integer id);
}
