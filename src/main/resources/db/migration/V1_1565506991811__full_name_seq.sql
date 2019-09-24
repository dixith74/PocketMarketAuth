ALTER TABLE public.pm_users DROP COLUMN IF EXISTS user_type;

ALTER TABLE public.pm_users ADD COLUMN IF NOT EXISTS full_name character varying(100);

SELECT setval('pm_users_user_id_seq', 10000, true);

SELECT setval('pm_orders_order_id_seq', 10000, true);

SELECT setval('items_item_id_seq', 10000, true);

SELECT setval('track_id_gen_seq', 10000, true);