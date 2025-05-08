CREATE TABLE special_availabilities (
    special_availability_id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    available BOOLEAN NOT NULL,
    start_time TIME,
    end_time TIME,
    desk_id BIGINT REFERENCES desks(desk_id) ON DELETE CASCADE
);