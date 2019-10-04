ALTER TABLE public.pm_address DROP COLUMN IF EXISTS can_del;

ALTER TABLE public.pm_address ADD COLUMN IF NOT EXISTS is_billing boolean;

ALTER TABLE public.pm_address ADD COLUMN IF NOT EXISTS is_shipping boolean;

ALTER TABLE public.pm_address ADD COLUMN IF NOT EXISTS active boolean;

ALTER TABLE public.pm_address ADD COLUMN IF NOT EXISTS created_time timestamp with time zone;

ALTER TABLE public.pm_orders ADD COLUMN IF NOT EXISTS shipping_id bigint;

ALTER TABLE public.pm_orders ADD COLUMN IF NOT EXISTS billing_id bigint;

ALTER TABLE public.pm_orders
    ADD CONSTRAINT fk_billing_id FOREIGN KEY (billing_id)
    REFERENCES public.pm_address (addr_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE public.pm_orders
    ADD CONSTRAINT fk_shipping_id FOREIGN KEY (shipping_id)
    REFERENCES public.pm_address (addr_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;	