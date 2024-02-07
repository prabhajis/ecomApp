#### Assumptions

I was unable to insert product IDs that do not exist in the products table to the shelves table. The product IDs provided in shelves.json include product ids that are not present in the product table. It is assumed that these entries do not create a complete dataset.

#versions used
Java 8
Mysql 5.7
Spring boot 2.5.9

#### Application Capabilities
1. Insert Product list using the below endpoint

http://<host>:<port>/data-service/v1/addProducts 

2. Insert shelves data using the below endpoint

http://<host>:<port>/data-service/v1/addShelves

3. Get product data by shopper Id and other optional parameters

http://<host>:<port>/ecom-service/v1/get-products?shopperId=S-1000&limit=2

4. Get shopper details by product id

http://<host>:<port>/ecom-service/v1/get-shopper?productId=BM-1173639537&limit=6


#### For Testing convenience, you can access swagger url below. 
http://<host>:<port>/swagger-ui/index.html#/

#### Please find the postman collection inside the repository "ecomApp.postman_collection.json"


