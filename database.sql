drop database if exists product_new;
create database product_new;
use product_new;

create table category (
                          category_id int primary key,
                          category_name varchar (50)
);

insert into category
values
(1, 'Phone'),
(2, 'Television'),
(3, 'Laptop');

create table product (
                         product_id int primary key auto_increment,
                         product_name varchar (50),
                         price int,
                         quantity int,
                         color varchar (50),
                         `description` varchar (50),
                         category_id int,
                         foreign key (category_id) references category(category_id) on delete cascade on update cascade
);

insert into product
values
(1, 'Nokia 7.3', 10000000, 10, 'Red', 'Nice', 1),
(2, 'Smart TV Samsung', 10000000, 5, 'Black', 'Nice', 2),
(3, 'Acer Nitro 5', 10000000, 7, 'Yellow', 'Nice', 3),
(4, 'Iphone 12', 20000000, 3, 'Green', 'Nice', 1),
(5, 'Dell Pro', 15000000, 11, 'White', 'Nice', 3),
(6, 'Asus ROG', 25000000, 12, 'Green', 'Nice', 3),
(7, 'HP Vip', 35000000, 15, 'Green', 'Nice', 3);

DELIMITER //
create procedure update_product (id_update int, name_update varchar(50), price_update int, quantity_update int,
                                 color_update varchar (50), description_update varchar (50), category_id_update int)
BEGIN
update product
set product_name = name_update, price = price_update, quantity = quantity_update, color = color_update,
    `description` = description_update, category_id = category_id_update
where product_id = id_update;
END //
DELIMITER ;

DELIMITER //
create procedure delete_product(id_need_delete int)
BEGIN
delete from product
where product_id = id_need_delete;
END //
DELIMITER ;

select * from category;