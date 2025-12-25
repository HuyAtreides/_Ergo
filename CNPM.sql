
CREATE DATABASE EcommerceDB;
GO
USE EcommerceDB;
GO


CREATE TABLE Address (
    address_id INT IDENTITY PRIMARY KEY,
    city_or_province NVARCHAR(100),
    district NVARCHAR(100),
    ward NVARCHAR(100),
    street_number NVARCHAR(255)
);

CREATE TABLE [User] (
    user_id INT IDENTITY PRIMARY KEY,
    email NVARCHAR(255) UNIQUE NOT NULL,
    name NVARCHAR(255),
    phone NVARCHAR(20),
    password NVARCHAR(255),
    address NVARCHAR(255), -- Địa chỉ text tự do
    gender NVARCHAR(10),
    status NVARCHAR(50),
    role_id NVARCHAR(50)
);

CREATE TABLE Customer (
    customer_id INT PRIMARY KEY,
    FOREIGN KEY (customer_id) REFERENCES [User](user_id)
);

CREATE TABLE Employee (
    employee_id INT PRIMARY KEY,
    FOREIGN KEY (employee_id) REFERENCES [User](user_id)
);

CREATE TABLE Administrator (
    admin_id INT PRIMARY KEY,
    FOREIGN KEY (admin_id) REFERENCES [User](user_id)
);

CREATE TABLE Category (
    category_id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(255),
    description NVARCHAR(500),
    status NVARCHAR(50)
);

CREATE TABLE Product (
    product_id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(255),
    price DECIMAL(18, 2),
    material NVARCHAR(100),
    description NVARCHAR(MAX),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

CREATE TABLE ProductImage (
    image_id INT IDENTITY PRIMARY KEY,
    product_id INT,
    image_path NVARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE ProductType (
    type_id INT IDENTITY PRIMARY KEY,
    color NVARCHAR(50),
    height FLOAT,
    width FLOAT,
    length FLOAT,
    weight FLOAT
);

CREATE TABLE Product_ProductType (
    product_id INT,
    type_id INT,
    PRIMARY KEY (product_id, type_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (type_id) REFERENCES ProductType(type_id)
);

CREATE TABLE Voucher (
    voucher_id INT IDENTITY PRIMARY KEY,
    code NVARCHAR(50) UNIQUE,
    discount DECIMAL(18, 2),
    start_date DATETIME,
    end_date DATETIME,
    voucher_type NVARCHAR(50),
    lowerbound_price DECIMAL(18, 2) NULL, 
    target_product_id INT NULL, 
    FOREIGN KEY (target_product_id) REFERENCES Product(product_id)
);


CREATE TABLE [Order] (
    order_id INT IDENTITY PRIMARY KEY,
    customer_id INT,
    order_date DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    discount_amount DECIMAL(18, 2) DEFAULT 0,
    shipping_fee DECIMAL(18, 2) DEFAULT 0,
    total_cost DECIMAL(18, 2),
    address_id INT, -- Liên kết tới bảng Address
    voucher_id INT, -- Liên kết tới Voucher
    phone_number NVARCHAR(20),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id),
    FOREIGN KEY (voucher_id) REFERENCES Voucher(voucher_id)
);

CREATE TABLE OrderItem (
    order_item_id INT IDENTITY PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(18, 2),
    FOREIGN KEY (order_id) REFERENCES [Order](order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE PaymentMethod (
    method_id INT IDENTITY PRIMARY KEY,
    method_name NVARCHAR(50) -- COD, QR, Credit Card...
);

CREATE TABLE Payment (
    payment_id INT IDENTITY PRIMARY KEY,
    order_id INT,
    payment_date DATETIME,
    status NVARCHAR(50),
    method_id INT,
    -- Các trường mở rộng cho CODPayment hoặc QR
    phone_number_cod NVARCHAR(20) NULL,
    qr_image_path NVARCHAR(255) NULL,
    FOREIGN KEY (order_id) REFERENCES [Order](order_id),
    FOREIGN KEY (method_id) REFERENCES PaymentMethod(method_id)
);

CREATE TABLE Cart (
    cart_id INT IDENTITY PRIMARY KEY,
    customer_id INT UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE CartItem (
    cart_item_id INT IDENTITY PRIMARY KEY,
    cart_id INT,
    product_id INT,
    type_id INT,
    quantity INT,
    price DECIMAL(18, 2),
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (type_id) REFERENCES ProductType(type_id)
);

CREATE TABLE Review (
    review_id INT IDENTITY PRIMARY KEY,
    customer_id INT,
    product_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(MAX),
    review_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE Wishlist (
    wishlist_id INT IDENTITY PRIMARY KEY,
    customer_id INT UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Wishlist_Product (
    wishlist_id INT,
    product_id INT,
    PRIMARY KEY (wishlist_id, product_id),
    FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE Blog (
    blog_id INT IDENTITY PRIMARY KEY,
    title NVARCHAR(255),
    content NVARCHAR(MAX),
    posting_date DATETIME,
    approval_status BIT,
    approved_by INT,
    FOREIGN KEY (approved_by) REFERENCES Administrator(admin_id)
);

CREATE TABLE Conversation (
    conversation_id INT IDENTITY PRIMARY KEY,
    customer_id INT,
    employee_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE Message (
    message_id INT IDENTITY PRIMARY KEY,
    conversation_id INT,
    sender_id INT,
    content NVARCHAR(MAX),
    sent_time DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (conversation_id) REFERENCES Conversation(conversation_id),
    FOREIGN KEY (sender_id) REFERENCES [User](user_id)
);