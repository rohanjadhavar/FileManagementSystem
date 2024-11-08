package com.fileManageent.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FileRepo extends JpaRepository<Files, Long> {

    // Method to find a file by its ID
    @Query("SELECT f FROM Files f WHERE f.id = :id")
    Files findFileById(@Param("id") Long id);

    // Method to find a list of files associated with a specific user
    @Query("SELECT f FROM Files f WHERE f.user = :user")
    List<Files> findFilesByUser(@Param("user") User user);
}
