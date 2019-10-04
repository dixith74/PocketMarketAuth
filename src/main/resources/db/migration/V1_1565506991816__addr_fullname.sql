ALTER TABLE public.pm_address DROP COLUMN IF EXISTS first_name;
ALTER TABLE public.pm_address DROP COLUMN IF EXISTS last_name;

ALTER TABLE public.pm_address ADD COLUMN IF NOT EXISTS full_name character varying(50);