CREATE TABLE desks (
    desk_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    minimum_booking_notice INTERVAL,
    min_gap_between_bookings INTERVAL,
    school_id BIGINT REFERENCES schools(school_id) ON DELETE CASCADE
);