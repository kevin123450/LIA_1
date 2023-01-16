package com.example.filhanterare.repo;

import com.example.filhanterare.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBrepository extends JpaRepository<FileDB, String> {
}
