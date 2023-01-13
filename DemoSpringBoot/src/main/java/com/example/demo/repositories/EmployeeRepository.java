package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // @Query(nativeQuery = true,
    //         value = "SELECT , transiction.quantity,transiction.instrument_name, transiction.Price,instrument.LTP"
    //         + "FROM employee"
    //         + "INNER JOIN transiction"
    //         + "ON instrument.instrument=transiction.instrument_name")
    // public List<Object[]> getTransictionsAndInstruments();
}
