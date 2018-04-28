insert into users(id, name, email, address, phone, password, regdate) values (1, 'sungpark', 'urstory@gmail.com', '서울시 강동구', '010-2554-4745', '{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into users(id, name, email, address, phone, password, regdate) values (2, 'lee', 'lee@gmail.com', '서울시 송파구', '010-8746-2645','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into users(id, name, email, address, phone, password, regdate) values (3, 'admin', 'kim@naver.com', '서울시 강서구', '010-6544-9425','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into user_roles(user_no, role_name) values (1, 'USER');
insert into user_roles(user_no, role_name) values (2, 'ADMIN');
insert into user_roles(user_no, role_name) values (3, 'USER');