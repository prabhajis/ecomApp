-- create database
CREATE DATABASE ecommercedb;

USE ecommercedb;;

-- Create a table for products
CREATE TABLE Products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(15),
    category VARCHAR(50),
    brand VARCHAR(50)
);

-- Create a table for shelves
CREATE TABLE Shelves (
    shopper_id VARCHAR(10),
    product_id VARCHAR(15),
    relevancy_score DOUBLE,
    PRIMARY KEY (shopper_id, product_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);