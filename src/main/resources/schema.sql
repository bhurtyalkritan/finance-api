-- src/main/resources/schema.sql

-- Create stored procedure for H2 Database
CREATE ALIAS IF NOT EXISTS GET_USER_EMAIL FOR "com.example.portfolio_api.repository.UserRepository.getUserEmailById";
