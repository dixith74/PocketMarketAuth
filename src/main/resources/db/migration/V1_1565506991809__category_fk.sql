ALTER TABLE public.pm_product_config
    ADD CONSTRAINT constratint_fk_cat_id FOREIGN KEY (category_id)
    REFERENCES public.pm_categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;