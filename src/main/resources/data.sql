INSERT INTO category(category_name) VALUES ('men');
INSERT INTO category(category_name) VALUES ('women');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(1, 79, 50, 'Perfect for Summer Days', 'guy1.png', 'Blue Striped Casual Dress');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(2, 129, 23, 'Elegant and Flowy', 'girl2.png', 'Pink Floral Maxi Dress');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(2, 89, 30, 'Classic and Chic', 'girl3.png', 'White Lace Blouse');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(1, 109, 15, 'Stylish and Versatile', 'guy2.png', 'Black Denim Jeans');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(1, 69, 50, 'Casual and Trendy', 'guy3.png', 'Red Checkered Shirt');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(1, 49, 30, 'Fun and Flirty', 'guy5.png', 'Striped Crop Top');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(1, 99, 60, 'Playful and Vibrant', 'guy4.png', 'Yellow Ruffled Dress');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(2, 79, 100, 'Chic and Sophisticated', 'girl4.png', 'Printed Midi Skirt');

INSERT INTO products(category_id, price, quantity_in_stock, description, image, product_name)
    VALUES(2, 89, 70, 'Quirky and Cute', 'ex2.png', 'Blue Polka Dot Dress');

INSERT INTO customer(name,e_mail, password) VALUES('kaijie', '212858@stuednt.upm.edu.my', 'password');
INSERT INTO customer(name,e_mail, password) VALUES('John', '233333@stuednt.upm.edu.my', 'password');

INSERT INTO orders(customer_id) VALUES (1);
INSERT INTO orders(customer_id) VALUES (1);
INSERT INTO order_product(order_id, product_id, quantity) VALUES (1, 2, 10);
INSERT INTO order_product(order_id, product_id, quantity) VALUES (1, 3, 10);
INSERT INTO order_product(order_id, product_id, quantity) VALUES (1, 4, 10);
INSERT INTO order_product(order_id, product_id, quantity) VALUES (2, 5, 5);
INSERT INTO order_product(order_id, product_id, quantity) VALUES (2, 7, 1);