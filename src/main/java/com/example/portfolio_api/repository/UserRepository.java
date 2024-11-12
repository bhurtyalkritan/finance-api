// src/main/java/com/example/portfolio_api/repository/UserRepository.java

package com.example.portfolio_api.repository;

import com.example.portfolio_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their account number.
     *
     * @param accountNumber The account number of the user.
     * @return The User entity.
     */
    User findByAccountNumber(String accountNumber);

    /**
     * Find a user by their email.
     *
     * @param email The email of the user.
     * @return An Optional containing the User if found, else empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists by their email.
     *
     * @param email The email to check.
     * @return True if a user with the given email exists, else false.
     */
    boolean existsByEmail(String email);

    /**
     * Find a user by their email using a native SQL query.
     *
     * @param email The email of the user.
     * @return The User entity.
     */
    @Query(value = "SELECT * FROM users u WHERE u.email = ?1", nativeQuery = true)
    User findByEmailNative(String email);

    /**
     * Get the total count of users in the database.
     *
     * @return The count of users.
     */
    @Query(value = "SELECT COUNT(*) FROM users", nativeQuery = true)
    int getUserCount();

    /**
     * Call a stored procedure to get a user's email by their ID.
     *
     * @param id The ID of the user.
     * @return The email of the user.
     */
    @Procedure(procedureName = "GET_USER_EMAIL")
    String getUserEmailById(@Param("ID_IN") Long id);

    /**
     * Static method to simulate a stored procedure for H2 database.
     * This is useful for testing purposes.
     *
     * @param connection The database connection.
     * @param id_in      The ID of the user.
     * @return The email of the user, or null if not found.
     * @throws SQLException If a database access error occurs.
     */
    static String getUserEmailById(Connection connection, Long id_in) throws SQLException {
        String email = null;
        String sql = "SELECT email FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id_in);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        }
        return email;
    }
}
