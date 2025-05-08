CREATE TABLE appointments (
    appointment_id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    desk_id BIGINT REFERENCES desks(desk_id) ON DELETE CASCADE,
    student_id BIGINT REFERENCES students(student_id) ON DELETE CASCADE,
    time_zone VARCHAR(100) NOT NULL
);