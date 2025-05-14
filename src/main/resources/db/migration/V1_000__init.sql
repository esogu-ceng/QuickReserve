-- Create schools table
CREATE TABLE schools (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    timezone VARCHAR(50) NOT NULL
);

-- Create desks table
CREATE TABLE desks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    school_id BIGINT NOT NULL,
    CONSTRAINT fk_desk_school FOREIGN KEY (school_id) REFERENCES schools(id) ON DELETE CASCADE
);

-- Create working_hours table
CREATE TABLE working_hours (
    id BIGSERIAL PRIMARY KEY,
    day_of_week VARCHAR(20) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    desk_id BIGINT NOT NULL,
    CONSTRAINT fk_working_hours_desk FOREIGN KEY (desk_id) REFERENCES desks(id) ON DELETE CASCADE
);

-- Create students table
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    timezone VARCHAR(50) NOT NULL
);

-- Create appointment_slots table
CREATE TABLE appointment_slots (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT true,
    is_special BOOLEAN NOT NULL DEFAULT false,
    is_merged BOOLEAN NOT NULL DEFAULT false,
    desk_id BIGINT NOT NULL,
    CONSTRAINT fk_slot_desk FOREIGN KEY (desk_id) REFERENCES desks(id) ON DELETE CASCADE
);

-- Create appointments table
CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,
    slot_id BIGINT NOT NULL UNIQUE,
    student_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_appointment_slot FOREIGN KEY (slot_id) REFERENCES appointment_slots(id),
    CONSTRAINT fk_appointment_student FOREIGN KEY (student_id) REFERENCES students(id)
);