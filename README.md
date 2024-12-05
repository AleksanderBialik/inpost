
## Run Locally

Go to the project directory

```bash
  ./gradlew clean build -x test
```
```bash
  docker compose up
```
app is available at localhost:8080


## API Reference

#### Create a pricing policy

```http
  POST /v1/pricing-policy
```

```
{
    "name":"test",
    "type":"PRODUCT_AMOUNT",
    "discountLevels":[{
        "minProductsQuantity":10,
        "value":1
    }],
    "minProductsPriceBeforeTax":1,
    "canBeAppliedWithOtherPolicies":true,
    "calculationType":"PERCENTAGE/FLAT"
}
```
#### Get calculation

```http
  GET /v1/calculation
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `productId`      | `string` | **Required**. Id of the prodcut |
| `productQuantity`      | `integer` | **Required**. Quantity of the products in calculation |
| `policiesIds`      | `[uuid]` | **Required**. Ids of policies |


