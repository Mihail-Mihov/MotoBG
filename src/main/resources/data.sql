-- brands
INSERT INTO brands (id, brand)
VALUES (1, 'Aprilia');
INSERT INTO brands (id, brand)
VALUES (2, 'Ducati');
INSERT INTO brands (id, brand)
VALUES (3, 'Kawasaki');
INSERT INTO brands (id, brand)
VALUES (4, 'Suzuki');

-- models
insert into models (id, model, brand_id)
values (1, 'KX', 3);
insert into models (id, model, brand_id)
values (2, 'Ninja', 3);
insert into models (id, model, brand_id)
values (3, 'KLX', 3);

-- cities
insert into cities (id, city_name)
values (1, 'Sofia');
insert into cities (id, city_name)
values (2, 'Plovdiv');
insert into cities (id, city_name)
values (3, 'Varna');
insert into cities (id, city_name)
values (4, 'Burgas');
insert into cities (id, city_name)
values (5, 'Gabrovo');

-- some test users
INSERT INTO users (id, active, created, description, email, first_name, home_town , last_name, modified, password, phone_number, profile_picture_url, username)
VALUES (1, 1, CURRENT_DATE, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. LoremIpsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer' ,
        'admin@admin.com', 'AdminName', 'Sofia' ,'AdminFamily', CURRENT_DATE ,
        'd39fea9652d0bfae8a991c7b77828f93fd5fa20ebdfdaa1685b9baefebc1c472e52e7c5dcd73d8c9',
        '089777777' , 'https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745', 'admin');

        INSERT INTO users (id, active, created, description, email, first_name, home_town , last_name, modified, password, phone_number, profile_picture_url, username)
        VALUES (2, 1, CURRENT_DATE, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. LoremIpsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer' ,
                'test@test.com', 'Petar', 'Gabrovo' ,'Georgiev', CURRENT_DATE ,
                'd39fea9652d0bfae8a991c7b77828f93fd5fa20ebdfdaa1685b9baefebc1c472e52e7c5dcd73d8c9',
                '089777777' , 'https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745', 'pesho');

        INSERT INTO users (id, active, created, description, email, first_name, home_town , last_name, modified, password, phone_number, profile_picture_url, username)
        VALUES (3, 1, CURRENT_DATE, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. LoremIpsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer' ,
                'joro@joro.com', 'Joro', 'Burgas' ,'Ignatov', CURRENT_DATE ,
                'd39fea9652d0bfae8a991c7b77828f93fd5fa20ebdfdaa1685b9baefebc1c472e52e7c5dcd73d8c9',
                '089777777' , 'https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745', 'joro');

        INSERT INTO users (id, active, created, description, email, first_name, home_town , last_name, modified, password, phone_number, profile_picture_url, username)
        VALUES (4, 1, CURRENT_DATE, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. LoremIpsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer' ,
                'svetlin@nakov.com', 'Svetlin', 'Smolyan' ,'Nakov', CURRENT_DATE ,
                'd39fea9652d0bfae8a991c7b77828f93fd5fa20ebdfdaa1685b9baefebc1c472e52e7c5dcd73d8c9',
                '089777777' , 'https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745', 'svetlin');


-- user roles
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (2, 2);
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (3, 2);
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (4, 2);



--# INSERT INTO pictures(image_url, title, author_id, offer_id)
--# VALUES ();
--
--
    INSERT INTO offers (id, brand, city, created, description, image_url, item_condition, model, modified, old_price, price, rate,
       title, year, author_id)
    VALUES (1, 'Suzuki', 'Sofia', DATE_SUB(CURDATE(),INTERVAL 30 DAY) , 'A brand new motorcycle. Please buy it, i need money!',
        'https://yamaha.ggmoto.net/wp-content/uploads/2022/01/2022-Yamaha-YZ125LC-EU-Icon_Blue_-Studio-001-03.jpg',
        'Нов', 'GSX r125', CURRENT_DATE, 5000, 4200, 0, 'Suzuki GSX r125 - НОВ',
            DATE('2003-12-31') , 1 );

          INSERT INTO offers (id, brand, city, created, description, image_url, item_condition, model, modified, old_price, price, rate,
               title, year, author_id)
            VALUES (2, 'Honda', 'Burgas', DATE_SUB(CURDATE(),INTERVAL 30 DAY) , 'A brand new motorcycle. Please buy it, i need money!',
                'https://www.motoroads.com/photos/big-2014-honda-xr250-tornado-rent-a-motorbike-in-morocco-marrakech.jpeg',
                'Употребяван', 'XR 250', NULL, 9000, 6000, 0, 'Употребяван мотор Honda XR 250',
                    DATE('2011-12-31') , 2);

         INSERT INTO offers (id, brand, city, created, description, image_url, item_condition, model, modified, old_price, price, rate,
                   title, year, author_id)
                VALUES (3, 'Aprilia', 'Gabrovo', DATE_SUB(CURDATE(),INTERVAL 30 DAY) , 'A brand new motorcycle Aprilia. Please buy it, i need money!',
                    'https://ultimatemotorcycling.com/wp-content/uploads/2021/04/2021-Aprilia-RSV4-Factory-Review-superbike-motorcycle-test-laguna-seca-6.jpg',
                    'Нов', 'RSV4', NULL, NULL, 6200, 0, 'Употребяван мотор Aprilia RSV4',
                        DATE('2021-01-01') , 3);

          INSERT INTO offers (id, brand, city, created, description, image_url, item_condition, model, modified, old_price, price, rate,
                   title, year, author_id)
                VALUES (4, 'Vespa', 'Sofia', DATE_SUB(CURDATE(),INTERVAL 30 DAY) , 'A brand new motorcycle Vespa. Please buy it, i need money!',
                    'https://autoline.bg/img/s/avtomobil-skuter-Vespa-Primavera-125---1660866199526465548_big--22081902430302080200.jpg',
                    'Нов', 'Primavera', NULL, NULL, 6200, 0, 'Veespa Primavera 125',
                        DATE('2019-01-01') , 4);

         INSERT INTO offers (id, brand, city, created, description, image_url, item_condition, model, modified, old_price, price, rate,
                   title, year, author_id)
                VALUES (5, 'Balkan', 'Sofia', DATE_SUB(CURDATE(),INTERVAL 30 DAY) , 'A retro moto Balkan.',
                    'https://upload.wikimedia.org/wikipedia/commons/e/e8/Balkan_MK50-3Typ_2.JPG',
                    'За части', 'МК50', NULL, NULL, 6200, 0, 'Veespa Primavera 125',
                        DATE('1998-01-01') , 4);



--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 2' , '2021-12-19 18:48:12', 2, 1);
--
--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 3' , '2021-12-19 18:48:12', 2, 2);
--
--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 4' , '2021-12-19 18:48:12', 2, 3);
--
--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 5' , '2021-12-19 18:48:12', 2, 4);
--
--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 6' , '2021-12-19 18:48:12', 2, 5);
--
--insert into comments (can_approve, can_delete, comment, created, author_id, offer_id)
--values  (true, true, 'test comment 7' , '2021-12-19 18:48:12', 2, 6);
