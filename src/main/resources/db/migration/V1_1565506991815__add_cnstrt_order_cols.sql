--ALTER TABLE pm_orders ADD CONSTRAINT contraint_fk_plcd_by_cstr_id FOREIGN KEY (placed_by_custmr_id) REFERENCES pm_users(user_id);

--ALTER TABLE pm_orders ADD CONSTRAINT constraint_fk_orderby FOREIGN KEY (ord_cnfrm_by_id) REFERENCES pm_users(user_id);

ALTER TABLE pm_orders ADD CONSTRAINT fk_ord_posted_by FOREIGN KEY (ord_posted_by) REFERENCES pm_users(user_id);

ALTER TABLE pm_orders ADD CONSTRAINT fk_ord_placed_by FOREIGN KEY (ord_placed_by) REFERENCES pm_users(user_id);