CREATE TABLE availability_slots (
    availability_slot_id BIGSERIAL PRIMARY KEY,
    day_of_week VARCHAR(9) NOT NULL, -- Day of the week, use enum or VARCHAR
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    desk_id BIGINT REFERENCES desks(desk_id) ON DELETE CASCADE
);

