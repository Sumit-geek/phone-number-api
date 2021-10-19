insert into customer
(id, customer_id, created_time)
values('0522415f-0f7a-42b1-a072-3dd6f45beccb', now());

insert into customer
(id, customer_id, created_time)
values('54fd31fc-0753-4742-82b7-6369cc6a5662', now());

insert into phone_number
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '1111', '0411', 'active', now());

insert into phone_number
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '1111', '0422', 'active', now());

insert into phone_number
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '2222', '0433', 'inActive', now());

insert into phone_number
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '2222', '0444', 'active', now());
