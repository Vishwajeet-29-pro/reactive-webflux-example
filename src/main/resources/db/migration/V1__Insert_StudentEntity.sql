CREATE TABLE students (
    uuid UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    enrollment_date TIMESTAMP NOT NULL,
    course VARCHAR(255) NOT NULL,
    percentage DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL
);
