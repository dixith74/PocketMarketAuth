CREATE TABLE public.pm_notifications
(
    notification_id serial NOT NULL,
    user_id bigint,
    notification character varying(200),
    is_read boolean,
    created_time timestamp with time zone,
    PRIMARY KEY (notification_id)
);