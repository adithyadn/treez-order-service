# Sample Requests

## Inventory Service
1. Create inventory item
   POST http://localhost:3000/inventories
```json
	{
		"productName": "Apple iPhone",
		"description": "Best iPhone Ever",
		"quantity": 100,
		"price": 999.99
	}
```
2. Read all inventory items
   GET http://localhost:3000/inventories
   
3. Read single inventory item
   GET http://localhost:3000/inventories/1
	
4. Update inventory item
   PUT http://localhost:3000/inventories/1
```json
	{
		"productId": 1,
		"productName": "Samsung S11",
		"description": "Best Samsung Ever",
		"quantity": 100,
		"price": 899.99
	}
```

5. Delete inventory item
   DELETE http://localhost:3000/inventories/1
 
 
 ## Order Service
	
6. Create order
   POST http://localhost:3000/orders
```json
	{
		"shippingType": "GROUND",
		"orderDateTime": "2020-02-05T18:25:43.511Z",
		"orderSubTotal": 25.00,
		"tax": 5.00,
		"orderTotal": 30.00,
		"shippingAddress": "100 Acadia Rd, North York, ON",
		"orderItems": [
				{
					"quantity": 1,
					"itemPrice": 25.00,
					"product": {
						"productId": 1
					}
				}
			]

	}
```

7. Read all orders
   GET http://localhost:3000/orders
   
8. Read single order
   GET http://localhost:3000/orders/1
	
9. Update order
   PUT http://localhost:3000/orders/1
```json
	{
		"shippingType": "OVERNIGHT",
		"orderDateTime": "2020-02-05T18:25:43.511Z",
		"orderSubTotal": 25.00,
		"tax": 5.00,
		"orderTotal": 30.00,
		"shippingAddress": "100 Acadia Rd, North York, ON",
		"orderItems": [
				{
					"quantity": 1,
					"itemPrice": 25.00,
					"product": {
						"productId": 1
					}
				}
			]

	}	 
```

10. Delete order
    DELETE http://localhost:3000/orders/1
