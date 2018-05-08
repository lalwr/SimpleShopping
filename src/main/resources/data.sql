insert into users(id, name, email, address, phone, password, regdate) values (1, 'sungpark', 'urstory@gmail.com', '서울시 강동구', '010-2554-4745', '{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into users(id, name, email, address, phone, password, regdate) values (2, 'lee', 'lee@gmail.com', '서울시 송파구', '010-8746-2645','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into users(id, name, email, address, phone, password, regdate) values (3, 'choi', 'choi@gmail.com', '서울시 강서구', '010-6544-9425','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now());
insert into user_roles(id, user_id, role_name) values (1, 1, 'USER');
insert into user_roles(id, user_id, role_name) values (2, 2, 'ADMIN');
insert into user_roles(id, user_id, role_name) values (3, 3, 'USER');

insert into category(no, name, use) values(null, 'All', 'Y');
insert into category(no, name, use) values(null, 'Linux', 'Y');
insert into category(no, name, use) values(null, 'Programming', 'Y');
insert into category(no, name, use) values(null, 'Computer', 'Y');
insert into category(no, name, use) values(null, 'ETC', 'Y');

insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker1', 1500, 1, now(), '1', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker2', 1500, 1, now(), '1', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker3', 1500, 1, now(), '1', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker4', 1500, 1, now(), '1', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker5', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker6', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker7', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker8', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker9', 1500, 1, now(), '3', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker10', 1500, 1, now(), '3', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker11', 1500, 1, now(), '3', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker12', 1500, 1, now(), '3', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker13', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker14', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker15', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker16', 1500, 1, now(), '4', '상품설명', null);
