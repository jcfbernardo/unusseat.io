CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  creation_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE events (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(100) NOT NULL,
  event_date DATE NOT NULL CHECK (event_date >= CURRENT_DATE),
  total_capacity INT NOT NULL CHECK (total_capacity > 0)
);

CREATE TABLE tickets (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  event_id UUID NOT NULL REFERENCES events(id) ON DELETE RESTRICT,
  seat_identifier VARCHAR(10) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
  UNIQUE (event_id, seat_identifier)
);

CREATE TABLE reservations (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL REFERENCES users(id),
  ticket_id UUID NOT NULL REFERENCES tickets(id),
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  expiration TIMESTAMPTZ NOT NULL CHECK (expiration > CURRENT_TIMESTAMP),
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX idx_active_reservations
ON reservations(ticket_id)
WHERE status IN ('PENDING', 'CONFIRMED');
