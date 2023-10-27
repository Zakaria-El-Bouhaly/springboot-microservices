insert into roles (id, name)
values (1, 'admin'),
       (2, 'user');

insert into users (id, email,is_activated, full_name, password)
values (1, 'ccasini0@hibu.com', 'Carter Casini',true, '$2a$10$OBKOGCzJu8YmLGxWYJfNlu4RlRkakWnWVNd/ZYw9BvCdbRQVFqdSW')
     , (2, 'jarmiger1@seesaa.net', 'Josey Armiger',false, '$2a$10$b7/jEOwAvfc6.wU8vvh7.eKbtK4DtvM6yddZk97VAYJ59V4SgO0du');


insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2);


insert into offers (id, name, description, phone_number, city, image, price, latitude, longitude, user_id)
values (1, 'Bluestem', 'NA', '459-510-5248', 'Itsukaichi', 'http://dummyimage.com/220x100.png/dddddd/000000', 47,
        37.0348033, 138.2465787, 2)
     , (3, 'Harper', 'OC', '277-705-7548', 'asfi', 'http://dummyimage.com/247x100.png/5fa2dd/ffffff', 24, 9.9372776,
        105.6236585, 1)
     , (4, 'Dennis', 'OC', '172-239-9802', 'Touho', 'http://dummyimage.com/224x100.png/5fa2dd/ffffff', 7, -20.7795586,
        165.2386673, 2);

insert into bookings (id, check_in, check_out, offer_id, user_id)
values (1, '1588550400000', '1588550400000', 1, 1),
       (2, '1588550400000', '1584550400000', 3, 2),
       (3, '1588550400000', '1588250400000', 4, 1);