#### Application Capabilities
1. Insert Product list using the below endpoint

http://host:port/data-service/v1/addProducts 

2. Insert shelves data using the below endpoint

http://host:port/data-service/v1/addShelves

3. Get product data by shopper Id and other optional parameters

http://host:port/ecom-service/v1/get-products?shopperId=S-1000&limit=2

4. Get shopper details by product id

http://host:port/ecom-service/v1/get-shopper?productId=BM-1173639537&limit=6

5. Delete Products by Product Id

   http://host:port/ecom-service/v1/delete-product/{productId}

7. Delete Shopper by Shopper Id and Product Id

   http://host:port/ecom-service/v1/delete-shopper/{productId}/{shopperId}

#versions used
Java 8
Mysql 5.7
Spring boot 2.5.9

#### For Testing convenience, you can access the swagger URL below. 

http://<host>:<port>/swagger-ui/index.html#/

#### Please find the postman collection inside the repository "ecomApp.postman_collection.json"


