--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2019-08-11 20:51:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 210 (class 1259 OID 40931)
-- Name: pm_products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_products (
    item_id bigint NOT NULL,
    item_name character varying(100),
    price double precision,
    availability boolean,
    created_time timestamp(4) with time zone,
    updated_time timestamp(4) with time zone,
    category_id bigint NOT NULL,
    location character varying,
    user_id bigint,
    qty integer,
    image character varying(200),
    item_desc character varying(200),
    grade integer,
    units character varying(10)
);


ALTER TABLE public.pm_products OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 40929)
-- Name: items_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.items_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.items_item_id_seq OWNER TO postgres;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 209
-- Name: items_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.items_item_id_seq OWNED BY public.pm_products.item_id;


--
-- TOC entry 208 (class 1259 OID 40918)
-- Name: pm_order_prodcuts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_order_prodcuts (
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    item_id bigint,
    quantity integer,
    price double precision,
    user_id bigint
);


ALTER TABLE public.pm_order_prodcuts OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 40916)
-- Name: order_items_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_items_id_seq OWNER TO postgres;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 207
-- Name: order_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_items_id_seq OWNED BY public.pm_order_prodcuts.id;


--
-- TOC entry 203 (class 1259 OID 40891)
-- Name: pm_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_address (
    user_id bigint NOT NULL,
    street character varying(50),
    city character varying(50),
    state character varying(50),
    country character varying(50),
    first_name character varying(50),
    last_name character varying(50),
    addr_one character varying(100),
    addr_two character varying(100),
    pincode character varying(10),
    can_del boolean,
    addr_id bigint NOT NULL
);


ALTER TABLE public.pm_address OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 40956)
-- Name: pm_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_categories (
    category_id bigint NOT NULL,
    category_name character varying(50) NOT NULL,
    status boolean
);


ALTER TABLE public.pm_categories OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 40954)
-- Name: pm_categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_categories_category_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_categories_category_id_seq OWNER TO postgres;

--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 214
-- Name: pm_categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_categories_category_id_seq OWNED BY public.pm_categories.category_id;


--
-- TOC entry 216 (class 1259 OID 40964)
-- Name: pm_items_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_items_category_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_items_category_id_seq OWNER TO postgres;

--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 216
-- Name: pm_items_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_items_category_id_seq OWNED BY public.pm_products.category_id;


--
-- TOC entry 219 (class 1259 OID 41924)
-- Name: pm_ord_ngtns; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_ord_ngtns (
    ngtn_id bigint NOT NULL,
    ord_id bigint,
    ngtn_by_cstr_id bigint,
    ord_plcd_cstr_id bigint,
    ngtn_amnt double precision,
    ngtn_ord_crncy "char",
    ngtn_cmpltd boolean
);


ALTER TABLE public.pm_ord_ngtns OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 41922)
-- Name: pm_ord_ngtns_ngtn_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_ord_ngtns_ngtn_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_ord_ngtns_ngtn_id_seq OWNER TO postgres;

--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 218
-- Name: pm_ord_ngtns_ngtn_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_ord_ngtns_ngtn_id_seq OWNED BY public.pm_ord_ngtns.ngtn_id;


--
-- TOC entry 206 (class 1259 OID 40901)
-- Name: pm_orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_orders (
    order_id bigint NOT NULL,
    customer_id bigint NOT NULL,
    total_price double precision NOT NULL,
    payment_type character varying(50),
    payment_status character varying(20),
    order_status character varying,
    created_time timestamp(4) with time zone,
    updated_time timestamp(4) with time zone,
    placed_by_custmr_id bigint,
    ord_trck_stts character varying(50),
    order_by bigint
);


ALTER TABLE public.pm_orders OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 40899)
-- Name: pm_orders_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_orders_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_orders_customer_id_seq OWNER TO postgres;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 205
-- Name: pm_orders_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_orders_customer_id_seq OWNED BY public.pm_orders.customer_id;


--
-- TOC entry 204 (class 1259 OID 40897)
-- Name: pm_orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 204
-- Name: pm_orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_orders_order_id_seq OWNED BY public.pm_orders.order_id;


--
-- TOC entry 212 (class 1259 OID 40938)
-- Name: pm_payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_payments (
    payment_id bigint NOT NULL,
    amount double precision,
    trasctn_id bigint,
    bank_name character varying(100),
    payment_status "char",
    payment_mode integer,
    creation_ts timestamp(4) with time zone,
    updated_ts timestamp(4) with time zone,
    order_id bigint NOT NULL
);


ALTER TABLE public.pm_payments OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 40946)
-- Name: pm_payments_payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_payments_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_payments_payment_id_seq OWNER TO postgres;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 213
-- Name: pm_payments_payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_payments_payment_id_seq OWNED BY public.pm_payments.payment_id;


--
-- TOC entry 198 (class 1259 OID 40861)
-- Name: pm_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_roles (
    role_id bigint NOT NULL,
    role_name character varying(50),
    created_time date,
    updated_time date
);


ALTER TABLE public.pm_roles OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 40935)
-- Name: pm_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_transactions (
    trans_id bigint NOT NULL,
    order_id bigint,
    tras_status "char",
    created_ts date,
    update_ts date,
    user_id bigint
);


ALTER TABLE public.pm_transactions OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 40970)
-- Name: pm_transactions_trans_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_transactions_trans_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_transactions_trans_id_seq OWNER TO postgres;

--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 217
-- Name: pm_transactions_trans_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_transactions_trans_id_seq OWNED BY public.pm_transactions.trans_id;


--
-- TOC entry 220 (class 1259 OID 51097)
-- Name: pm_user_address_addr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_user_address_addr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_user_address_addr_id_seq OWNER TO postgres;

--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 220
-- Name: pm_user_address_addr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_user_address_addr_id_seq OWNED BY public.pm_address.addr_id;


--
-- TOC entry 202 (class 1259 OID 40889)
-- Name: pm_user_address_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_user_address_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_user_address_user_id_seq OWNER TO postgres;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 202
-- Name: pm_user_address_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_user_address_user_id_seq OWNED BY public.pm_address.user_id;


--
-- TOC entry 201 (class 1259 OID 40872)
-- Name: pm_user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_user_role (
    user_role_id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.pm_user_role OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 40870)
-- Name: pm_user_role_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_user_role_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_user_role_user_id_seq OWNER TO postgres;

--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 200
-- Name: pm_user_role_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_user_role_user_id_seq OWNED BY public.pm_user_role.user_id;


--
-- TOC entry 199 (class 1259 OID 40868)
-- Name: pm_user_role_user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_user_role_user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_user_role_user_role_id_seq OWNER TO postgres;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 199
-- Name: pm_user_role_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_user_role_user_role_id_seq OWNED BY public.pm_user_role.user_role_id;


--
-- TOC entry 197 (class 1259 OID 40846)
-- Name: pm_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pm_users (
    user_id bigint NOT NULL,
    first_name character varying(100),
    last_name character varying(100),
    email character varying(50),
    mobile_no character varying(100) NOT NULL,
    user_name character varying(100),
    created_time date,
    updated_time date,
    password character varying(100),
    user_stts character varying(20),
    rating double precision,
    image character varying(200),
    address character varying(300)
);


ALTER TABLE public.pm_users OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 40844)
-- Name: pm_users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pm_users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pm_users_user_id_seq OWNER TO postgres;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 196
-- Name: pm_users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pm_users_user_id_seq OWNED BY public.pm_users.user_id;


--
-- TOC entry 221 (class 1259 OID 53761)
-- Name: schema_history_pm_main; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schema_history_pm_main (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.schema_history_pm_main OWNER TO postgres;

--
-- TOC entry 2748 (class 2604 OID 40894)
-- Name: pm_address user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_address ALTER COLUMN user_id SET DEFAULT nextval('public.pm_user_address_user_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 51099)
-- Name: pm_address addr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_address ALTER COLUMN addr_id SET DEFAULT nextval('public.pm_user_address_addr_id_seq'::regclass);


--
-- TOC entry 2757 (class 2604 OID 51021)
-- Name: pm_categories category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_categories ALTER COLUMN category_id SET DEFAULT nextval('public.pm_categories_category_id_seq'::regclass);


--
-- TOC entry 2758 (class 2604 OID 41927)
-- Name: pm_ord_ngtns ngtn_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_ord_ngtns ALTER COLUMN ngtn_id SET DEFAULT nextval('public.pm_ord_ngtns_ngtn_id_seq'::regclass);


--
-- TOC entry 2752 (class 2604 OID 40921)
-- Name: pm_order_prodcuts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_order_prodcuts ALTER COLUMN id SET DEFAULT nextval('public.order_items_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 40904)
-- Name: pm_orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders ALTER COLUMN order_id SET DEFAULT nextval('public.pm_orders_order_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 40905)
-- Name: pm_orders customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders ALTER COLUMN customer_id SET DEFAULT nextval('public.pm_orders_customer_id_seq'::regclass);


--
-- TOC entry 2756 (class 2604 OID 40948)
-- Name: pm_payments payment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_payments ALTER COLUMN payment_id SET DEFAULT nextval('public.pm_payments_payment_id_seq'::regclass);


--
-- TOC entry 2753 (class 2604 OID 40934)
-- Name: pm_products item_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_products ALTER COLUMN item_id SET DEFAULT nextval('public.items_item_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 51035)
-- Name: pm_products category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_products ALTER COLUMN category_id SET DEFAULT nextval('public.pm_items_category_id_seq'::regclass);


--
-- TOC entry 2755 (class 2604 OID 40972)
-- Name: pm_transactions trans_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_transactions ALTER COLUMN trans_id SET DEFAULT nextval('public.pm_transactions_trans_id_seq'::regclass);


--
-- TOC entry 2746 (class 2604 OID 40875)
-- Name: pm_user_role user_role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_user_role ALTER COLUMN user_role_id SET DEFAULT nextval('public.pm_user_role_user_role_id_seq'::regclass);


--
-- TOC entry 2747 (class 2604 OID 40876)
-- Name: pm_user_role user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_user_role ALTER COLUMN user_id SET DEFAULT nextval('public.pm_user_role_user_id_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 40849)
-- Name: pm_users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_users ALTER COLUMN user_id SET DEFAULT nextval('public.pm_users_user_id_seq'::regclass);


--
-- TOC entry 2943 (class 0 OID 40891)
-- Dependencies: 203
-- Data for Name: pm_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_address (user_id, street, city, state, country, first_name, last_name, addr_one, addr_two, pincode, can_del, addr_id) FROM stdin;
\.


--
-- TOC entry 2955 (class 0 OID 40956)
-- Dependencies: 215
-- Data for Name: pm_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_categories (category_id, category_name, status) FROM stdin;
1	RICE	t
2	DAL	t
\.


--
-- TOC entry 2959 (class 0 OID 41924)
-- Dependencies: 219
-- Data for Name: pm_ord_ngtns; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_ord_ngtns (ngtn_id, ord_id, ngtn_by_cstr_id, ord_plcd_cstr_id, ngtn_amnt, ngtn_ord_crncy, ngtn_cmpltd) FROM stdin;
\.


--
-- TOC entry 2948 (class 0 OID 40918)
-- Dependencies: 208
-- Data for Name: pm_order_prodcuts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_order_prodcuts (id, order_id, item_id, quantity, price, user_id) FROM stdin;
1	1	1	100	23.329999999999998	2
2	2	2	100	25.329999999999998	2
4	4	4	100	25.329999999999998	2
\.


--
-- TOC entry 2946 (class 0 OID 40901)
-- Dependencies: 206
-- Data for Name: pm_orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_orders (order_id, customer_id, total_price, payment_type, payment_status, order_status, created_time, updated_time, placed_by_custmr_id, ord_trck_stts, order_by) FROM stdin;
2	2	25.329999999999998	NA	NA	OPEN	2019-07-01 00:00:00+05:30	2019-07-01 00:00:00+05:30	2	NA	\N
4	2	25.329999999999998	NA	NA	CLOSED	2019-07-01 21:23:19.959+05:30	2019-07-01 21:23:19.959+05:30	2	NA	\N
1	2	23.329999999999998	NA	NA	Order delivery inprogress	2019-07-01 00:00:00+05:30	2019-07-01 00:00:00+05:30	2	NA	2
\.


--
-- TOC entry 2952 (class 0 OID 40938)
-- Dependencies: 212
-- Data for Name: pm_payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_payments (payment_id, amount, trasctn_id, bank_name, payment_status, payment_mode, creation_ts, updated_ts, order_id) FROM stdin;
\.


--
-- TOC entry 2950 (class 0 OID 40931)
-- Dependencies: 210
-- Data for Name: pm_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_products (item_id, item_name, price, availability, created_time, updated_time, category_id, location, user_id, qty, image, item_desc, grade, units) FROM stdin;
4	Swarna masuri 51	25.329999999999998	t	2019-07-01 21:23:19.935+05:30	2019-07-01 21:23:19.935+05:30	1	Andhra	2	100	custom_image	\N	\N	\N
2	Swarna masuri 50	25.329999999999998	t	2019-07-01 00:00:00+05:30	2019-07-01 00:00:00+05:30	1	Andhra	2	100	custom_image	masuri rice	2	KG
1	Swarna masuri new	23.329999999999998	t	2019-07-01 00:00:00+05:30	2019-07-01 00:00:00+05:30	1	Karnataka	2	100	\\products\\12\\air_logistics.jpg	Sonamasuri rice	2	KG
\.


--
-- TOC entry 2938 (class 0 OID 40861)
-- Dependencies: 198
-- Data for Name: pm_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_roles (role_id, role_name, created_time, updated_time) FROM stdin;
\.


--
-- TOC entry 2951 (class 0 OID 40935)
-- Dependencies: 211
-- Data for Name: pm_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_transactions (trans_id, order_id, tras_status, created_ts, update_ts, user_id) FROM stdin;
\.


--
-- TOC entry 2941 (class 0 OID 40872)
-- Dependencies: 201
-- Data for Name: pm_user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_user_role (user_role_id, role_id, user_id) FROM stdin;
\.


--
-- TOC entry 2937 (class 0 OID 40846)
-- Dependencies: 197
-- Data for Name: pm_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pm_users (user_id, first_name, last_name, email, mobile_no, user_name, created_time, updated_time, password, user_stts, rating, image, address) FROM stdin;
2	\N	\N	tata	89800000	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 2961 (class 0 OID 53761)
-- Dependencies: 221
-- Data for Name: schema_history_pm_main; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schema_history_pm_main (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	0.9	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	postgres	2019-08-11 12:38:54.380231	0	t
2	1.1565506991805	orderby key	SQL	V1_1565506991805__orderby_key.sql	-476606204	postgres	2019-08-11 12:38:54.399484	33	t
\.


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 209
-- Name: items_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_item_id_seq', 4, true);


--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 207
-- Name: order_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_items_id_seq', 4, true);


--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 214
-- Name: pm_categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_categories_category_id_seq', 2, true);


--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 216
-- Name: pm_items_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_items_category_id_seq', 1, false);


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 218
-- Name: pm_ord_ngtns_ngtn_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_ord_ngtns_ngtn_id_seq', 1, false);


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 205
-- Name: pm_orders_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_orders_customer_id_seq', 1, false);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 204
-- Name: pm_orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_orders_order_id_seq', 4, true);


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 213
-- Name: pm_payments_payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_payments_payment_id_seq', 1, false);


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 217
-- Name: pm_transactions_trans_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_transactions_trans_id_seq', 1, false);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 220
-- Name: pm_user_address_addr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_user_address_addr_id_seq', 1, false);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 202
-- Name: pm_user_address_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_user_address_user_id_seq', 1, false);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 200
-- Name: pm_user_role_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_user_role_user_id_seq', 1, false);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 199
-- Name: pm_user_role_user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_user_role_user_role_id_seq', 1, false);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 196
-- Name: pm_users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pm_users_user_id_seq', 6, true);


--
-- TOC entry 2781 (class 2606 OID 41005)
-- Name: pm_products item_prm_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_products
    ADD CONSTRAINT item_prm_key PRIMARY KEY (item_id);


--
-- TOC entry 2779 (class 2606 OID 40923)
-- Name: pm_order_prodcuts order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_order_prodcuts
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (id);


--
-- TOC entry 2787 (class 2606 OID 51023)
-- Name: pm_categories pm_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_categories
    ADD CONSTRAINT pm_categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 2793 (class 2606 OID 41929)
-- Name: pm_ord_ngtns pm_ord_ngtns_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_ord_ngtns
    ADD CONSTRAINT pm_ord_ngtns_pkey PRIMARY KEY (ngtn_id);


--
-- TOC entry 2777 (class 2606 OID 40910)
-- Name: pm_orders pm_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders
    ADD CONSTRAINT pm_orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2785 (class 2606 OID 40953)
-- Name: pm_payments pm_payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_payments
    ADD CONSTRAINT pm_payments_pkey PRIMARY KEY (payment_id);


--
-- TOC entry 2767 (class 2606 OID 40865)
-- Name: pm_roles pm_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_roles
    ADD CONSTRAINT pm_roles_pkey PRIMARY KEY (role_id);


--
-- TOC entry 2783 (class 2606 OID 40977)
-- Name: pm_transactions pm_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_transactions
    ADD CONSTRAINT pm_transactions_pkey PRIMARY KEY (trans_id);


--
-- TOC entry 2775 (class 2606 OID 51107)
-- Name: pm_address pm_user_address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_address
    ADD CONSTRAINT pm_user_address_pkey PRIMARY KEY (addr_id);


--
-- TOC entry 2773 (class 2606 OID 40878)
-- Name: pm_user_role pm_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_user_role
    ADD CONSTRAINT pm_user_role_pkey PRIMARY KEY (user_role_id);


--
-- TOC entry 2761 (class 2606 OID 40854)
-- Name: pm_users pm_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_users
    ADD CONSTRAINT pm_users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2795 (class 2606 OID 53769)
-- Name: schema_history_pm_main schema_history_pm_main_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schema_history_pm_main
    ADD CONSTRAINT schema_history_pm_main_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2769 (class 2606 OID 41031)
-- Name: pm_roles ukcjswxfkliai4hubj4jc61lhbm; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_roles
    ADD CONSTRAINT ukcjswxfkliai4hubj4jc61lhbm UNIQUE (role_name);


--
-- TOC entry 2789 (class 2606 OID 41029)
-- Name: pm_categories ukcosrt606q1v1ar3vmo5hppa8u; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_categories
    ADD CONSTRAINT ukcosrt606q1v1ar3vmo5hppa8u UNIQUE (category_name);


--
-- TOC entry 2763 (class 2606 OID 41033)
-- Name: pm_users ukjtu556xfjtgfylet6s9ks2sp5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_users
    ADD CONSTRAINT ukjtu556xfjtgfylet6s9ks2sp5 UNIQUE (mobile_no);


--
-- TOC entry 2765 (class 2606 OID 40860)
-- Name: pm_users unique_mbl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_users
    ADD CONSTRAINT unique_mbl UNIQUE (mobile_no);


--
-- TOC entry 2771 (class 2606 OID 40867)
-- Name: pm_roles unique_role_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_roles
    ADD CONSTRAINT unique_role_name UNIQUE (role_name);


--
-- TOC entry 2791 (class 2606 OID 40963)
-- Name: pm_categories unq_catg_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_categories
    ADD CONSTRAINT unq_catg_id UNIQUE (category_name);


--
-- TOC entry 2796 (class 1259 OID 53770)
-- Name: schema_history_pm_main_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX schema_history_pm_main_s_idx ON public.schema_history_pm_main USING btree (success);


--
-- TOC entry 2802 (class 2606 OID 53771)
-- Name: pm_orders constraint_fk_orderby; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders
    ADD CONSTRAINT constraint_fk_orderby FOREIGN KEY (order_by) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2799 (class 2606 OID 51108)
-- Name: pm_address fk_addr_usr_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_address
    ADD CONSTRAINT fk_addr_usr_key FOREIGN KEY (user_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2800 (class 2606 OID 40911)
-- Name: pm_orders fk_customer_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders
    ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2807 (class 2606 OID 51036)
-- Name: pm_products fk_item_id_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_products
    ADD CONSTRAINT fk_item_id_key FOREIGN KEY (category_id) REFERENCES public.pm_categories(category_id);


--
-- TOC entry 2805 (class 2606 OID 41020)
-- Name: pm_order_prodcuts fk_itm_usr_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_order_prodcuts
    ADD CONSTRAINT fk_itm_usr_usr_id FOREIGN KEY (user_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2812 (class 2606 OID 41930)
-- Name: pm_ord_ngtns fk_ngtn_cust_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_ord_ngtns
    ADD CONSTRAINT fk_ngtn_cust_id FOREIGN KEY (ngtn_by_cstr_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2814 (class 2606 OID 41940)
-- Name: pm_ord_ngtns fk_ngtn_ord_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_ord_ngtns
    ADD CONSTRAINT fk_ngtn_ord_id FOREIGN KEY (ord_id) REFERENCES public.pm_orders(order_id);


--
-- TOC entry 2813 (class 2606 OID 41935)
-- Name: pm_ord_ngtns fk_ngtn_plcd_cstmr_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_ord_ngtns
    ADD CONSTRAINT fk_ngtn_plcd_cstmr_id FOREIGN KEY (ord_plcd_cstr_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2801 (class 2606 OID 53756)
-- Name: pm_orders fk_order_by; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_orders
    ADD CONSTRAINT fk_order_by FOREIGN KEY (order_by) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2803 (class 2606 OID 40924)
-- Name: pm_order_prodcuts fk_order_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_order_prodcuts
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.pm_orders(order_id);


--
-- TOC entry 2804 (class 2606 OID 41011)
-- Name: pm_order_prodcuts fk_orditem_itemid_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_order_prodcuts
    ADD CONSTRAINT fk_orditem_itemid_key FOREIGN KEY (item_id) REFERENCES public.pm_products(item_id);


--
-- TOC entry 2811 (class 2606 OID 40994)
-- Name: pm_payments fk_pmnt_ord_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_payments
    ADD CONSTRAINT fk_pmnt_ord_id FOREIGN KEY (order_id) REFERENCES public.pm_orders(order_id);


--
-- TOC entry 2806 (class 2606 OID 41917)
-- Name: pm_products fk_prod_usr_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_products
    ADD CONSTRAINT fk_prod_usr_key FOREIGN KEY (user_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2797 (class 2606 OID 40879)
-- Name: pm_user_role fk_role_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_user_role
    ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES public.pm_roles(role_id);


--
-- TOC entry 2810 (class 2606 OID 40984)
-- Name: pm_payments fk_trans_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_payments
    ADD CONSTRAINT fk_trans_id FOREIGN KEY (trasctn_id) REFERENCES public.pm_transactions(trans_id);


--
-- TOC entry 2808 (class 2606 OID 40978)
-- Name: pm_transactions fk_trans_ord_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_transactions
    ADD CONSTRAINT fk_trans_ord_id FOREIGN KEY (order_id) REFERENCES public.pm_orders(order_id);


--
-- TOC entry 2809 (class 2606 OID 40989)
-- Name: pm_transactions fk_trns_usr_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_transactions
    ADD CONSTRAINT fk_trns_usr_id FOREIGN KEY (user_id) REFERENCES public.pm_users(user_id);


--
-- TOC entry 2798 (class 2606 OID 40884)
-- Name: pm_user_role fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pm_user_role
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.pm_users(user_id);


-- Completed on 2019-08-11 20:51:39

--
-- PostgreSQL database dump complete
--

