CREATE TABLE tedtalks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(500) NOT NULL,
    author VARCHAR(200) NOT NULL,
    date DATE NOT NULL,
    views BIGINT NOT NULL CHECK (views >= 0),
    likes BIGINT NOT NULL CHECK (likes >= 0),
    link VARCHAR(500) NOT NULL UNIQUE
);

CREATE INDEX idx_author ON tedtalks(author);
CREATE INDEX idx_date ON tedtalks(date);

