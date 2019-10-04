--ALTER TABLE public.pm_orders RENAME ord_posted_by TO placed_by_custmr_id;

--ALTER TABLE public.pm_orders RENAME ord_placed_by TO ord_cnfrm_by_id;

ALTER TABLE public.pm_orders RENAME placed_by_custmr_id TO ord_posted_by;

ALTER TABLE public.pm_orders RENAME ord_cnfrm_by_id TO ord_placed_by;