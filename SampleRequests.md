# Sample Request


	1. Create inventory item
		  POST http://localhost:3000/inventories
		  ```
		    {
            	"productName": "Apple iPhone",
            	"description": "Best iPhone Ever",
            	"quantity": 100,
            	"price": 999.99
            }```
	2. Read all inventory items
		  GET http://localhost:3000/inventories
	3. Read single inventory item
		  GET http://localhost:3000/inventories/1
	4. Update inventory item
		  PUT http://localhost:3000/inventories/1
	5. Delete inventory item
		  DELETE http://localhost:3000/inventories/1
	6. Create order
		  POST http://localhost:3000/orders
	7. Read all orders
		  GET http://localhost:3000/orders
	8. Read single order
		  GET http://localhost:3000/orders/1
	9. Update order
		  PUT http://localhost:3000/orders/1
	10. Delete order
      DELETE http://localhost:3000/orders/1
