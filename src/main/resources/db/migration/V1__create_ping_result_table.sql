CREATE TABLE ping_result (
                             id SERIAL PRIMARY KEY,
                             ip_address VARCHAR(255),
                             domain VARCHAR(255),
                             check_date TIMESTAMP,
                             test_status VARCHAR(50),
                             ping_result TEXT
);