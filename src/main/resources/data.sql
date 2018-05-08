insert into users(name, email, address, phone, password, regdate, use) values ('sungpark', 'urstory@gmail.com', '서울시 강동구', '01025544745', '{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into users(name, email, address, phone, password, regdate, use) values ('lee', 'lee@gmail.com', '서울시 송파구', '01087462645','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into users(name, email, address, phone, password, regdate, use) values ('admin', 'admin@gmail.com', '서울시 강남구', '01065449425','{bcrypt}$2a$10$YawlEecaIvN5xMW7886JhOj6Iuy..f.XfoGy8/vJCW/5iRQlP3pzy', now(), 'Y');
insert into user_roles(user_no, role_name) values (1, 'USER');
insert into user_roles(user_no, role_name) values (2, 'ADMIN');
insert into user_roles(user_no, role_name) values (3, 'ADMIN');
insert into user_roles(user_no, role_name) values (3, 'USER');

insert into category(no, name, use) values(null, 'All', 'Y');
insert into category(no, name, use) values(null, 'Linux', 'Y');
insert into category(no, name, use) values(null, 'Programming', 'Y');
insert into category(no, name, use) values(null, 'Computer', 'Y');
insert into category(no, name, use) values(null, 'ETC', 'Y');

insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker1', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker2', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker3', 1500, 1, now(), '2', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker4', 1500, 1, now(), '2', '상품설명', null);
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
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker17', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker18', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker19', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker20', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker21', 1500, 1, now(), '4', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker22', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker23', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker24', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker25', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker26', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker27', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker28', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker29', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker30', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker31', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker32', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker33', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker34', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker35', 1500, 1, now(), '5', '상품설명', null);
insert into product(no, name, price, amount, regdate, category_no, description, image_path) values (null, 'sticker36', 1500, 1, now(), '5', '상품설명', null);


insert into cart(no, amount, user_no, product_no) values(null, 1, 1, 1);
insert into cart(no, amount, user_no, product_no) values(null, 2, 1, 2);
insert into cart(no, amount, user_no, product_no) values(null, 3, 1, 3);