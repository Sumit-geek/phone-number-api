insert into customers
(id, created_time)
values('0522415f-0f7a-42b1-a072-3dd6f45beccb', now());

insert into customers
(id, created_time)
values('54fd31fc-0753-4742-82b7-6369cc6a5662', now());

insert into phone_numbers
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '0522415f-0f7a-42b1-a072-3dd6f45beccb', '111111', 'Active', now());

insert into phone_numbers
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '0522415f-0f7a-42b1-a072-3dd6f45beccb', '222222', 'Active', now());

insert into phone_numbers
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '54fd31fc-0753-4742-82b7-6369cc6a5662', '333333', 'InActive', now());

insert into phone_numbers
(id, customer_id, phone_no, status, created_time)
values(uuid_generate_v4(), '54fd31fc-0753-4742-82b7-6369cc6a5662', '444444', 'Active', now());
