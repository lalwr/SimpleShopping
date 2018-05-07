insert into users(name, email, address, phone, password, regdate, use) values ('sungpark', 'urstory@gmail.com', '서울시 강동구', '01025544745', '{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into users(name, email, address, phone, password, regdate, use) values ('lee', 'lee@gmail.com', '서울시 송파구', '01087462645','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into users(name, email, address, phone, password, regdate, use) values ('admin', 'admin@gmail.com', '서울시 강남구', '01065449425','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into user_roles(user_no, role_name) values (1, 'USER');
insert into user_roles(user_no, role_name) values (2, 'ADMIN');
insert into user_roles(user_no, role_name) values (3, 'ADMIN');
insert into user_roles(user_no, role_name) values (3, 'USER');