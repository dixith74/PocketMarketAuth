CREATE TABLE IF NOT EXISTS public.pm_product_config
(
    config_id bigint NOT NULL,
    config_name character varying(255),
    config_desc character varying(255),
    availability boolean,
    image character varying(500),
    category_id bigint,
    PRIMARY KEY (config_id)
);