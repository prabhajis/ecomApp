-- create database
CREATE DATABASE ecommercedb;

USE ecommercedb;

-- Create a table for products

CREATE TABLE products (
    product_id VARCHAR(15) PRIMARY KEY,
    category VARCHAR(50),
    brand VARCHAR(50)
);

-- Create a table for shelves
CREATE TABLE shelves (
    shopper_id VARCHAR(10),
    product_id VARCHAR(15),
    relevancy_score DOUBLE,
    PRIMARY KEY (shopper_id, product_id) ON CASCADE DELETE,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

