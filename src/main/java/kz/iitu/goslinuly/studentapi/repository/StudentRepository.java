package kz.iitu.goslinuly.studentapi.repository;

import kz.iitu.goslinuly.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
