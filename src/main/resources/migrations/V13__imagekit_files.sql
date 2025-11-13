CREATE TABLE IF NOT EXISTS imagekit_files (
    id BIGSERIAL PRIMARY KEY,
    file_id TEXT NOT NULL UNIQUE,    -- ImageKit.io unique File ID
    file_url TEXT NOT NULL,          -- ImageKit CDN URL for the file
    user_id TEXT NOT NULL,           -- User associated with the file
    upload_timestamp TIMESTAMP DEFAULT NOW()
);